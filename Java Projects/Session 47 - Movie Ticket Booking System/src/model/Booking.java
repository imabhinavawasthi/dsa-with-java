package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Booking {
    private final int bookingId;
    private final User user;
    private final Show show;
    private final List<String> seatNumbers;
    private final double totalAmount;
    private final LocalDateTime bookingTime;
    private BookingStatus status;
    private String paymentMethod;

    public Booking(int bookingId, User user, Show show, List<String> seatNumbers,
                   double totalAmount, String paymentMethod) {
        this.bookingId = bookingId;
        this.user = user;
        this.show = show;
        this.seatNumbers = seatNumbers;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.bookingTime = LocalDateTime.now();
        this.status = BookingStatus.CONFIRMED;
    }

    public int getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<String> getSeatNumbers() {
        return seatNumbers;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return String.format(
                "=========================================\n" +
                "  BOOKING RECEIPT - ID: #B%d\n" +
                "=========================================\n" +
                "  Customer     : %s (Email: %s)\n" +
                "  Movie        : %s (%d mins)\n" +
                "  Theatre      : %s (%s)\n" +
                "  Screen       : %s\n" +
                "  Show Time    : %s\n" +
                "  Seats        : %s\n" +
                "  Total Amount : ₹%.2f\n" +
                "  Payment Via  : %s\n" +
                "  Booking Date : %s\n" +
                "  Status       : %s\n" +
                "=========================================",
                bookingId, user.getName(), user.getEmail(),
                show.getMovie().getTitle(), show.getMovie().getDurationMinutes(),
                show.getTheatre().getName(), show.getTheatre().getCity(),
                show.getScreen().getName(),
                show.getShowTime(),
                String.join(", ", seatNumbers),
                totalAmount,
                paymentMethod,
                bookingTime.format(dtf),
                status
        );
    }
}
