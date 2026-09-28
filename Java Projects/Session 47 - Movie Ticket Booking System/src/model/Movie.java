package model;

public class Movie {
    private final int id;
    private final String title;
    private final String genre;
    private final int durationMinutes;

    public Movie(int id, String title, String genre, int durationMinutes) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - %d mins", id, title, genre, durationMinutes);
    }
}
