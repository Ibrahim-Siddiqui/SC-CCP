package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        HowMany guests = new HowMany(2);
        
        // Act
        Reservation res = new Reservation(today, start, end, guests);
        
        // Assert
        assertNotNull(res);
        assertEquals(today, res.getReservationDate());
        assertEquals(start, res.getStartDate());
        assertEquals(end, res.getEndDate());
    }
    
    @Test
    void testConstructorWithNullReservationDate() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> new Reservation(null, today.plusDays(1), today.plusDays(3), new HowMany(1)));
    }
    
    @Test
    void testConstructorWithNullStartDate() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Reservation(today, null, today.plusDays(3), new HowMany(1)));
    }
    
    @Test
    void testConstructorWithNullEndDate() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Reservation(today, today.plusDays(1), null, new HowMany(1)));
    }
    
    @Test
    void testConstructorWithNullNumberOfGuests() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Reservation(today, today.plusDays(1), today.plusDays(3), null));
    }
    
    @Test
    void testConstructorWithStartBeforeReservationDate() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Reservation(today, today.minusDays(1), today.plusDays(3), new HowMany(1)));
    }
    
    @Test
    void testConstructorWithEndBeforeStart() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Reservation(today, today.plusDays(3), today.plusDays(1), new HowMany(1)));
    }
    
    @Test
    void testConstructorWithEndEqualToStart() {
        assertThrows(IllegalArgumentException.class, 
            () -> new Reservation(today, today.plusDays(1), today.plusDays(1), new HowMany(1)));
    }
    
    // ===== Number of Nights Tests =====
    @Test
    void testGetNumberOfNights() {
        // Arrange
        java.time.LocalDate start = today.plusDays(1);
        java.time.LocalDate end = today.plusDays(4);
        Reservation res = new Reservation(today, start, end, new HowMany(1));
        
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
        Reservation res = new Reservation(today, start, end, new HowMany(1));
        
        // Act
        int nights = res.getNumberOfNights();
        
        // Assert
        assertEquals(1, nights);
    }
}
