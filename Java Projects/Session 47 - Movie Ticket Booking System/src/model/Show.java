package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Show {
    private final int id;
    private final Movie movie;
    private final Theatre theatre;
    private final Screen screen;
    private final String showTime;
    // Map of seatNumber (e.g., "A1") -> Seat instance
    private final Map<String, Seat> seats;

    public Show(int id, Movie movie, Theatre theatre, Screen screen, String showTime) {
        this.id = id;
        this.movie = movie;
        this.theatre = theatre;
        this.screen = screen;
        this.showTime = showTime;
        this.seats = new LinkedHashMap<>();
        initializeSeats();
    }

    /**
     * Initializes show-specific seats based on screen layout:
     * - Row A: RECLINER (₹500)
     * - Row B & C: PREMIUM (₹300)
     * - Remaining rows: REGULAR (₹200)
     */
    private void initializeSeats() {
        int rows = screen.getTotalRows();
        int cols = screen.getSeatsPerRow();

        for (int r = 0; r < rows; r++) {
            char rowChar = (char) ('A' + r);
            SeatType type;
            if (r == 0) {
                type = SeatType.RECLINER;
            } else if (r <= 2) {
                type = SeatType.PREMIUM;
            } else {
                type = SeatType.REGULAR;
            }

            for (int c = 1; c <= cols; c++) {
                String seatNum = "" + rowChar + c;
                seats.put(seatNum, new Seat(seatNum, type));
            }
        }
    }

    public int getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public Screen getScreen() {
        return screen;
    }

    public String getShowTime() {
        return showTime;
    }

    public Map<String, Seat> getSeats() {
        return seats;
    }

    public Seat getSeat(String seatNumber) {
        return seats.get(seatNumber.toUpperCase().trim());
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> avail = new ArrayList<>();
        for (Seat s : seats.values()) {
            if (!s.isBooked()) {
                avail.add(s);
            }
        }
        return avail;
    }

    public int getAvailableCount() {
        int count = 0;
        for (Seat s : seats.values()) {
            if (!s.isBooked()) count++;
        }
        return count;
    }

    @Override
    public String toString() {
        return String.format("[Show #%d] %s at %s (%s) | Time: %s | Available Seats: %d/%d",
                id, movie.getTitle(), theatre.getName(), screen.getName(),
                showTime, getAvailableCount(), seats.size());
    }
}
