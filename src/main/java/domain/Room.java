package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Room represents a single room in a hotel.
 * It manages its own state: room type, occupants, and reservations.
 * 
 * Responsibilities:
 * - Store room number and room type
 * - Track current occupant (0..1 multiplicity)
 * - Manage reservations (0..1 multiplicity)
 * - Control check-in and check-out operations
 * - Determine availability
 */
public class Room {
    
    private int number;
    private RoomType roomType;
    // Current occupant: at most one guest at a time (0..1 multiplicity)
    private Guest occupant;
    // Reservations for this room: maps guest ID to reservation
    private Map<String, Reservation> reservations;
    // Track which guest is checked in
    private String checkedInGuestId;
    
    /**
     * Constructor: Creates a new Room with number and room type.
     * 
     * @param number the room number
     * @param roomType the type of room (e.g., single, double, suite)
     * @throws IllegalArgumentException if number is non-positive or roomType is null
     */
    public Room(int number, RoomType roomType) {
        if (number <= 0) {
            throw new IllegalArgumentException("Room number must be positive");
        }
        if (roomType == null) {
            throw new IllegalArgumentException("RoomType cannot be null");
        }
        this.number = number;
        this.roomType = roomType;
        this.reservations = new HashMap<>();
        this.occupant = null;
        this.checkedInGuestId = null;
    }
    
    /**
     * Get the room number.
     * 
     * @return the room number
     */
    public int getNumber() {
        return number;
    }
    
    /**
     * Get the room type.
     * 
     * @return the room type
     */
    public RoomType getRoomType() {
        return roomType;
    }
    
    /**
     * Get the current occupant (guest checked in).
     * 
     * @return the current occupant, or null if room is empty
     */
    public Guest getOccupant() {
        return occupant;
    }
    
    /**
     * Check if the room is currently occupied.
     * 
     * @return true if a guest is checked in, false otherwise
     */
    public boolean isOccupied() {
        return occupant != null;
    }
    
    /**
     * Check if the room is available (not occupied and has no active reservations).
     * 
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return !isOccupied() && reservations.isEmpty();
    }
    
    /**
     * Get the number of reservations for this room.
     * 
     * @return number of reservations
     */
    public int getReservationCount() {
        return reservations.size();
    }
    
    /**
     * Add a reservation to this room.
     * A reservation can only be added if the room is not occupied.
     * 
     * @param reservation the reservation to add
     * @param guest the guest making the reservation
     * @throws IllegalArgumentException if reservation or guest is null
     * @throws IllegalStateException if room is already occupied
     */
    public void addReservation(Reservation reservation, Guest guest) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null");
        }
        if (guest == null) {
            throw new IllegalArgumentException("Guest cannot be null");
        }
        
        // Prevent reservation if room is occupied
        if (isOccupied()) {
            throw new IllegalStateException("Cannot add reservation: room " + number + " is currently occupied");
        }
        
        String guestId = guest.getName().getFullName();
        reservations.put(guestId, reservation);
    }
    
    /**
     * Remove a reservation from this room.
     * 
     * @param guestId the identifier of the guest whose reservation is to be removed
     * @throws IllegalArgumentException if guestId is null/empty or reservation doesn't exist
     */
    public void removeReservation(String guestId) {
        if (guestId == null || guestId.trim().isEmpty()) {
            throw new IllegalArgumentException("Guest ID cannot be null or empty");
        }
        
        if (!reservations.containsKey(guestId)) {
            throw new IllegalArgumentException("No reservation found for guest '" + guestId + "' in room " + number);
        }
        
        reservations.remove(guestId);
    }
    
    /**
     * Check in a guest to this room.
     * The guest must have an existing reservation.
     * 
     * @param guestId the identifier of the guest checking in
     * @throws IllegalArgumentException if guestId is null/empty
     * @throws IllegalStateException if room is already occupied or no reservation exists
     */
    public void checkInGuest(String guestId) {
        if (guestId == null || guestId.trim().isEmpty()) {
            throw new IllegalArgumentException("Guest ID cannot be null or empty");
        }
        
        // Prevent double check-in
        if (isOccupied()) {
            throw new IllegalStateException("Cannot check in: room " + number + " is already occupied");
        }
        
        // Check if reservation exists
        if (!reservations.containsKey(guestId)) {
            throw new IllegalStateException("No reservation found for guest '" + guestId + "' in room " + number);
        }
        
        // Mark the guest as checked in
        this.checkedInGuestId = guestId;
    }
    
    /**
     * Perform actual check-in by setting the occupant.
     * (Called after checkInGuest validation)
     * 
     * @param guest the guest checking in
     * @throws IllegalArgumentException if guest is null
     * @throws IllegalStateException if room is already occupied
     */
    public void setOccupant(Guest guest) {
        if (guest == null) {
            throw new IllegalArgumentException("Guest cannot be null");
        }
        if (isOccupied()) {
            throw new IllegalStateException("Room " + number + " is already occupied");
        }
        this.occupant = guest;
    }
    
    /**
     * Check out a guest from this room.
     * 
     * @param guestId the identifier of the guest checking out
     * @throws IllegalArgumentException if guestId is null/empty
     * @throws IllegalStateException if no guest is checked in or guest ID doesn't match
     */
    public void checkOutGuest(String guestId) {
        if (guestId == null || guestId.trim().isEmpty()) {
            throw new IllegalArgumentException("Guest ID cannot be null or empty");
        }
        
        if (!isOccupied()) {
            throw new IllegalStateException("Room " + number + " has no occupant to check out");
        }
        
        String currentGuestName = occupant.getName().getFullName();
        if (!currentGuestName.equals(guestId)) {
            throw new IllegalStateException("Guest ID mismatch: expected '" + currentGuestName + 
                                          "' but got '" + guestId + "'");
        }
        
        this.occupant = null;
        this.checkedInGuestId = null;
        // Remove the reservation after check-out
        if (reservations.containsKey(guestId)) {
            reservations.remove(guestId);
        }
    }
    
    /**
     * Get the ID of the currently checked-in guest.
     * 
     * @return the guest ID, or null if no one is checked in
     */
    public String getCheckedInGuestId() {
        return checkedInGuestId;
    }
}
