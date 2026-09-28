package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private final String name;
    private final String email;
    private final List<Integer> bookingIds;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bookingIds = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Integer> getBookingIds() {
        return bookingIds;
    }

    public void addBooking(int bookingId) {
        this.bookingIds.add(bookingId);
    }

    @Override
    public String toString() {
        return String.format("User [ID: %d, Name: %s, Email: %s, Total Bookings: %d]",
                id, name, email, bookingIds.size());
    }
}
