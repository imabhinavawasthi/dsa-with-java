package service;

import model.*;
import exception.*;
import payment.PaymentProcessor;

import java.io.*;
import java.util.*;

public class BookingService {
    // Primary storage: O(1) lookup by bookingId
    private final Map<Integer, Booking> bookingMap;
    private final ShowService showService;
    private final UserService userService;
    private int nextBookingId = 1001;

    public BookingService(ShowService showService, UserService userService) {
        this.bookingMap = new HashMap<>();
        this.showService = showService;
        this.userService = userService;
    }

    /**
     * ATOMIC BOOKING ENGINE WITH CONCURRENCY SYNCHRONIZATION:
     * 1. Find Show
     * 2. Validate User
     * 3. Validate Seats (format, existence)
     * 4. Check Availability (atomic check)
     * 5. Execute Payment via Strategy Pattern
     * 6. Mark Seats BOOKED (only after ALL validated)
     * 7. Create & Store Booking
     */
    public synchronized Booking bookSeats(int showId, int userId, List<String> seatNumbers,
                                         PaymentProcessor paymentProcessor)
            throws ShowNotFoundException, InvalidSeatException, SeatNotAvailableException, IllegalArgumentException {

        // 1. Validate inputs
        if (seatNumbers == null || seatNumbers.isEmpty()) {
            throw new IllegalArgumentException("Must select at least one seat to book.");
        }

        // 2. Validate Show
        Show show = showService.getShow(showId);

        // 3. Validate User
        User user = userService.getUser(userId);
        if (user == null) {
            throw new IllegalArgumentException("User with ID " + userId + " is not registered.");
        }

        // 4. Validate Seats existence & availability ATOMICALLY
        List<Seat> validatedSeats = new ArrayList<>();
        double calculatedAmount = 0.0;

        for (String seatNum : seatNumbers) {
            Seat seat = show.getSeat(seatNum);

            if (seat == null) {
                throw new InvalidSeatException("Seat '" + seatNum + "' does not exist in Screen: " + show.getScreen().getName());
            }

            if (seat.isBooked()) {
                throw new SeatNotAvailableException("Seat '" + seatNum + "' is ALREADY BOOKED for this show. Transaction aborted.");
            }

            validatedSeats.add(seat);
            calculatedAmount += seat.getPrice();
        }

        // 5. Payment processing (Strategy Pattern)
        if (paymentProcessor != null) {
            boolean success = paymentProcessor.processPayment(calculatedAmount);
            if (!success) {
                throw new IllegalStateException("Payment failed. Seats have NOT been booked.");
            }
        }

        // 6. ALL or NOTHING: Now mark all validated seats as BOOKED
        for (Seat seat : validatedSeats) {
            seat.setStatus(SeatStatus.BOOKED);
        }

        // 7. Generate and record Booking
        int bookingId = nextBookingId++;
        String paymentDesc = paymentProcessor != null ? paymentProcessor.getMethodName() : "Paid";
        Booking booking = new Booking(bookingId, user, show, new ArrayList<>(seatNumbers), calculatedAmount, paymentDesc);

        bookingMap.put(bookingId, booking);
        user.addBooking(bookingId);

        return booking;
    }

    /**
     * CANCELLATION ENGINE:
     * Maintains historical record. Status changes to CANCELLED, and seats return to AVAILABLE.
     */
    public synchronized boolean cancelBooking(int bookingId) throws BookingNotFoundException {
        Booking booking = bookingMap.get(bookingId);
        if (booking == null) {
            throw new BookingNotFoundException("Booking with ID #B" + bookingId + " not found.");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            System.out.println("  [Notice] Booking #B" + bookingId + " is ALREADY CANCELLED.");
            return false;
        }

        // Release seats back to AVAILABLE in that specific show
        Show show = booking.getShow();
        for (String seatNum : booking.getSeatNumbers()) {
            Seat seat = show.getSeat(seatNum);
            if (seat != null) {
                seat.setStatus(SeatStatus.AVAILABLE);
            }
        }

        // Mark booking status as CANCELLED (Never delete data!)
        booking.setStatus(BookingStatus.CANCELLED);
        return true;
    }

    public Booking getBooking(int bookingId) throws BookingNotFoundException {
        Booking booking = bookingMap.get(bookingId);
        if (booking == null) {
            throw new BookingNotFoundException("Booking with ID #B" + bookingId + " does not exist.");
        }
        return booking;
    }

    public List<Booking> getUserBookings(int userId) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking b : bookingMap.values()) {
            if (b.getUser().getId() == userId) {
                userBookings.add(b);
            }
        }
        return userBookings;
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookingMap.values());
    }

    /**
     * File Persistence: Save bookings to CSV
     */
    public void saveBookingsToFile(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Booking b : bookingMap.values()) {
                // Format: bookingId,userId,showId,seatsJoined,totalAmount,status,paymentMethod
                String line = String.format("%d,%d,%d,%s,%.2f,%s,%s",
                        b.getBookingId(),
                        b.getUser().getId(),
                        b.getShow().getId(),
                        String.join(";", b.getSeatNumbers()),
                        b.getTotalAmount(),
                        b.getStatus().name(),
                        b.getPaymentMethod()
                );
                bw.write(line);
                bw.newLine();
            }
            System.out.println("  [Persistence] Successfully saved " + bookingMap.size() + " bookings to: " + filePath);
        } catch (IOException e) {
            System.err.println("  [Error] Failed to save bookings to file: " + e.getMessage());
        }
    }

    /**
     * File Persistence: Load bookings from CSV
     */
    public void loadBookingsFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int loadedCount = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    int bId = Integer.parseInt(parts[0].trim());
                    int uId = Integer.parseInt(parts[1].trim());
                    int sId = Integer.parseInt(parts[2].trim());
                    List<String> seats = Arrays.asList(parts[3].trim().split(";"));
                    double amt = Double.parseDouble(parts[4].trim());
                    BookingStatus status = BookingStatus.valueOf(parts[5].trim());
                    String payMethod = parts[6].trim();

                    User user = userService.getUser(uId);
                    Show show = showService.getShow(sId);

                    if (user != null && show != null) {
                        Booking b = new Booking(bId, user, show, seats, amt, payMethod);
                        b.setStatus(status);

                        // If it was confirmed, mark seats booked
                        if (status == BookingStatus.CONFIRMED) {
                            for (String s : seats) {
                                Seat seat = show.getSeat(s);
                                if (seat != null) seat.setStatus(SeatStatus.BOOKED);
                            }
                        }

                        bookingMap.put(bId, b);
                        user.addBooking(bId);
                        if (bId >= nextBookingId) {
                            nextBookingId = bId + 1;
                        }
                        loadedCount++;
                    }
                }
            }
            System.out.println("  [Persistence] Loaded " + loadedCount + " persisted bookings from: " + filePath);
        } catch (Exception e) {
            System.err.println("  [Notice] Initializing clean state (Could not parse existing file: " + e.getMessage() + ")");
        }
    }
}
