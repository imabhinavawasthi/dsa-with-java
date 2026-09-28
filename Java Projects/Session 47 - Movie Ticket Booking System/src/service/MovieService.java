package service;

import model.Movie;
import exception.MovieNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieService {
    // Primary storage: O(1) lookup by movieId
    private final Map<Integer, Movie> movieMap;

    public MovieService() {
        this.movieMap = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        movieMap.put(movie.getId(), movie);
    }

    public Movie getMovie(int movieId) throws MovieNotFoundException {
        Movie movie = movieMap.get(movieId);
        if (movie == null) {
            throw new MovieNotFoundException("Movie with ID " + movieId + " does not exist.");
        }
        return movie;
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movieMap.values());
    }

    /**
     * Case-insensitive keyword search by movie title.
     * Full collection scan: O(N) trade-off discussed in Session Agenda.
     */
    public List<Movie> searchMovies(String keyword) {
        List<Movie> results = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) return results;
        String query = keyword.toLowerCase().trim();
        for (Movie movie : movieMap.values()) {
            if (movie.getTitle().toLowerCase().contains(query) ||
                movie.getGenre().toLowerCase().contains(query)) {
                results.add(movie);
            }
        }
        return results;
    }

    public boolean containsMovie(int movieId) {
        return movieMap.containsKey(movieId);
    }
}
