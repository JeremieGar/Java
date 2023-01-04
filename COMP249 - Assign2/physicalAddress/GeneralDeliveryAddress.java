// -----------------------------------------------------
// Part: 1
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------
package physicalAddress;
import address.*;
import digitalAddress.TelecomAddress;

/**
 * Child of superclass Address
 * represents a physical general delivery address
 */
public class GeneralDeliveryAddress extends Address{

    private String addressLine;
    private String city;
    private String regionOrState;
    private String zipOrPostCode;
    private TelecomAddress telecomAddress;

    /**
     * default constructor
     */
    public GeneralDeliveryAddress(){
        super();
    }

    /**
     * parameterized constructor
     * @param validFrom valid after
     * @param validTo valid before
     * @param addressLine physical address
     * @param city city the address is in
     * @param regionOrState region or state the city is in
     * @param zipOrPostCode postal code the address is in
     * @param telecomAddress a TelecomAddress object
     */
    public GeneralDeliveryAddress(String validFrom, String validTo, String addressLine, String city,
                                  String regionOrState, String zipOrPostCode, TelecomAddress telecomAddress){
        super(validFrom, validTo);
        this.addressLine = addressLine;
        this.city = city;
        this.regionOrState = regionOrState;
        this.zipOrPostCode = zipOrPostCode;
        this.telecomAddress = telecomAddress;
    }

    /**
     * copy constructor
     * @param copy an address to be copied
     */
    public GeneralDeliveryAddress(GeneralDeliveryAddress copy){
        super(copy.getValidFrom(), copy.getValidTo());
        addressLine = copy.addressLine;
        city = copy.city;
        regionOrState = copy.regionOrState;
        zipOrPostCode = copy.zipOrPostCode;
        telecomAddress = copy.telecomAddress;
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

    public TelecomAddress getTelecomAddress() {
        return telecomAddress;
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

    public void setTelecomAddress(TelecomAddress telecomAddress) {
        this.telecomAddress = telecomAddress;
    }

    /**
     * toString() override from superclass
     * @return a string with all the address' information
     */
    @Override
    public String toString() {
        return "The general delivery address " + addressLine + ", " + city + ", " + regionOrState + " " + zipOrPostCode +
                ", has a telecom address and is valid from " + getValidFrom() + " to " + getValidTo() + ". " +
                telecomAddress.toString();
    }

    /**
     * equals() override from superclass
     * @param o an object to be compared
     * @return whether or not o is equal to the called object
     */
    @Override
    public boolean equals(Object o){

        if (o == null || getClass() != o.getClass()) return false;

        GeneralDeliveryAddress generalDeliveryAddress = (GeneralDeliveryAddress) o;

        if(generalDeliveryAddress.getValidFrom() != getValidFrom() || generalDeliveryAddress.getValidTo() != getValidTo())
            return false;

        if(generalDeliveryAddress.addressLine != addressLine || generalDeliveryAddress.city != city
                || generalDeliveryAddress.regionOrState != regionOrState
                || generalDeliveryAddress.zipOrPostCode != zipOrPostCode
                || !generalDeliveryAddress.telecomAddress.equals(telecomAddress))
            return false;
        else
            return true;
    }
}
