package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for HotelChain.
 * Tests all operations: creating chain, adding hotels, making/canceling reservations,
 * checking in/out guests.
 */
class HotelChainTest {
    
    private HotelChain chain;
    private Hotel hotel;
    private Room room;
    private RoomType roomType;
    private Guest guest;
    private ReserverPayer payer;
    private Reservation reservation;
    
    @BeforeEach
    void setUp() {
        // Arrange: Create all necessary objects
        chain = new HotelChain("Test Hotel Chain");
        hotel = new Hotel(new Name("Test", "Hotel"));
        roomType = new RoomType(RoomKind.SINGLE, new Money(100.0, "USD"));
        room = new Room(101, roomType);
        hotel.addRoom(101, room);
        chain.addHotel(hotel);
        
        guest = new Guest(
            new Name("John", "Doe"),
            new Address("123 Main St", "City", "Country", "12345")
        );
        payer = new ReserverPayer(
            new CreditCard("1234567890123456", "John Doe", "12/25"),
            new Identity("ID123", "Passport")
        );
        reservation = new Reservation(
            java.time.LocalDate.now(),
            java.time.LocalDate.now().plusDays(1),
            java.time.LocalDate.now().plusDays(3),
            new HowMany(1)
        );
    }
    
    // ===== HotelChain Constructor Tests =====
    @Test
    void testConstructorWithValidName() {
        // Act & Assert
        assertNotNull(chain);
        assertEquals("Test Hotel Chain", chain.getName());
    }
    
    @Test
    void testConstructorWithNullName() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new HotelChain(null));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t"})
    void testConstructorWithBlankName(String blankName) {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new HotelChain(blankName));
    }
    
    // ===== Add Hotel Tests =====
    @Test
    void testAddHotelWithValidHotel() {
        // Arrange
        HotelChain newChain = new HotelChain("New Chain");
        Hotel newHotel = new Hotel(new Name("Another", "Hotel"));
        
        // Act
        newChain.addHotel(newHotel);
        
        // Assert
        assertEquals(newHotel, newChain.getHotel("Another Hotel"));
    }
    
    @Test
    void testAddHotelWithNullHotel() {
        // Arrange
        HotelChain newChain = new HotelChain("New Chain");
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> newChain.addHotel(null));
    }
    
    @Test
    void testAddDuplicateHotel() {
        // Arrange
        HotelChain newChain = new HotelChain("New Chain");
        Hotel hotel1 = new Hotel(new Name("Same", "Name"));
        newChain.addHotel(hotel1);
        
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> newChain.addHotel(hotel1));
    }
    
    @Test
    void testGetHotelCount() {
        // Act
        int count = chain.getHotelCount();
        
        // Assert
        assertEquals(1, count);
    }
    
    // ===== Make Reservation Tests =====
    @Test
    void testMakeReservationSuccess() {
        // Act
        chain.makeReservation("Test Hotel", 101, guest, payer, reservation);
        
        // Assert
        assertEquals(1, room.getReservationCount());
    }
    
    @Test
    void testMakeReservationWithNullGuest() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.makeReservation("Test Hotel", 101, null, payer, reservation));
    }
    
    @Test
    void testMakeReservationWithNullPayer() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.makeReservation("Test Hotel", 101, guest, null, reservation));
    }
    
    @Test
    void testMakeReservationWithNullReservation() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.makeReservation("Test Hotel", 101, guest, payer, null));
    }
    
    @Test
    void testMakeReservationWithNonExistentHotel() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.makeReservation("Non Existent Hotel", 101, guest, payer, reservation));
    }
    
    @Test
    void testMakeReservationWithNonExistentRoom() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.makeReservation("Test Hotel", 999, guest, payer, reservation));
    }
    
    // ===== Cancel Reservation Tests =====
    @Test
    void testCancelReservationSuccess() {
        // Arrange
        chain.makeReservation("Test Hotel", 101, guest, payer, reservation);
        
        // Act
        chain.cancelReservation("Test Hotel", 101, "John Doe");
        
        // Assert
        assertEquals(0, room.getReservationCount());
    }
    
    @Test
    void testCancelReservationWithNullReservationId() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.cancelReservation("Test Hotel", 101, null));
    }
    
    @Test
    void testCancelReservationWithEmptyReservationId() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.cancelReservation("Test Hotel", 101, ""));
    }
    
    @Test
    void testCancelReservationWithNonExistentHotel() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.cancelReservation("Non Existent", 101, "John Doe"));
    }
    
    // ===== Check In Tests =====
    @Test
    void testCheckInGuestSuccess() {
        // Arrange
        chain.makeReservation("Test Hotel", 101, guest, payer, reservation);
        room.setOccupant(guest);
        
        // Act
        chain.checkInGuest("Test Hotel", 101, "John Doe");
        
        // Assert
        assertTrue(room.isOccupied());
    }
    
    @Test
    void testCheckInGuestWithNullGuestId() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.checkInGuest("Test Hotel", 101, null));
    }
    
    @Test
    void testCheckInGuestWithEmptyGuestId() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.checkInGuest("Test Hotel", 101, ""));
    }
    
    @Test
    void testCheckInGuestWithNonExistentHotel() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.checkInGuest("Non Existent", 101, "John Doe"));
    }
    
    // ===== Check Out Tests =====
    @Test
    void testCheckOutGuestSuccess() {
        // Arrange
        chain.makeReservation("Test Hotel", 101, guest, payer, reservation);
        room.setOccupant(guest);
        chain.checkInGuest("Test Hotel", 101, "John Doe");
        
        // Act
        chain.checkOutGuest("Test Hotel", 101, "John Doe");
        
        // Assert
        assertFalse(room.isOccupied());
    }
    
    @Test
    void testCheckOutGuestWithNullGuestId() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.checkOutGuest("Test Hotel", 101, null));
    }
    
    @Test
    void testCheckOutGuestWithEmptyGuestId() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, 
            () -> chain.checkOutGuest("Test Hotel", 101, ""));
    }
}
