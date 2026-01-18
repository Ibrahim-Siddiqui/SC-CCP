package domain;

/**
 * Identity represents an identity document for a person.
 * It is a value object (immutable).
 * Examples: passport, driver's license, national ID.
 * 
 * Responsibilities:
 * - Store identity document number and type
 * - Provide identity information
 */
public class Identity {
    
    private String number;
    private String type;
    
    /**
     * Constructor: Creates an Identity object.
     * 
     * @param number the identity document number
     * @param type the type of identity (e.g., "Passport", "CNIC", "Driver's License")
     * @throws IllegalArgumentException if either field is null or empty
     */
    public Identity(String number, String type) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException("Identity number cannot be null or empty");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Identity type cannot be null or empty");
        }
        this.number = number.trim();
        this.type = type.trim();
    }
    
    /**
     * Get the identity document number.
     * 
     * @return the document number
     */
    public String getNumber() {
        return number;
    }
    
    /**
     * Get the identity document type.
     * 
     * @return the document type
     */
    public String getType() {
        return type;
    }
    
    /**
     * Get string representation of this identity.
     * 
     * @return string in format "Type: Number"
     */
    @Override
    public String toString() {
        return type + ": " + number;
    }
    
    /**
     * Check equality with another Identity object.
     * 
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Identity other = (Identity) obj;
        return number.equals(other.number) && type.equalsIgnoreCase(other.type);
    }
    
    /**
     * Get hash code for this Identity object.
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {
        return number.hashCode() * 31 + type.toLowerCase().hashCode();
    }
}
