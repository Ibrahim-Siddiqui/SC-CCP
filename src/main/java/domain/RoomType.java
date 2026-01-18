package domain;

/**
 * RoomType represents the type and pricing information for a room.
 * It defines what kind of room it is and how much it costs.
 * 
 * Responsibilities:
 * - Store room kind (SINGLE, DOUBLE, SUITE)
 * - Store room cost (Money value object)
 * - Provide room type information
 */
public class RoomType {
    
    private RoomKind kind;
    private Money cost;
    
    /**
     * Constructor: Creates a RoomType with kind and cost.
     * 
     * @param kind the kind of room (SINGLE, DOUBLE, SUITE)
     * @param cost the cost of the room
     * @throws IllegalArgumentException if kind or cost is null
     */
    public RoomType(RoomKind kind, Money cost) {
        if (kind == null) {
            throw new IllegalArgumentException("RoomKind cannot be null");
        }
        if (cost == null) {
            throw new IllegalArgumentException("Cost cannot be null");
        }
        this.kind = kind;
        this.cost = cost;
    }
    
    /**
     * Get the room kind.
     * 
     * @return the room kind
     */
    public RoomKind getKind() {
        return kind;
    }
    
    /**
     * Get the cost of this room type.
     * 
     * @return the cost
     */
    public Money getCost() {
        return cost;
    }
    
    /**
     * Get the cost amount as a double.
     * 
     * @return the cost amount
     */
    public double getCostAmount() {
        return cost.getAmount();
    }
    
    /**
     * Get string representation of this room type.
     * 
     * @return string in format "KindDescription (Cost)"
     */
    @Override
    public String toString() {
        return kind.getDescription() + " (" + cost + ")";
    }
}
