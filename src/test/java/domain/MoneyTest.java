package test.java.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.Money;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Money value object.
 */
class MoneyTest {
    
    @Test
    void testConstructorWithValidData() {
        // Act
        Money money = new Money(100.0, "USD");
        
        // Assert
        assertEquals(100.0, money.getAmount());
        assertEquals("USD", money.getCurrency());
    }
    
    @Test
    void testConstructorWithZeroAmount() {
        // Act
        Money money = new Money(0.0, "USD");
        
        // Assert
        assertEquals(0.0, money.getAmount());
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -100.50, -0.01})
    void testConstructorWithNegativeAmount(double negativeAmount) {
        assertThrows(IllegalArgumentException.class, () -> new Money(negativeAmount, "USD"));
    }
    
    @Test
    void testConstructorWithNullCurrency() {
        assertThrows(IllegalArgumentException.class, () -> new Money(100.0, null));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t"})
    void testConstructorWithBlankCurrency(String blank) {
        assertThrows(IllegalArgumentException.class, () -> new Money(100.0, blank));
    }
    
    @Test
    void testCurrencyToUpperCase() {
        // Act
        Money money = new Money(100.0, "usd");
        
        // Assert
        assertEquals("USD", money.getCurrency());
    }
    
    @Test
    void testMoneyEquality() {
        // Act
        Money money1 = new Money(100.0, "USD");
        Money money2 = new Money(100.0, "USD");
        
        // Assert
        assertEquals(money1, money2);
    }
    
    @Test
    void testMoneyInequality_DifferentAmount() {
        // Act
        Money money1 = new Money(100.0, "USD");
        Money money2 = new Money(50.0, "USD");
        
        // Assert
        assertNotEquals(money1, money2);
    }
    
    @Test
    void testMoneyInequality_DifferentCurrency() {
        // Act
        Money money1 = new Money(100.0, "USD");
        Money money2 = new Money(100.0, "EUR");
        
        // Assert
        assertNotEquals(money1, money2);
    }
    
    @Test
    void testMoneyToString() {
        // Act
        Money money = new Money(150.5, "USD");
        
        // Assert
        assertEquals("150.5 USD", money.toString());
    }
}
