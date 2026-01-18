package domain;

/**
 * Money represents a monetary value with a specific currency.
 * It is a value object (immutable).
 * 
 * Responsibilities:
 * - Store amount and currency
 * - Provide monetary information
 */
public class Money {
    
    private double amount;
    private String currency;
    
    /**
     * Constructor: Creates a Money object with amount and currency.
     * 
     * @param amount the monetary amount (must be non-negative)
     * @param currency the currency code (e.g., "USD", "PKR")
     * @throws IllegalArgumentException if amount is negative or currency is null/empty
     */
    public Money(double amount, String currency) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        this.amount = amount;
        this.currency = currency.trim().toUpperCase();
    }
    
    /**
     * Get the monetary amount.
     * 
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }
    
    /**
     * Get the currency code.
     * 
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }
    
    /**
     * Get string representation of this money value.
     * 
     * @return string in format "AMOUNT CURRENCY" (e.g., "150.0 USD")
     */
    @Override
    public String toString() {
        return amount + " " + currency;
    }
    
    /**
     * Check equality with another Money object.
     * Two Money objects are equal if they have the same amount and currency.
     * 
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Money other = (Money) obj;
        return Double.compare(amount, other.amount) == 0 && currency.equals(other.currency);
    }
    
    /**
     * Get hash code for this Money object.
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Double.hashCode(amount) * 31 + currency.hashCode();
    }
}
