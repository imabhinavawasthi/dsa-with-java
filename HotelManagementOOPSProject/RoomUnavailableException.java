package HotelManagementOOPSProject;

/**
 * Custom Exception Class
 * Thrown when a user attempts to book a room that is not available
 */
public class RoomUnavailableException extends Exception {
    /**
     * Constructor with error message
     *
     * @param message The error message
     */
    public RoomUnavailableException(String message) {
        super(message);
    }

    /**
     * Constructor with error message and cause
     *
     * @param message The error message
     * @param cause The cause of the exception
     */
    public RoomUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}

