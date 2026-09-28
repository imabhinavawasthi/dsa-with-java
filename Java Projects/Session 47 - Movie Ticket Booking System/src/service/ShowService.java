package service;

import model.Show;
import model.Seat;
import exception.ShowNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowService {
    // Primary storage: O(1) lookup by showId
    private final Map<Integer, Show> showMap;

    public ShowService() {
        this.showMap = new HashMap<>();
    }

    public void addShow(Show show) {
        showMap.put(show.getId(), show);
    }

    public Show getShow(int showId) throws ShowNotFoundException {
        Show show = showMap.get(showId);
        if (show == null) {
            throw new ShowNotFoundException("Show with ID " + showId + " does not exist.");
        }
        return show;
    }

    public List<Show> getAllShows() {
        return new ArrayList<>(showMap.values());
    }

    public List<Show> getShowsForMovie(int movieId) {
        List<Show> result = new ArrayList<>();
        for (Show s : showMap.values()) {
            if (s.getMovie().getId() == movieId) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Show> getShowsForTheatre(int theatreId) {
        List<Show> result = new ArrayList<>();
        for (Show s : showMap.values()) {
            if (s.getTheatre().getId() == theatreId) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Seat> getAvailableSeats(int showId) throws ShowNotFoundException {
        Show show = getShow(showId);
        return show.getAvailableSeats();
    }
}
