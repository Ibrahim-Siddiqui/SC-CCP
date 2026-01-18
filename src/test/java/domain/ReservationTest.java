package test.java.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.Reservation;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Reservation.
 */
class ReservationTest {
    
    private java.time.LocalDate today;
    
    @BeforeEach
    void setUp() {
        today = java.time.LocalDate.now();
    }
    
    // ===== Constructor Tests =====
    @Test
    void testConstructorWithValidData() {
        // Arrange
        java.time.LocalDate start = today.plusDays(1);
        java.time.LocalDate end = today.plusDays(3);
        HowMany guests = new HowMany(1);
        
        // Act
        Reservation res = new Reservation(start, end, guests);
        
        // Assert
        assertNotNull(res);
        assertEquals(start, res.getStartDate());
        assertEquals(end, res.getEndDate());
    }
    
    @Test
    void testConstructorWithNullStartDate() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Reservation(null, today.plusDays(1), new HowMany(1)));
    }
    
    @Test
    void testConstructorWithNullEndDate() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Reservation(today, null, new HowMany(1)));
    }
    
    @Test
    void testConstructorWithNullNumberOfGuests() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Reservation(today, today.plusDays(1), null));
    }
    
    @Test
    void testConstructorWithEndBeforeStart() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Reservation(today, today.plusDays(1), new HowMany(1)));
    }
    
    @Test
    void testConstructorWithEndEqualToStart() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Reservation(today, today.plusDays(1), new HowMany(1)));
    }
    
    // ===== Number of Nights Tests =====
    @Test
    void testGetNumberOfNights() {
        // Arrange
        java.time.LocalDate start = today.plusDays(1);
        java.time.LocalDate end = today.plusDays(4);
        Reservation res = new Reservation(start, end, new HowMany(1));
        
        // Act
        int nights = res.getNumberOfNights();
        
        // Assert
        assertEquals(3, nights);
    }
    
    @Test
    void testGetNumberOfNightsSingleNight() {
        // Arrange
        java.time.LocalDate start = today.plusDays(1);
        java.time.LocalDate end = today.plusDays(2);
        Reservation res = new Reservation(start, end, new HowMany(1));
        
        // Act
        int nights = res.getNumberOfNights();
        
        // Assert
        assertEquals(1, nights);
    }
}
