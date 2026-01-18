package test.java.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.Name;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Name value object.
 */
class NameTest {
    
    @Test
    void testConstructorWithValidNames() {
        // Act
        Name name = new Name("John", "Doe");
        
        // Assert
        assertEquals("John", name.getFirstName());
        assertEquals("Doe", name.getLastName());
        assertEquals("John Doe", name.getFullName());
    }
    
    @Test
    void testConstructorWithNullFirstName() {
        assertThrows(IllegalArgumentException.class, () -> new Name(null, "Doe"));
    }
    
    @Test
    void testConstructorWithNullLastName() {
        assertThrows(IllegalArgumentException.class, () -> new Name("John", null));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t"})
    void testConstructorWithBlankFirstName(String blank) {
        assertThrows(IllegalArgumentException.class, () -> new Name(blank, "Doe"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t"})
    void testConstructorWithBlankLastName(String blank) {
        assertThrows(IllegalArgumentException.class, () -> new Name("John", blank));
    }
    
    @Test
    void testNameTrimming() {
        // Act
        Name name = new Name("  John  ", "  Doe  ");
        
        // Assert
        assertEquals("John", name.getFirstName());
        assertEquals("Doe", name.getLastName());
    }
    
    @Test
    void testNameEquality() {
        // Act
        Name name1 = new Name("John", "Doe");
        Name name2 = new Name("john", "doe");
        
        // Assert (case-insensitive)
        assertEquals(name1, name2);
    }
    
    @Test
    void testNameInequality() {
        // Act
        Name name1 = new Name("John", "Doe");
        Name name2 = new Name("Jane", "Doe");
        
        // Assert
        assertNotEquals(name1, name2);
    }
    
    @Test
    void testNameHashCode() {
        // Act
        Name name1 = new Name("John", "Doe");
        Name name2 = new Name("john", "doe");
        
        // Assert
        assertEquals(name1.hashCode(), name2.hashCode());
    }
    
    @Test
    void testNameToString() {
        // Act
        Name name = new Name("John", "Doe");
        
        // Assert
        assertEquals("John Doe", name.toString());
    }
}
