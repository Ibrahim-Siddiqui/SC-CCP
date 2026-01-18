package domain;

/**
 * Address represents a physical address.
 * It is a value object (immutable).
 * 
 * Responsibilities:
 * - Store street, city, country, and postal code
 * - Provide complete address information
 */
public class Address {
    
    private String street;
    private String city;
    private String country;
    private String postalCode;
    
    /**
     * Constructor: Creates an Address object.
     * 
     * @param street the street address
     * @param city the city
     * @param country the country
     * @param postalCode the postal code
     * @throws IllegalArgumentException if any field is null or empty
     */
    public Address(String street, String city, String country, String postalCode) {
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        if (postalCode == null || postalCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Postal code cannot be null or empty");
        }
        
        this.street = street.trim();
        this.city = city.trim();
        this.country = country.trim();
        this.postalCode = postalCode.trim();
    }
    
    /**
     * Get the street address.
     * 
     * @return the street
     */
    public String getStreet() {
        try {
            return street;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return street;
    }
    
    /**
     * Get the city.
     * 
     * @return the city
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Get the country.
     * 
     * @return the country
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * Get the postal code.
     * 
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }
    
    /**
     * Get the complete address as a formatted string.
     * 
     * @return address in format "Street, City, Country PostalCode"
     */
    public String getCompleteAddress() {
        return street + ", " + city + ", " + country + " " + postalCode;
    }
    
    /**
     * Get string representation of this address.
     * 
     * @return complete address
     */
    @Override
    public String toString() {
        return getCompleteAddress();
    }
    
    /**
     * Check equality with another Address object.
     * 
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Address other = (Address) obj;
        return street.equalsIgnoreCase(other.street) && 
               city.equalsIgnoreCase(other.city) &&
               country.equalsIgnoreCase(other.country) &&
               postalCode.equalsIgnoreCase(other.postalCode);
    }
    
    /**
     * Get hash code for this Address object.
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {
        return street.toLowerCase().hashCode() * 31 + 
               city.toLowerCase().hashCode() * 31 +
               country.toLowerCase().hashCode() * 31 +
               postalCode.toLowerCase().hashCode();
    }
}
