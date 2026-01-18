package domain;

import java.time.LocalDate;

/**
 * Reservation represents a booking for a guest in a room.
 * It stores the dates and other relevant information about the reservation.
 * 
 * Responsibilities:
 * - Store reservation dates (reservation date, start date, end date)
 * - Store guest count (number of occupants)
 * - Provide reservation information
 */
public class Reservation {
    
    private LocalDate reservationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private HowMany numberOfGuests;
    
    /**
     * Constructor: Creates a Reservation with all required information.
     * 
     * @param reservationDate the date when the reservation was made
     * @param startDate the check-in date
     * @param endDate the check-out date
     * @param guests the number of guests
     * @throws IllegalArgumentException if any date is null, numberOfGuests is null,
     *                                  or dates are in invalid order
     */
    public Reservation(LocalDate startDate, LocalDate endDate, HowMany guests) {
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
        if (guests == null) {
            throw new IllegalArgumentException("Number of guests cannot be null");
        }
        
        // Validate date order
        if (endDate.isBefore(startDate) || endDate.equals(startDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuests = guests;
    }
    
    /**
     * Get the reservation date (when booking was made).
     * 
     * @return the reservation date
     */
    public LocalDate getReservationDate() {
        return reservationDate;
    }
    
    /**
     * Get the start date (check-in date).
     * 
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    
    /**
     * Get the end date (check-out date).
     * 
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }
    
    /**
     * Get the number of guests for this reservation.
     * 
     * @return the number of guests
     */
    public HowMany getNumberOfGuests() {
        return numberOfGuests;
    }
    
    /**
     * Get the number of nights for this reservation.
     * 
     * @return the number of nights
     */
    public int getNumberOfNights() {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }
    
    /**
     * Check if this reservation is currently active (between start and end dates).
     * 
     * @return true if reservation is active, false otherwise
     */
    public boolean isActive() {
        LocalDate today = LocalDate.now();
        return !today.isBefore(startDate) && today.isBefore(endDate);
    }
    
    /**
     * Get string representation of this reservation.
     * 
     * @return string with reservation details
     */
    @Override
    public String toString() {
        return "Reservation from " + startDate + " to " + endDate + 
               " for " + numberOfGuests.getNumber() + " guest(s)";
    }
}
