package domain;

/**
 * RoomKind is an enumeration of room types available in the hotel.
 * 
 * Values:
 * - SINGLE: Single bed room
 * - DOUBLE: Double bed room
 * - SUITE: Suite room
 */
public enum RoomKind {
    SINGLE("Single Bed"),
    DOUBLE("Double Bed"),
    SUITE("Suite");
    
    private String description;
    
    /**
     * Constructor for RoomKind enum.
     * 
     * @param description human-readable description
     */
    RoomKind(String description) {
        this.description = description;
    }
    
    /**
     * Get the description of this room kind.
     * 
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }
}
