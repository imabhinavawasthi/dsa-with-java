package model;

public enum SeatType {
    REGULAR(200.0),
    PREMIUM(300.0),
    RECLINER(500.0);

    private final double price;

    SeatType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
