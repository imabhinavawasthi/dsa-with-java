/**
 * Room Entity Class
 * Represents a hotel room with availability status and occupant information
 */
public class Room {
    private int roomNumber;
    private boolean isAvailable;
    private double price;
    private Guest guest; // Reference to the guest occupying the room

    /**
     * Constructor to initialize a Room object
     *
     * @param roomNumber The room number
     * @param isAvailable The availability status of the room
     * @param price The price per night for the room
     */
    public Room(int roomNumber, boolean isAvailable, double price) {
        this.roomNumber = roomNumber;
        this.isAvailable = isAvailable;
        this.price = price;
        this.guest = null; // No guest initially
    }

    /**
     * Gets the room number
     *
     * @return The room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the room number
     *
     * @param roomNumber The room number to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Checks if the room is available
     *
     * @return true if the room is available, false otherwise
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability status of the room
     *
     * @param available The availability status to set
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Gets the price of the room
     *
     * @return The room price per night
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the room
     *
     * @param price The room price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the guest currently occupying the room
     *
     * @return The guest object or null if no guest
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * Sets the guest occupying the room
     *
     * @param guest The guest object to set
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    /**
     * Returns a string representation of the room
     *
     * @return Room information as a string
     */
    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", isAvailable=" + isAvailable +
                ", price=" + price +
                ", guest=" + (guest != null ? guest.getName() : "None") +
                '}';
    }
}

