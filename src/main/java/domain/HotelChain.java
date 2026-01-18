package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * HotelChain coordinates the entire hotel reservation system.
 * It manages all hotels and acts as the central coordinator for reservations.
 * 
 * Responsibilities:
 * - Store and manage multiple hotels
 * - Process reservations, cancellations, check-ins, and check-outs
 * - Validate operations before executing them
 */
public class HotelChain {
    
    private String name;
    // Map to store hotels by their name (hotels can have unique names)
    private Map<String, Hotel> hotels;
    
    /**
     * Constructor: Creates a new HotelChain with the given name.
     * 
     * @param name the name of the hotel chain
     * @throws IllegalArgumentException if name is null or empty
     */
    public HotelChain(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("HotelChain name cannot be null or empty");
        }
        this.name = name;
        this.hotels = new HashMap<>();
    }
    
    /**
     * Get the name of this hotel chain.
     * 
     * @return the chain name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Add a hotel to this chain.
     * 
     * @param hotel the hotel to add
     * @throws IllegalArgumentException if hotel is null
     * @throws IllegalStateException if a hotel with the same name already exists
     */
    public void addHotel(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null");
        }
        String hotelName = hotel.getNameAsString();
        if (hotels.containsKey(hotelName)) {
            throw new IllegalStateException("Hotel with name '" + hotelName + "' already exists in this chain");
        }
        hotels.put(hotelName, hotel);
    }
    
    /**
     * Get a hotel by name.
     * 
     * @param hotelName the name of the hotel
     * @return the hotel, or null if not found
     */
    public Hotel getHotel(String hotelName) {
        return hotels.get(hotelName);
    }
    
    /**
     * Get the number of hotels in this chain.
     * 
     * @return number of hotels
     */
    public int getHotelCount() {
        return hotels.size();
    }
    
    /**
     * Make a reservation for a guest in a specific hotel room.
     * 
     * Validation Steps:
     * 1. Check if all parameters are valid
     * 2. Check if the operation is allowed
     * 3. Execute the reservation
     * 
     * @param hotelName the name of the hotel
     * @param roomNumber the room number
     * @param guest the guest making the reservation
     * @param reserverPayer the person paying for the reservation
     * @param reservation the reservation details
     * @throws IllegalArgumentException if parameters are invalid
     * @throws IllegalStateException if operation cannot be performed
     */
    public void makeReservation(String hotelName, int roomNumber, Guest guest, 
                               ReserverPayer reserverPayer, Reservation reservation) {
        // Validate parameters
        if (!canMakeReservation(hotelName, roomNumber, guest, reserverPayer, reservation)) {
            throw new IllegalStateException("Reservation cannot be made: validation failed");
        }
        
        // Execute reservation
        Hotel hotel = hotels.get(hotelName);
        Room room = hotel.getRoom(roomNumber);
        room.addReservation(reservation, guest);
    }
    
    /**
     * Validation method: Check if reservation can be made.
     * 
     * @param hotelName the hotel name
     * @param roomNumber the room number
     * @param guest the guest
     * @param reserverPayer the payer
     * @param reservation the reservation
     * @return true if reservation can be made, false otherwise
     */
    private boolean canMakeReservation(String hotelName, int roomNumber, Guest guest,
                                      ReserverPayer reserverPayer, Reservation reservation) {
        // Check if hotel exists
        if (!hotels.containsKey(hotelName)) {
            throw new IllegalArgumentException("Hotel '" + hotelName + "' does not exist");
        }
        
        // Check if parameters are not null
        if (guest == null) {
            throw new IllegalArgumentException("Guest cannot be null");
        }
        if (reserverPayer == null) {
            throw new IllegalArgumentException("ReserverPayer cannot be null");
        }
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null");
        }
        
        // Check if room exists and is available
        Hotel hotel = hotels.get(hotelName);
        Room room = hotel.getRoom(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room " + roomNumber + " does not exist in hotel '" + hotelName + "'");
        }
        
        return true;
    }
    
    /**
     * Cancel an existing reservation.
     * 
     * @param hotelName the name of the hotel
     * @param roomNumber the room number
     * @param reservationId a unique identifier for the reservation
     * @throws IllegalStateException if reservation cannot be cancelled
     */
    public void cancelReservation(String hotelName, int roomNumber, String reservationId) {
        if (!canCancelReservation(hotelName, roomNumber, reservationId)) {
            throw new IllegalStateException("Reservation cannot be cancelled: validation failed");
        }
        
        Hotel hotel = hotels.get(hotelName);
        Room room = hotel.getRoom(roomNumber);
        room.removeReservation(reservationId);
    }
    
    /**
     * Validation method: Check if reservation can be cancelled.
     * 
     * @param hotelName the hotel name
     * @param roomNumber the room number
     * @param reservationId the reservation identifier
     * @return true if cancellation is allowed, false otherwise
     */
    private boolean canCancelReservation(String hotelName, int roomNumber, String reservationId) {
        if (!hotels.containsKey(hotelName)) {
            throw new IllegalArgumentException("Hotel '" + hotelName + "' does not exist");
        }
        if (reservationId == null || reservationId.trim().isEmpty()) {
            throw new IllegalArgumentException("Reservation ID cannot be null or empty");
        }
        
        Hotel hotel = hotels.get(hotelName);
        Room room = hotel.getRoom(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room " + roomNumber + " does not exist");
        }
        
        return true;
    }
    
    /**
     * Check in a guest to their reserved room.
     * 
     * @param hotelName the name of the hotel
     * @param roomNumber the room number
     * @param guestId a unique identifier for the guest
     * @throws IllegalStateException if check-in cannot be performed
     */
    public void checkInGuest(String hotelName, int roomNumber, String guestId) {
        if (!canCheckInGuest(hotelName, roomNumber, guestId)) {
            throw new IllegalStateException("Check-in cannot be performed: validation failed");
        }
        
        Hotel hotel = hotels.get(hotelName);
        Room room = hotel.getRoom(roomNumber);
        room.checkInGuest(guestId);
    }
    
    /**
     * Validation method: Check if guest can check in.
     * 
     * @param hotelName the hotel name
     * @param roomNumber the room number
     * @param guestId the guest identifier
     * @return true if check-in is allowed, false otherwise
     */
    private boolean canCheckInGuest(String hotelName, int roomNumber, String guestId) {
        if (!hotels.containsKey(hotelName)) {
            throw new IllegalArgumentException("Hotel '" + hotelName + "' does not exist");
        }
        if (guestId == null || guestId.trim().isEmpty()) {
            throw new IllegalArgumentException("Guest ID cannot be null or empty");
        }
        
        Hotel hotel = hotels.get(hotelName);
        Room room = hotel.getRoom(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room " + roomNumber + " does not exist");
        }
        
        return true;
    }
    
    /**
     * Check out a guest from their room.
     * 
     * @param hotelName the name of the hotel
     * @param roomNumber the room number
     * @param guestId a unique identifier for the guest
     * @throws IllegalStateException if check-out cannot be performed
     */
    public void checkOutGuest(String hotelName, int roomNumber, String guestId) {
        if (!canCheckOutGuest(hotelName, roomNumber, guestId)) {
            throw new IllegalStateException("Check-out cannot be performed: validation failed");
        }
        
        Hotel hotel = hotels.get(hotelName);
        Room room = hotel.getRoom(roomNumber);
        room.checkOutGuest(guestId);
    }
    
    /**
     * Validation method: Check if guest can check out.
     * 
     * @param hotelName the hotel name
     * @param roomNumber the room number
     * @param guestId the guest identifier
     * @return true if check-out is allowed, false otherwise
     */
    private boolean canCheckOutGuest(String hotelName, int roomNumber, String guestId) {
        if (!hotels.containsKey(hotelName)) {
            throw new IllegalArgumentException("Hotel '" + hotelName + "' does not exist");
        }
        if (guestId == null || guestId.trim().isEmpty()) {
            throw new IllegalArgumentException("Guest ID cannot be null or empty");
        }
        
        Hotel hotel = hotels.get(hotelName);
        Room room = hotel.getRoom(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room " + roomNumber + " does not exist");
        }
        
        return true;
    }
}
