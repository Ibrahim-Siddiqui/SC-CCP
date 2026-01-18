package domain;

/**
 * Name represents a person's name.
 * It is a value object (immutable).
 * 
 * Responsibilities:
 * - Store first name and last name
 * - Provide full name
 */
public class Name {
    
    private String firstName;
    private String lastName;
    
    /**
     * Constructor: Creates a Name object with first and last name.
     * 
     * @param firstName the first name
     * @param lastName the last name
     * @throws IllegalArgumentException if either name is null or empty
     */
    public Name(String firstName, String lastName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
    }
    
    /**
     * Get the first name.
     * 
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Get the last name.
     * 
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Get the full name (first name + last name).
     * 
     * @return full name in format "FirstName LastName"
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Get string representation of this name.
     * 
     * @return full name
     */
    @Override
    public String toString() {
        return getFullName();
    }
    
    /**
     * Check equality with another Name object.
     * Two Name objects are equal if they have the same first and last name.
     * 
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Name other = (Name) obj;
        return firstName.equalsIgnoreCase(other.firstName) && 
               lastName.equalsIgnoreCase(other.lastName);
    }
    
    /**
     * Get hash code for this Name object.
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {
        return firstName.toLowerCase().hashCode() * 31 + lastName.toLowerCase().hashCode();
    }
}
