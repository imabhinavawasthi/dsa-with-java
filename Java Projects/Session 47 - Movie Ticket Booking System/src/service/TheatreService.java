package service;

import model.Theatre;
import model.Screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreService {
    // Primary storage: O(1) lookup by theatreId
    private final Map<Integer, Theatre> theatreMap;

    public TheatreService() {
        this.theatreMap = new HashMap<>();
    }

    public void addTheatre(Theatre theatre) {
        theatreMap.put(theatre.getId(), theatre);
    }

    public Theatre getTheatre(int theatreId) {
        return theatreMap.get(theatreId);
    }

    public void addScreenToTheatre(int theatreId, Screen screen) {
        Theatre theatre = theatreMap.get(theatreId);
        if (theatre != null) {
            theatre.addScreen(screen);
        }
    }

    public List<Theatre> getAllTheatres() {
        return new ArrayList<>(theatreMap.values());
    }
}
