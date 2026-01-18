package domain;

/**
 * CreditCard represents a credit card for payment.
 * It is a value object (immutable).
 * 
 * Responsibilities:
 * - Store card number, cardholder name, and expiry date
 * - Provide card information
 */
public class CreditCard {
    
    private String cardNumber;
    private String cardholderName;
    private String expiryDate;
    
    /**
     * Constructor: Creates a CreditCard object.
     * 
     * @param cardNumber the credit card number (16 digits)
     * @param cardholderName the name on the card
     * @param expiryDate the expiry date (format: MM/YY)
     * @throws IllegalArgumentException if any field is invalid
     */
    public CreditCard(String cardNumber, String cardholderName, String expiryDate) {
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Card number cannot be null or empty");
        }
        if (cardholderName == null || cardholderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Cardholder name cannot be null or empty");
        }
        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Expiry date cannot be null or empty");
        }
        
        this.cardNumber = cardNumber.trim();
        this.cardholderName = cardholderName.trim();
        this.expiryDate = expiryDate.trim();
    }
    
    /**
     * Get the card number (masked for security).
     * Shows only last 4 digits.
     * 
     * @return masked card number
     */
    public String getMaskedCardNumber() {
        if (cardNumber.length() < 4) {
            return "****";
        }
        return "****" + cardNumber.substring(cardNumber.length() - 4);
    }
    
    /**
     * Get the full card number (for validation/processing).
     * 
     * @return the full card number
     */
    public String getCardNumber() {
        return cardNumber;
    }
    
    /**
     * Get the cardholder name.
     * 
     * @return the cardholder name
     */
    public String getCardholderName() {
        return cardholderName;
    }
    
    /**
     * Get the expiry date.
     * 
     * @return the expiry date
     */
    public String getExpiryDate() {
        return expiryDate;
    }
    
    /**
     * Get string representation of this credit card.
     * 
     * @return string with masked card number and cardholder name
     */
    @Override
    public String toString() {
        return getMaskedCardNumber() + " (" + cardholderName + ")";
    }
    
    /**
     * Check equality with another CreditCard object.
     * 
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        CreditCard other = (CreditCard) obj;
        return cardNumber.equals(other.cardNumber) && 
               cardholderName.equalsIgnoreCase(other.cardholderName) &&
               expiryDate.equals(other.expiryDate);
    }
    
    /**
     * Get hash code for this CreditCard object.
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {
        return cardNumber.hashCode() * 31 + cardholderName.toLowerCase().hashCode();
    }
}
