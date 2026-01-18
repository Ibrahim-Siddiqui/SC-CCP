package domain;

/**
 * Guest represents a person staying at a hotel.
 * A guest has a name and address details.
 * 
 * Responsibilities:
 * - Store guest's personal information (name, address)
 * - Provide guest information
 */
public class Guest {
    
    private Name name;
    private Address addressDetails;
    
    /**
     * Constructor: Creates a Guest with name and address.
     * 
     * @param name the guest's name
     * @param addressDetails the guest's address
     * @throws IllegalArgumentException if name or address is null
     */
    public Guest(Name name, Address addressDetails) {
        if (name == null) {
            throw new IllegalArgumentException("Guest name cannot be null");
        }
        if (addressDetails == null) {
            throw new IllegalArgumentException("Guest address cannot be null");
        }
        this.name = name;
        this.addressDetails = addressDetails;
    }
    
    /**
     * Get the guest's name.
     * 
     * @return the name
     */
    public Name getName() {
        return name;
    }
    
    /**
     * Get the guest's address details.
     * 
     * @return the address
     */
    public Address getAddressDetails() {
        return addressDetails;
    }
    
    /**
     * Get the guest's full name as string.
     * 
     * @return full name
     */
    public String getNameAsString() {
        return name.getFullName();
    }
    
    /**
     * Get string representation of this guest.
     * 
     * @return string with guest name
     */
    @Override
    public String toString() {
        return name.getFullName();
    }
    
    /**
     * Check equality with another Guest object.
     * Two guests are equal if they have the same name.
     * 
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Guest other = (Guest) obj;
        return name.equals(other.name);
    }
    
    /**
     * Get hash code for this Guest object.
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
