package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Theatre {
    private final int id;
    private final String name;
    private final String city;
    private final Map<Integer, Screen> screens;

    public Theatre(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.screens = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Map<Integer, Screen> getScreens() {
        return screens;
    }

    public void addScreen(Screen screen) {
        screens.put(screen.getId(), screen);
    }

    public Screen getScreen(int screenId) {
        return screens.get(screenId);
    }

    public List<Screen> getAllScreens() {
        return new ArrayList<>(screens.values());
    }

    @Override
    public String toString() {
        return String.format("[%d] %s, %s (%d screens)", id, name, city, screens.size());
    }
}
