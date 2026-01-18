package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Guest.
 */
class GuestTest {
    
    private Name guestName;
    private Address guestAddress;
    
    @Test
    void testConstructorWithValidData() {
        // Arrange
        guestName = new Name("Alice", "Brown");
        guestAddress = new Address("100 Main St", "New York", "USA", "10001");
        
        // Act
        Guest guest = new Guest(guestName, guestAddress);
        
        // Assert
        assertEquals(guestName, guest.getName());
        assertEquals(guestAddress, guest.getAddressDetails());
    }
    
    @Test
    void testConstructorWithNullName() {
        // Arrange
        guestAddress = new Address("100 Main St", "New York", "USA", "10001");
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Guest(null, guestAddress));
    }
    
    @Test
    void testConstructorWithNullAddress() {
        // Arrange
        guestName = new Name("Alice", "Brown");
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Guest(guestName, null));
    }
    
    @Test
    void testGetNameAsString() {
        // Arrange
        guestName = new Name("Alice", "Brown");
        guestAddress = new Address("100 Main St", "New York", "USA", "10001");
        Guest guest = new Guest(guestName, guestAddress);
        
        // Act
        String nameStr = guest.getNameAsString();
        
        // Assert
        assertEquals("Alice Brown", nameStr);
    }
    
    @Test
    void testGuestEquality() {
        // Arrange
        guestName = new Name("Alice", "Brown");
        guestAddress = new Address("100 Main St", "New York", "USA", "10001");
        Guest guest1 = new Guest(guestName, guestAddress);
        Guest guest2 = new Guest(guestName, guestAddress);
        
        // Act & Assert
        assertEquals(guest1, guest2);
    }
}
