package HotelManagementOOPSProject;

/**
 * HotelManager Class - Service/Manager Layer
 * Manages hotel operations including room bookings and checkouts
 */
public class HotelManager {
    private Room[] rooms; // Array of Room objects
    private static final int TOTAL_ROOMS = 10;

    /**
     * Constructor to initialize the HotelManager with an array of rooms
     * All rooms are initialized with default availability and price
     */
    public HotelManager() {
        rooms = new Room[TOTAL_ROOMS];

        // Initialize all rooms
        for (int i = 0; i < TOTAL_ROOMS; i++) {
            rooms[i] = new Room(101 + i, true, 5000.0); // Room numbers from 101 to 110
        }
    }

    /**
     * Displays all available rooms in the hotel
     */
    public void displayAvailableRooms() {
        System.out.println("\n===== AVAILABLE ROOMS =====");
        boolean foundAvailable = false;

        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room No: " + room.getRoomNumber() +
                        " | Price: ₹" + room.getPrice() + " per night");
                foundAvailable = true;
            }
        }

        if (!foundAvailable) {
            System.out.println("No rooms available at the moment!");
        }
        System.out.println("============================\n");
    }

    /**
     * Books a room for a guest
     *
     * @param roomNo The room number to book
     * @param guest The guest object
     * @throws RoomUnavailableException if the room is not available or doesn't exist
     */
    public void bookRoom(int roomNo, Guest guest) throws RoomUnavailableException {
        // Find the room with the given room number
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNo) {
                // Check if room is available
                if (!room.isAvailable()) {
                    throw new RoomUnavailableException(
                            "Room " + roomNo + " is not available for booking!");
                }

                // Book the room
                room.setAvailable(false);
                room.setGuest(guest);
                System.out.println("\n✓ Room " + roomNo + " successfully booked for " +
                        guest.getName() + "!");
                System.out.println("Price: ₹" + room.getPrice() + " per night\n");
                return;
            }
        }

        // Room not found
        throw new RoomUnavailableException("Room " + roomNo + " does not exist!");
    }

    /**
     * Checks out a guest from a room
     *
     * @param roomNo The room number from which to checkout
     */
    public void checkOut(int roomNo) {
        // Find the room with the given room number
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNo) {
                if (!room.isAvailable()) {
                    // Room is occupied, perform checkout
                    Guest guest = room.getGuest();
                    System.out.println("\n✓ Guest " + guest.getName() +
                            " checked out from Room " + roomNo + "!");
                    System.out.println("Amount Charged: ₹" + room.getPrice() +
                            " per night\n");

                    // Make room available again
                    room.setAvailable(true);
                    room.setGuest(null);
                } else {
                    // Room is already empty
                    System.out.println("\n✗ Room " + roomNo + " is already empty!\n");
                }
                return;
            }
        }

        // Room not found
        System.out.println("\n✗ Room " + roomNo + " does not exist!\n");
    }

    /**
     * Displays all rooms and their current status
     */
    public void displayAllRooms() {
        System.out.println("\n===== ALL ROOMS STATUS =====");
        for (Room room : rooms) {
            String status = room.isAvailable() ? "AVAILABLE" : "OCCUPIED by " + room.getGuest().getName();
            System.out.println("Room " + room.getRoomNumber() + " | " + status +
                    " | Price: ₹" + room.getPrice());
        }
        System.out.println("=============================\n");
    }

    /**
     * Gets a room by room number
     *
     * @param roomNo The room number
     * @return The Room object or null if not found
     */
    public Room getRoom(int roomNo) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNo) {
                return room;
            }
        }
        return null;
    }

    /**
     * Gets the total number of rooms in the hotel
     *
     * @return The total number of rooms
     */
    public int getTotalRooms() {
        return TOTAL_ROOMS;
    }

    /**
     * Gets the number of available rooms
     *
     * @return The count of available rooms
     */
    public int getAvailableRoomsCount() {
        int count = 0;
        for (Room room : rooms) {
            if (room.isAvailable()) {
                count++;
            }
        }
        return count;
    }
}

