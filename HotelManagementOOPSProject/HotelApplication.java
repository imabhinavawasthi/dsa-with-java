package HotelManagementOOPSProject;
/**
 * HotelApplication - UI Layer / Main Application Class
 * Handles user interaction and menu-driven interface for the Hotel Management System
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class HotelApplication {
    private HotelManager hotelManager;
    private Scanner scanner;
    private int nextGuestId = 1000; // Counter for generating unique guest IDs

    /**
     * Constructor to initialize the application
     */
    public HotelApplication() {
        hotelManager = new HotelManager();
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu
     */
    private void displayMenu() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║   HOTEL MANAGEMENT SYSTEM       ║");
        System.out.println("╚════════════════════════════════╝");
        System.out.println("1. View Available Rooms");
        System.out.println("2. Book a Room");
        System.out.println("3. Checkout");
        System.out.println("4. View All Rooms Status");
        System.out.println("5. Exit");
        System.out.print("\nEnter your choice: ");
    }

    /**
     * Handles viewing available rooms
     */
    private void handleViewRooms() {
        hotelManager.displayAvailableRooms();
        System.out.println("Available: " + hotelManager.getAvailableRoomsCount() +
                " out of " + hotelManager.getTotalRooms() + " rooms");
    }

    /**
     * Handles booking a room with user input
     */
    private void handleBookRoom() {
        try {
            System.out.println("\n===== ROOM BOOKING =====");
            hotelManager.displayAvailableRooms();

            System.out.print("Enter room number to book: ");
            int roomNo = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();

            System.out.print("Enter guest phone number: ");
            String phone = scanner.nextLine();

            // Create a new guest object
            Guest guest = new Guest(name, phone, nextGuestId++);

            // Try to book the room
            hotelManager.bookRoom(roomNo, guest);

        } catch (InputMismatchException e) {
            System.out.println("✗ Invalid input! Please enter a valid room number.");
            scanner.nextLine(); // Clear the invalid input
        } catch (RoomUnavailableException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Handles checkout of a guest from a room
     */
    private void handleCheckOut() {
        try {
            System.out.println("\n===== CHECKOUT =====");
            System.out.print("Enter room number to checkout: ");
            int roomNo = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            hotelManager.checkOut(roomNo);

        } catch (InputMismatchException e) {
            System.out.println("✗ Invalid input! Please enter a valid room number.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    /**
     * Handles viewing all rooms with their status
     */
    private void handleViewAllRooms() {
        hotelManager.displayAllRooms();
    }

    /**
     * Displays a welcome message
     */
    private void displayWelcome(String name) {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║      Welcome " + name + " to        ║");
        System.out.println("║         Hotel PW               ║");
        System.out.println("╚════════════════════════════════╝\n");
    }

    /**
     * Main application loop
     */
    public void start() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        displayWelcome(name);

        while (true) {
            try {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        handleViewRooms();
                        break;
                    case 2:
                        handleBookRoom();
                        break;
                    case 3:
                        handleCheckOut();
                        break;
                    case 4:
                        handleViewAllRooms();
                        break;
                    case 5:
                        System.out.println("\n╔════════════════════════════════╗");
                        System.out.println("║  Thank you for using our system ║");
                        System.out.println("║           Goodbye!              ║");
                        System.out.println("╚════════════════════════════════╝\n");
                        return; // Exit the application
                    default:
                        System.out.println("✗ Invalid choice! Please enter a number between 1 and 5.");
                }

            } catch (InputMismatchException e) {
                System.out.println("✗ Invalid input! Please enter a valid number (1-5).");
                scanner.nextLine(); // Clear the invalid input from the buffer
            } catch (Exception e) {
                System.out.println("✗ An unexpected error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear any remaining input
            }
        }
    }

    /**
     * Main method - Entry point of the application
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        HotelApplication app = new HotelApplication();
        app.start();
    }
}

