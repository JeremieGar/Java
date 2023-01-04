// -----------------------------------------------------
// Part: 1
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------
package address;

/**
 * Main address object
 */
public class Address {

    private String validFrom;
    private String validTo;

    /**
     * default constructor
     */
    protected Address(){
    }

    /**
     * Parametrized constructor
     * @param validFrom the address is valid after
     * @param validTo the address is valid before
     */
    protected Address(String validFrom, String validTo){
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    /**
     * copy constructor
     * @param copy an address to be copied
     */
    public Address(Address copy){
        validFrom = copy.validFrom;
        validTo = copy.validTo;;
    }

    //accessors & mutators
    /**
     * accessor method for validFrom
     * @return validFrom
     */
    public String getValidFrom(){
        return validFrom;
    }

    /**
     * accessor method for validTo
     * @return validTo
     */
    public String getValidTo(){
        return validTo;
    }

    /**
     * mutator method for validFrom
     * @param newValidFrom
     */
    public void setValidFrom(String newValidFrom){
        validFrom = newValidFrom;
    }

    /**
     * mutator method for validFrom
     * @param newValidTo
     */
    public void setValidTo(String newValidTo) {
        validTo = newValidTo;
    }

    /**
     * toString() override
     * @return a string with all an address' information
     */
    @Override
    public String toString(){
        return "This address is valid from " + validFrom + " to " + validTo;
    }

    /**
     * equals() overload
     * @param o an object to be compared
     * @return whether or not o is equal to the called object
     */
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) return false;

        Address a = (Address) o;

        if (!validFrom.equals(a.validFrom))
            return false;

        return validTo.equals(a.validTo);
    }

}
