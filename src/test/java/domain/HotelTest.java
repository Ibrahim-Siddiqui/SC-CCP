package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Hotel.
 */
class HotelTest {
    
    private Hotel hotel;
    private Name hotelName;
    private RoomType roomType;
    
    @BeforeEach
    void setUp() {
        hotelName = new Name("Grand", "Hotel");
        hotel = new Hotel(hotelName);
        roomType = new RoomType(RoomKind.SINGLE, new Money(100.0, "USD"));
    }
    
    // ===== Constructor Tests =====
    @Test
    void testConstructorWithValidName() {
        assertNotNull(hotel);
        assertEquals("Grand Hotel", hotel.getNameAsString());
    }
    
    @Test
    void testConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Hotel(null));
    }
    
    // ===== Add Room Tests =====
    @Test
    void testAddRoomSuccess() {
        // Arrange
        Room room = new Room(101, roomType);
        
        // Act
        hotel.addRoom(101, room);
        
        // Assert
        assertEquals(room, hotel.getRoom(101));
        assertEquals(1, hotel.getRoomCount());
    }
    
    @Test
    void testAddNullRoom() {
        assertThrows(IllegalArgumentException.class, () -> hotel.addRoom(101, null));
    }
    
    @Test
    void testAddDuplicateRoom() {
        // Arrange
        Room room1 = new Room(101, roomType);
        Room room2 = new Room(101, roomType);
        hotel.addRoom(101, room1);
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> hotel.addRoom(101, room2));
    }
    
    // ===== Room Count Tests =====
    @Test
    void testGetRoomCountEmpty() {
        assertEquals(0, hotel.getRoomCount());
    }
    
    @Test
    void testGetRoomCountMultiple() {
        // Arrange
        hotel.addRoom(101, new Room(101, roomType));
        hotel.addRoom(102, new Room(102, roomType));
        
        // Assert
        assertEquals(2, hotel.getRoomCount());
    }
    
    // ===== Availability Tests =====
    @Test
    void testIsAvailableRoomExists() {
        // Arrange
        Room room = new Room(101, roomType);
        hotel.addRoom(101, room);
        
        // Act & Assert
        assertTrue(hotel.isAvailable(101));
    }
    
    @Test
    void testIsAvailableRoomDoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> hotel.isAvailable(999));
    }
    
    @Test
    void testGetAvailableRoomCount() {
        // Arrange
        Room room1 = new Room(101, roomType);
        Room room2 = new Room(102, roomType);
        hotel.addRoom(101, room1);
        hotel.addRoom(102, room2);
        
        // Act & Assert
        assertEquals(2, hotel.getAvailableRoomCount());
    }
}
