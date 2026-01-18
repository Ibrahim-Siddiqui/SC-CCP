package domain;

/**
 * ReserverPayer represents a person who makes a reservation and pays for it.
 * This person provides payment information via credit card and identity details.
 * 
 * Responsibilities:
 * - Store identity information (ID, passport, etc.)
 * - Store credit card details for payment
 * - Provide payment and identity information
 */
public class ReserverPayer {
    
    private CreditCard creditCardDetails;
    private Identity id;
    
    /**
     * Constructor: Creates a ReserverPayer with identity and credit card information.
     * 
     * @param creditCardDetails the credit card details for payment
     * @param id the identity document for verification
     * @throws IllegalArgumentException if either parameter is null
     */
    public ReserverPayer(CreditCard creditCardDetails, Identity id) {
        if (creditCardDetails == null) {
            throw new IllegalArgumentException("Credit card details cannot be null");
        }
        if (id == null) {
            throw new IllegalArgumentException("Identity document cannot be null");
        }
        this.creditCardDetails = creditCardDetails;
        this.id = id;
    }
    
    /**
     * Get the credit card details.
     * 
     * @return the credit card
     */
    public CreditCard getCreditCardDetails() {
        return creditCardDetails;
    }
    
    /**
     * Get the identity document.
     * 
     * @return the identity
     */
    public Identity getId() {
        return id;
    }
    
    /**
     * Get string representation of this reserver/payer.
     * 
     * @return string with identity and masked credit card
     */
    @Override
    public String toString() {
        return id + " - Card: " + creditCardDetails.getMaskedCardNumber();
    }
}
