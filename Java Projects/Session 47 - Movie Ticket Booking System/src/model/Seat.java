package model;

public class Seat {
    private final String seatNumber; // e.g. "A1", "B4"
    private final SeatType seatType;
    private SeatStatus status;

    public Seat(String seatNumber, SeatType seatType) {
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.status = SeatStatus.AVAILABLE;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public boolean isBooked() {
        return this.status == SeatStatus.BOOKED;
    }

    public double getPrice() {
        return seatType.getPrice();
    }

    @Override
    public String toString() {
        return String.format("%s (%s - ₹%.0f) [%s]", seatNumber, seatType, getPrice(), status);
    }
}
