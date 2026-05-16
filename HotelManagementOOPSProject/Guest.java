package HotelManagementOOPSProject;

/**
 * Guest Entity Class
 * Represents a guest in the hotel management system
 */
public class Guest {
    private String name;
    private String phone;
    private int guestId;

    /**
     * Constructor to initialize a Guest object
     *
     * @param name    The name of the guest
     * @param phone   The phone number of the guest
     * @param guestId The unique ID of the guest
     */
    public Guest(String name, String phone, int guestId) {
        this.name = name;
        this.phone = phone;
        this.guestId = guestId;
    }

    /**
     * Gets the name of the guest
     *
     * @return The guest's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the guest
     *
     * @param name The guest's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the phone number of the guest
     *
     * @return The guest's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the guest
     *
     * @param phone The guest's phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the ID of the guest
     *
     * @return The guest's ID
     */
    public int getGuestId() {
        return guestId;
    }

    /**
     * Sets the ID of the guest
     *
     * @param guestId The guest's ID to set
     */
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    /**
     * Returns a string representation of the guest
     *
     * @return Guest information as a string
     */
    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", guestId=" + guestId +
                '}';
    }
}

