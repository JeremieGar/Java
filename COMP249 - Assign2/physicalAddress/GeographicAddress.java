// -----------------------------------------------------
// Part: 1
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------
package physicalAddress;
import address.Address;

/**
 * Child of superclass Address
 * represents a physical geographic address
 */
public class GeographicAddress extends Address {

    private String addressLine;
    private String city;
    private String regionOrState;
    private String zipOrPostCode;
    private Locale locale;

    /**
     * default constructor
     */
    public GeographicAddress(){
        super();
    }

    /**
     * parameterized constructor
     * @param validFrom valid after
     * @param validTo valid before
     * @param addressLine physical address
     * @param city city the address is in
     * @param regionOrState region or state the city is in
     * @param zipOrPostCode postal code of the address
     * @param locale Locale object
     */
    public GeographicAddress(String validFrom, String validTo, String addressLine, String city, String regionOrState,
                             String zipOrPostCode, Locale locale){
        super(validFrom, validTo);
        this.addressLine = addressLine;
        this.city = city;
        this.regionOrState = regionOrState;
        this.zipOrPostCode = zipOrPostCode;
        this.locale = locale;
    }

    /**
     * copy constructor
     * @param copy an address to be copied
     */
    public GeographicAddress(GeographicAddress copy){
        super(copy.getValidFrom(),copy.getValidTo());
        addressLine = copy.addressLine;
        city = copy.city;
        regionOrState = copy.regionOrState;
        zipOrPostCode = copy.zipOrPostCode;
        locale = copy.locale;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getCity() {
        return city;
    }

    public String getRegionOrState() {
        return regionOrState;
    }

    public String getZipOrPostCode() {
        return zipOrPostCode;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegionOrState(String regionOrState) {
        this.regionOrState = regionOrState;
    }

    public void setZipOrPostCode(String zipOrPostCode) {
        this.zipOrPostCode = zipOrPostCode;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * toString() override from superclass
     * @return a string with all the address' information
     */
    @Override
    public String toString() {
        return "The geographic address address " + addressLine + ", " + city + ", " + regionOrState + " " + zipOrPostCode +
                " locale " + locale.toString() + " is valid from " + getValidFrom() + " to " + getValidTo();
    }

    /**
     * equals() override from superclass
     * @param o an object to be compared
     * @return whether or not o is equal to the called object
     */
    @Override
    public boolean equals(Object o){

        if (o == null || getClass() != o.getClass()) return false;

        GeographicAddress geographicAddress = (GeographicAddress) o;

        if(geographicAddress.getValidFrom() != getValidFrom() || geographicAddress.getValidTo() != getValidTo())
            return false;

        if(geographicAddress.addressLine != addressLine || geographicAddress.city != city || geographicAddress.regionOrState != regionOrState
                || geographicAddress.zipOrPostCode != zipOrPostCode || !geographicAddress.locale.equals(locale))
            return false;
        else
            return true;
    }
}
