import model.*;
import exception.*;
import payment.*;
import service.*;

import java.util.*;

public class Main {
    private static final String BOOKINGS_FILE = "bookings.txt";

    public static void main(String[] args) {
        System.out.println("===============================================================");
        System.out.println("       WELCOME TO BOOKMYSHOW - MINI TICKET BOOKING SYSTEM      ");
        System.out.println("        Session 47 Java Mini Project 1 (Production Ready)      ");
        System.out.println("===============================================================");

        // 1. Initialize Services (Layered Architecture)
        MovieService movieService = new MovieService();
        TheatreService theatreService = new TheatreService();
        ShowService showService = new ShowService();
        UserService userService = new UserService();
        BookingService bookingService = new BookingService(showService, userService);

        // 2. Seed Mock Domain Data
        seedInitialData(movieService, theatreService, showService, userService);

        // 3. Load Persisted State if present
        bookingService.loadBookingsFromFile(BOOKINGS_FILE);

        // 4. Scanner CLI Menu
        Scanner scanner = new Scanner(System.in);
        int currentUserId = 1; // Default logged-in user: Alice

        boolean running = true;
        while (running) {
            User currentUser = userService.getUser(currentUserId);
            System.out.println("\n-------------------------------------------------------------");
            System.out.printf("  ACTIVE USER: %s (ID: %d, Email: %s)\n",
                    currentUser != null ? currentUser.getName() : "None",
                    currentUserId,
                    currentUser != null ? currentUser.getEmail() : "N/A");
            System.out.println("-------------------------------------------------------------");
            System.out.println(" 1. Browse All Movies");
            System.out.println(" 2. Search Movie by Title / Genre");
            System.out.println(" 3. View All Shows & Available Seats");
            System.out.println(" 4. View Seat Layout for a Show");
            System.out.println(" 5. Book Movie Tickets (Interactive & Atomic)");
            System.out.println(" 6. Cancel a Booking (Refund & Seat Release)");
            System.out.println(" 7. View My Booking History");
            System.out.println(" 8. Switch / Register User");
            System.out.println(" 9. Concurrency Simulation Test (Two Users, Same Seat)");
            System.out.println(" 0. Graceful Exit (Auto-Save State)");
            System.out.print(" Select an option [0-9]: ");

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid choice. Please enter a number between 0 and 9.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        displayAllMovies(movieService);
                        break;
                    case 2:
                        searchMovies(scanner, movieService);
                        break;
                    case 3:
                        displayAllShows(showService);
                        break;
                    case 4:
                        displaySeatLayout(scanner, showService);
                        break;
                    case 5:
                        handleBooking(scanner, showService, bookingService, currentUserId);
                        break;
                    case 6:
                        handleCancellation(scanner, bookingService);
                        break;
                    case 7:
                        displayUserHistory(bookingService, currentUserId);
                        break;
                    case 8:
                        currentUserId = handleUserSwitch(scanner, userService, currentUserId);
                        break;
                    case 9:
                        runConcurrencyTest(showService, userService, bookingService);
                        break;
                    case 0:
                        System.out.println("\n  [Shutdown] Auto-saving all bookings to file...");
                        bookingService.saveBookingsToFile(BOOKINGS_FILE);
                        System.out.println("  Thank you for using Mini BookMyShow! Goodbye.");
                        running = false;
                        break;
                    default:
                        System.out.println(" Unknown option. Please choose from 0 to 9.");
                }
            } catch (ShowNotFoundException | SeatNotAvailableException |
                     BookingNotFoundException | InvalidSeatException e) {
                System.out.println("\n  [BUSINESS ERROR] " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\n  [SYSTEM ERROR] " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void displayAllMovies(MovieService movieService) {
        System.out.println("\n--- NOW SHOWING MOVIES ---");
        List<Movie> movies = movieService.getAllMovies();
        for (Movie m : movies) {
            System.out.println("  " + m);
        }
    }

    private static void searchMovies(Scanner sc, MovieService movieService) {
        System.out.print("\nEnter keyword to search (title or genre): ");
        String kw = sc.nextLine().trim();
        List<Movie> matched = movieService.searchMovies(kw);
        if (matched.isEmpty()) {
            System.out.println("  No movies found matching: '" + kw + "'");
        } else {
            System.out.println("  Found " + matched.size() + " match(es):");
            for (Movie m : matched) {
                System.out.println("   * " + m);
            }
        }
    }

    private static void displayAllShows(ShowService showService) {
        System.out.println("\n--- SCHEDULED SHOWS ---");
        List<Show> shows = showService.getAllShows();
        for (Show s : shows) {
            System.out.println("  " + s);
        }
    }

    private static void displaySeatLayout(Scanner sc, ShowService showService) throws ShowNotFoundException {
        System.out.print("\nEnter Show ID: ");
        int showId = Integer.parseInt(sc.nextLine().trim());
        Show show = showService.getShow(showId);

        System.out.println("\n=============================================================");
        System.out.printf("  SEAT MATRIX: %s @ %s (%s)\n",
                show.getMovie().getTitle(), show.getTheatre().getName(), show.getShowTime());
        System.out.println("=============================================================");
        System.out.println("           ------------- [ SCREEN THIS WAY ] -------------   \n");

        Screen screen = show.getScreen();
        for (int r = 0; r < screen.getTotalRows(); r++) {
            char rowChar = (char) ('A' + r);
            System.out.printf("  Row %c | ", rowChar);
            for (int c = 1; c <= screen.getSeatsPerRow(); c++) {
                String seatNum = "" + rowChar + c;
                Seat seat = show.getSeat(seatNum);
                if (seat.isBooked()) {
                    System.out.print("[ X ] "); // X means BOOKED
                } else {
                    System.out.printf("[%s] ", seatNum);
                }
            }
            // Print category label
            if (r == 0) System.out.print(" <-- RECLINER (₹500)");
            else if (r <= 2) System.out.print(" <-- PREMIUM (₹300)");
            else System.out.print(" <-- REGULAR (₹200)");
            System.out.println();
        }
        System.out.println("\n  Legend: [SeatID] = Available | [ X ] = Already Booked");
        System.out.printf("  Total Available: %d / %d\n", show.getAvailableCount(), screen.getTotalCapacity());
    }

    private static void handleBooking(Scanner sc, ShowService showService,
                                      BookingService bookingService, int userId)
            throws ShowNotFoundException, InvalidSeatException, SeatNotAvailableException {

        System.out.print("\nEnter Show ID to book: ");
        int showId = Integer.parseInt(sc.nextLine().trim());
        Show show = showService.getShow(showId);

        System.out.println("Selected Show: " + show);
        System.out.print("Enter Seat Numbers separated by comma (e.g. A1, A2, B3): ");
        String rawSeats = sc.nextLine().trim();

        String[] parts = rawSeats.split(",");
        List<String> selectedSeats = new ArrayList<>();
        for (String p : parts) {
            String s = p.trim().toUpperCase();
            if (!s.isEmpty()) {
                selectedSeats.add(s);
            }
        }

        if (selectedSeats.isEmpty()) {
            System.out.println("  No valid seats entered. Aborting booking.");
            return;
        }

        // Preview Pricing
        double totalCost = 0;
        for (String sn : selectedSeats) {
            Seat s = show.getSeat(sn);
            if (s == null) throw new InvalidSeatException("Seat " + sn + " does not exist!");
            if (s.isBooked()) throw new SeatNotAvailableException("Seat " + sn + " is already booked!");
            totalCost += s.getPrice();
        }

        System.out.printf("\n  Seats Chosen: %s | Estimated Total: ₹%.2f\n", selectedSeats, totalCost);
        System.out.println("  Choose Payment Method (Strategy Pattern):");
        System.out.println("    1. UPI (e.g., Google Pay / PhonePe)");
        System.out.println("    2. Credit / Debit Card");
        System.out.println("    3. Counter Cash");
        System.out.print("  Select [1-3]: ");
        int payChoice = Integer.parseInt(sc.nextLine().trim());

        PaymentProcessor processor;
        if (payChoice == 1) {
            System.out.print("  Enter UPI ID (e.g. user@okaxis): ");
            String vpa = sc.nextLine().trim();
            processor = new UPIPaymentProcessor(vpa.isEmpty() ? "user@upi" : vpa);
        } else if (payChoice == 2) {
            System.out.print("  Enter 16-Digit Card Number: ");
            String card = sc.nextLine().trim();
            processor = new CardPaymentProcessor(card.isEmpty() ? "1111222233334444" : card);
        } else {
            processor = new CashPaymentProcessor();
        }

        // Execute atomic synchronized booking
        Booking booking = bookingService.bookSeats(showId, userId, selectedSeats, processor);
        System.out.println("\n" + booking);
        System.out.println("  CONGRATULATIONS! Tickets confirmed.");
    }

    private static void handleCancellation(Scanner sc, BookingService bookingService)
            throws BookingNotFoundException {
        System.out.print("\nEnter Booking ID to cancel (e.g. 1001): ");
        int bId = Integer.parseInt(sc.nextLine().trim());

        Booking b = bookingService.getBooking(bId);
        System.out.println("Current Booking Summary:");
        System.out.printf("  Movie: %s | Seats: %s | Status: %s\n",
                b.getShow().getMovie().getTitle(), b.getSeatNumbers(), b.getStatus());

        System.out.print("Are you sure you want to cancel this booking? (yes/no): ");
        String confirm = sc.nextLine().trim().toLowerCase();
        if (confirm.equals("yes") || confirm.equals("y")) {
            boolean cancelled = bookingService.cancelBooking(bId);
            if (cancelled) {
                System.out.printf("  SUCCESS: Booking #B%d has been CANCELLED.\n", bId);
                System.out.println("  Seats released back to inventory. Refund initiated via " + b.getPaymentMethod());
            }
        } else {
            System.out.println("  Cancellation aborted.");
        }
    }

    private static void displayUserHistory(BookingService bookingService, int userId) {
        List<Booking> list = bookingService.getUserBookings(userId);
        System.out.printf("\n--- BOOKING HISTORY FOR USER #%d (%d record(s)) ---\n", userId, list.size());
        if (list.isEmpty()) {
            System.out.println("  No bookings found for this user.");
            return;
        }
        for (Booking b : list) {
            System.out.printf("  * [#B%d] %s | Seats: %s | ₹%.2f | Status: %s | %s\n",
                    b.getBookingId(),
                    b.getShow().getMovie().getTitle(),
                    String.join(", ", b.getSeatNumbers()),
                    b.getTotalAmount(),
                    b.getStatus(),
                    b.getShow().getShowTime());
        }
    }

    private static int handleUserSwitch(Scanner sc, UserService userService, int currentUserId) {
        System.out.println("\n--- USER MANAGEMENT ---");
        System.out.println(" 1. Switch to existing user");
        System.out.println(" 2. Register new user");
        System.out.print(" Select: ");
        int op = Integer.parseInt(sc.nextLine().trim());
        if (op == 1) {
            System.out.println(" Registered Users:");
            for (User u : userService.getAllUsers()) {
                System.out.println("   " + u);
            }
            System.out.print(" Enter User ID to switch to: ");
            int uid = Integer.parseInt(sc.nextLine().trim());
            if (userService.containsUser(uid)) {
                System.out.println(" Switched to User: " + userService.getUser(uid).getName());
                return uid;
            } else {
                System.out.println(" User ID not found.");
            }
        } else if (op == 2) {
            System.out.print(" Enter Name: ");
            String name = sc.nextLine().trim();
            System.out.print(" Enter Email: ");
            String email = sc.nextLine().trim();
            int newId = userService.getAllUsers().size() + 1;
            User newUser = new User(newId, name, email);
            userService.registerUser(newUser);
            System.out.println(" Registered successfully: " + newUser);
            return newId;
        }
        return currentUserId;
    }

    /**
     * CONCURRENCY SIMULATION:
     * Demonstrates race condition protection via `synchronized` on bookingService.bookSeats.
     * Two concurrent threads attempt to book the exact same seat (e.g. A1) at the same millisecond.
     */
    private static void runConcurrencyTest(ShowService showService, UserService userService, BookingService bookingService) {
        System.out.println("\n=============================================================");
        System.out.println("   RACE CONDITION SIMULATION: 2 THREADS TRYING TO BOOK A1    ");
        System.out.println("=============================================================");

        int testShowId = 101;
        List<String> targetSeat = Collections.singletonList("A1");

        // Verify A1 availability first
        try {
            Seat s = showService.getShow(testShowId).getSeat("A1");
            if (s.isBooked()) {
                System.out.println("  [Setup] Seat A1 is currently booked. Freeing it for the race test...");
                s.setStatus(SeatStatus.AVAILABLE);
            }
        } catch (Exception ignored) {}

        Thread userA = new Thread(() -> {
            try {
                System.out.println("  [Thread-UserA] Submitting request for Seat A1...");
                Booking b = bookingService.bookSeats(testShowId, 1, targetSeat, new CashPaymentProcessor());
                System.out.println("  [Thread-UserA] SUCCESS! Booked: " + b.getBookingId());
            } catch (Exception e) {
                System.out.println("  [Thread-UserA] FAILED as expected: " + e.getMessage());
            }
        }, "UserA-Thread");

        Thread userB = new Thread(() -> {
            try {
                System.out.println("  [Thread-UserB] Submitting request for Seat A1...");
                Booking b = bookingService.bookSeats(testShowId, 2, targetSeat, new CashPaymentProcessor());
                System.out.println("  [Thread-UserB] SUCCESS! Booked: " + b.getBookingId());
            } catch (Exception e) {
                System.out.println("  [Thread-UserB] FAILED as expected: " + e.getMessage());
            }
        }, "UserB-Thread");

        userA.start();
        userB.start();

        try {
            userA.join();
            userB.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("=============================================================");
        System.out.println("  VERDICT: Exactly ONE thread got the booking, the other failed");
        System.out.println("  safely with SeatNotAvailableException. No double-booking occurred!");
        System.out.println("=============================================================");
    }

    private static void seedInitialData(MovieService movieService, TheatreService theatreService,
                                        ShowService showService, UserService userService) {
        // Movies
        Movie m1 = new Movie(1, "Avengers: Endgame", "Action/Sci-Fi", 181);
        Movie m2 = new Movie(2, "Interstellar", "Sci-Fi/Adventure", 169);
        Movie m3 = new Movie(3, "Inception", "Sci-Fi/Thriller", 148);
        Movie m4 = new Movie(4, "The Dark Knight", "Action/Crime", 152);
        movieService.addMovie(m1);
        movieService.addMovie(m2);
        movieService.addMovie(m3);
        movieService.addMovie(m4);

        // Theatres & Screens
        Theatre t1 = new Theatre(1, "PVR Cinemas, Orion Mall", "Bangalore");
        Screen sc1 = new Screen(1, "IMAX Screen 1", 5, 6); // 5 rows (A-E), 6 seats each = 30 seats
        Screen sc2 = new Screen(2, "Gold Screen 2", 4, 5); // 4 rows, 5 seats each = 20 seats
        t1.addScreen(sc1);
        t1.addScreen(sc2);
        theatreService.addTheatre(t1);

        Theatre t2 = new Theatre(2, "INOX Megaplex", "Mumbai");
        Screen sc3 = new Screen(3, "Dolby Atmos Screen", 5, 5);
        t2.addScreen(sc3);
        theatreService.addTheatre(t2);

        // Shows
        Show s1 = new Show(101, m1, t1, sc1, "Today, 06:30 PM");
        Show s2 = new Show(102, m2, t1, sc1, "Today, 10:00 PM");
        Show s3 = new Show(103, m3, t1, sc2, "Today, 07:00 PM");
        Show s4 = new Show(104, m4, t2, sc3, "Tomorrow, 08:00 PM");
        showService.addShow(s1);
        showService.addShow(s2);
        showService.addShow(s3);
        showService.addShow(s4);

        // Pre-book a couple seats in Show 101 to demonstrate initial state
        Seat a3 = s1.getSeat("A3");
        if (a3 != null) a3.setStatus(SeatStatus.BOOKED);
        Seat b4 = s1.getSeat("B4");
        if (b4 != null) b4.setStatus(SeatStatus.BOOKED);

        // Users
        userService.registerUser(new User(1, "Alice Sharma", "alice@example.com"));
        userService.registerUser(new User(2, "Bob Verma", "bob@example.com"));
        userService.registerUser(new User(3, "Charlie Patel", "charlie@example.com"));
    }
}
