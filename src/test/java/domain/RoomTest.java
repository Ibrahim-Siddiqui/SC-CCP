package test.java.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.Address;
import domain.Guest;
import domain.HowMany;
import domain.Money;
import domain.Name;
import domain.Reservation;
import domain.Room;
import domain.RoomKind;
import domain.RoomType;

import static org.junit.jupiter.api.Assertions.*;



/**
 * Test class for Room.
 */
class RoomTest {
    
    private Room room;
    private RoomType roomType;
    private Guest guest;
    private Reservation reservation;
    
    @BeforeEach
    void setUp() {
        roomType = new RoomType(RoomKind.DOUBLE, new Money(150.0, "USD"));
        room = new Room(101, roomType);
        guest = new Guest(
            new Name("Jane", "Smith"),
            new Address("456 Oak Ave", "City", "Country", "54321")
        );
        reservation = new Reservation(
            java.time.LocalDate.now().plusDays(1),
            java.time.LocalDate.now().plusDays(3),
            new HowMany(1)
        );
    }
    
    // ===== Constructor Tests =====
    @Test
    void testConstructorWithValidData() {
        assertNotNull(room);
        assertEquals(101, room.getNumber());
        assertEquals(roomType, room.getRoomType());
    }
    
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    void testConstructorWithInvalidRoomNumber(int invalidNumber) {
        assertThrows(IllegalArgumentException.class, () -> new Room(invalidNumber, roomType));
    }
    
    @Test
    void testConstructorWithNullRoomType() {
        assertThrows(IllegalArgumentException.class, () -> new Room(101, null));
    }
    
    // ===== Occupancy Tests =====
    @Test
    void testIsOccupiedInitially() {
        assertFalse(room.isOccupied());
    }
    
    @Test
    void testIsOccupiedAfterCheckIn() {
        // Arrange
        room.setOccupant(guest);
        
        // Act & Assert
        assertTrue(room.isOccupied());
    }
    
    @Test
    void testGetOccupant() {
        // Arrange
        room.setOccupant(guest);
        
        // Act & Assert
        assertEquals(guest, room.getOccupant());
    }
    
    // ===== Availability Tests =====
    @Test
    void testIsAvailableInitially() {
        assertTrue(room.isAvailable());
    }
    
    @Test
    void testIsAvailableAfterReservation() {
        // Arrange
        room.addReservation(reservation, guest);
        
        // Act & Assert
        assertFalse(room.isAvailable());
    }
    
    @Test
    void testIsAvailableAfterOccupancy() {
        // Arrange
        room.setOccupant(guest);
        
        // Act & Assert
        assertFalse(room.isAvailable());
    }
    
    // ===== Reservation Tests =====
    @Test
    void testAddReservationSuccess() {
        // Act
        room.addReservation(reservation, guest);
        
        // Assert
        assertEquals(1, room.getReservationCount());
    }
    
    @Test
    void testAddReservationWithNullReservation() {
        assertThrows(IllegalArgumentException.class, () -> room.addReservation(null, guest));
    }
    
    @Test
    void testAddReservationWithNullGuest() {
        assertThrows(IllegalArgumentException.class, () -> room.addReservation(reservation, null));
    }
    
    @Test
    void testAddReservationWhenOccupied() {
        // Arrange
        room.setOccupant(guest);
        
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> room.addReservation(reservation, guest));
    }
    
    @Test
    void testRemoveReservationSuccess() {
        // Arrange
        room.addReservation(reservation, guest);
        
        // Act
        room.removeReservation("Jane Smith");
        
        // Assert
        assertEquals(0, room.getReservationCount());
    }
    
    @Test
    void testRemoveReservationWithNullGuestId() {
        assertThrows(IllegalArgumentException.class, () -> room.removeReservation(null));
    }
    
    @Test
    void testRemoveNonExistentReservation() {
        assertThrows(IllegalArgumentException.class, () -> room.removeReservation("NonExistent"));
    }
    
    // ===== Check In Tests =====
    @Test
    void testSetOccupantSuccess() {
        // Act
        room.setOccupant(guest);
        
        // Assert
        assertTrue(room.isOccupied());
        assertEquals(guest, room.getOccupant());
    }
    
    @Test
    void testSetOccupantWhenAlreadyOccupied() {
        // Arrange
        room.setOccupant(guest);
        Guest anotherGuest = new Guest(
            new Name("Bob", "Johnson"),
            new Address("789 Pine St", "City", "Country", "11111")
        );
        
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> room.setOccupant(anotherGuest));
    }
    
    @Test
    void testSetOccupantWithNullGuest() {
        assertThrows(IllegalArgumentException.class, () -> room.setOccupant(null));
    }
    
    // ===== Check Out Tests =====
    @Test
    void testCheckOutGuestSuccess() {
        // Arrange
        room.addReservation(reservation, guest);
        room.setOccupant(guest);
        
        // Act
        room.checkOutGuest("Jane Smith");
        
        // Assert
        assertFalse(room.isOccupied());
        assertNull(room.getOccupant());
    }
    
    @Test
    void testCheckOutGuestWhenNoOccupant() {
        assertThrows(IllegalStateException.class, () -> room.checkOutGuest("Jane Smith"));
    }
    
    @Test
    void testCheckOutGuestWithWrongId() {
        // Arrange
        room.setOccupant(guest);
        
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> room.checkOutGuest("Wrong Name"));
    }
    
    @Test
    void testCheckOutGuestWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> room.checkOutGuest(null));
    }
}
