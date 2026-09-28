package model;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final int id;
    private final String name;
    private final int totalRows;
    private final int seatsPerRow;

    public Screen(int id, String name, int totalRows, int seatsPerRow) {
        this.id = id;
        this.name = name;
        this.totalRows = totalRows;
        this.seatsPerRow = seatsPerRow;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public int getTotalCapacity() {
        return totalRows * seatsPerRow;
    }

    @Override
    public String toString() {
        return String.format("%s (Capacity: %d seats, %d rows x %d cols)",
                name, getTotalCapacity(), totalRows, seatsPerRow);
    }
}
