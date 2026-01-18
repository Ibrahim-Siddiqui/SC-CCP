package domain;

/**
 * HowMany represents a quantity value.
 * It is a value object (immutable).
 * 
 * Responsibilities:
 * - Store a numeric count/quantity
 * - Validate that the number is positive
 */
public class HowMany {
    
    private int number;
    
    /**
     * Constructor: Creates a HowMany object with a positive integer.
     * 
     * @param number the quantity (must be positive)
     * @throws IllegalArgumentException if number is non-positive
     */
    public HowMany(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("HowMany number must be positive");
        }
        this.number = number;
    }
    
    /**
     * Get the numeric value.
     * 
     * @return the number
     */
    public int getNumber() {
        return number;
    }
    
    /**
     * Get string representation of this quantity.
     * 
     * @return the number as string
     */
    @Override
    public String toString() {
        return String.valueOf(number);
    }
    
    /**
     * Check equality with another HowMany object.
     * 
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        HowMany other = (HowMany) obj;
        return number == other.number;
    }
    
    /**
     * Get hash code for this HowMany object.
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(number);
    }
}
