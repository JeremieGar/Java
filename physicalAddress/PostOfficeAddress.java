// -----------------------------------------------------
// Part: 1
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------
package physicalAddress;
import address.*;

/**
 * Child of superclass Address
 * represents a physical post office address
 */
public class PostOfficeAddress extends Address{

    private String addressLine;
    private String city;
    private String regionOrState;
    private String zipOrPostCode;
    private Locale locale;
    private int boxLobbyDoorCode;

    /**
     * default constructor
     */
    public PostOfficeAddress(){
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
     * @param locale a Locale object
     * @param boxLobbyDoorCode door code for the lobby
     */
    public PostOfficeAddress(String validFrom, String validTo, String addressLine, String city, String regionOrState,
                             String zipOrPostCode, Locale locale, int boxLobbyDoorCode){
        super(validFrom, validTo);
        this.addressLine = addressLine;
        this.city = city;
        this.regionOrState = regionOrState;
        this.zipOrPostCode = zipOrPostCode;
        this.locale = locale;
        this.boxLobbyDoorCode = boxLobbyDoorCode;
    }

    /**
     * copy constructor
     * @param copy an address to be copied
     */
    public PostOfficeAddress(PostOfficeAddress copy){
        super(copy.getValidFrom(),copy.getValidTo());
        addressLine = copy.addressLine;
        city = copy.city;
        regionOrState = copy.regionOrState;
        zipOrPostCode = copy.zipOrPostCode;
        locale = copy.locale;
        boxLobbyDoorCode = copy.boxLobbyDoorCode;
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

    public int getBoxLobbyDoorCode() {
        return boxLobbyDoorCode;
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

    public void setBoxLobbyDoorCode(int boxLobbyDoorCode) {
        this.boxLobbyDoorCode = boxLobbyDoorCode;
    }

    /**
     * toString() override from superclass
     * @return a string with all the address' information
     */
    @Override
    public String toString() {
        return "The post office address " + addressLine + ", " + city + ", " + regionOrState + " " + zipOrPostCode +
                " locale " + locale.toString() + " door code: " + boxLobbyDoorCode + " is valid from " + getValidFrom() +
                " to " + getValidTo();
    }

    /**
     * equals() override from superclass
     * @param o an object to be compared
     * @return whether or not o is equal to the called object
     */
    @Override
    public boolean equals(Object o){

        if (o == null || getClass() != o.getClass()) return false;

        PostOfficeAddress postOfficeAddress = (PostOfficeAddress) o;

        if(postOfficeAddress.getValidFrom() != getValidFrom() || postOfficeAddress.getValidTo() != getValidTo())
            return false;

        if(postOfficeAddress.addressLine != addressLine || postOfficeAddress.city != city || postOfficeAddress.regionOrState != regionOrState
                || postOfficeAddress.zipOrPostCode != zipOrPostCode || !postOfficeAddress.locale.equals(locale)
                || postOfficeAddress.boxLobbyDoorCode != boxLobbyDoorCode)
            return false;
        else
            return true;
    }
}
