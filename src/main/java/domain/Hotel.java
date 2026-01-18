package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Hotel represents a single hotel within a hotel chain.
 * It manages all rooms and their reservations.
 * 
 * Responsibilities:
 * - Store and manage rooms (using room number as key)
 * - Create reservations for rooms
 * - Check room availability
 * - Coordinate with rooms for check-in/check-out operations
 */
public class Hotel {
    
    private Name name;
    // Map to store rooms by their room number (qualified association from UML)
    private Map<Integer, Room> rooms;
    
    /**
     * Constructor: Creates a new Hotel with the given name.
     * 
     * @param name the name of the hotel
     * @throws IllegalArgumentException if name is null
     */
    public Hotel(Name name) {
        if (name == null) {
            throw new IllegalArgumentException("Hotel name cannot be null");
        }
        this.name = name;
        this.rooms = new HashMap<>();
    }

    /**
     * Get the name of this hotel.
     * 
     * @return the hotel name
     */
    public Name getName() {
        return name;
    }
    
    /**
     * Get the string representation of the hotel name.
     * 
     * @return the hotel name as string
     */
    public String getNameAsString() {
        return name.getFullName();
    }
    
    /**
     * Add a room to this hotel.
     * Each room is stored using its room number as the key.
     * 
     * @param roomNumber the room number (unique identifier)
     * @param room the room to add
     * @throws IllegalArgumentException if room is null or room number already exists
     */
    public void addRoom(int roomNumber, Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (rooms.containsKey(roomNumber)) {
            throw new IllegalArgumentException("Room " + roomNumber + " already exists in this hotel");
        }
        rooms.put(roomNumber, room);
    }
    
    /**
     * Get a room by its room number.
     * 
     * @param roomNumber the room number
     * @return the room, or null if not found
     */
    public Room getRoom(int roomNumber) {
        return rooms.get(roomNumber);
    }
    
    /**
     * Get the total number of rooms in this hotel.
     * 
     * @return number of rooms
     */
    public int getRoomCount() {
        return rooms.size();
    }
    
    /**
     * Create a reservation for a guest in a specific room.
     * This method delegates to the room itself.
     * 
     * @param roomNumber the room number
     * @param guest the guest making the reservation
     * @param reservation the reservation details
     * @throws IllegalArgumentException if parameters are invalid
     * @throws IllegalStateException if room is not available
     */
    public void createReservation(int roomNumber, Guest guest, Reservation reservation) {
        if (guest == null) {
            throw new IllegalArgumentException("Guest cannot be null");
        }
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null");
        }
        
        Room room = getRoom(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room " + roomNumber + " does not exist in this hotel");
        }
        
        room.addReservation(reservation, guest);
    }
    
    /**
     * Check if a specific room is available (has no occupants and no active reservations).
     * 
     * @param roomNumber the room number
     * @return true if the room is available, false otherwise
     * @throws IllegalArgumentException if room number does not exist
     */
    public boolean isAvailable(int roomNumber) {
        Room room = getRoom(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room " + roomNumber + " does not exist in this hotel");
        }
        return room.isAvailable();
    }
    
    /**
     * Get the number of available rooms in the hotel.
     * 
     * @return count of available rooms
     */
    public int getAvailableRoomCount() {
        int count = 0;
        for (Room room : rooms.values()) {
            if (room.isAvailable()) {
                count++;
            }
        }
        return count;
    }
}
