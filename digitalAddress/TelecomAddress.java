// -----------------------------------------------------
// Part: 1
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------
package digitalAddress;
import address.*;

/**
 * Child of superclass Address
 * represents a digital telecom address
 */
public class TelecomAddress extends Address{

    private String countryCode;
    private String nationalDirectDialingPrefix;
    private int areaCode;
    private long number;
    private int extension;
    private String physicalType;

    /**
     * default constructor
     */
    public TelecomAddress() {
        super();
    }

    /**
     * parameterized constructor
     * @param validFrom valid after
     * @param validTo valid before
     * @param countryCode the number you must use to direct dial a particular country
     * @param nationalDirectDialingPrefix the prefix to make a call within a country between different cities or areas
     * @param areaCode the code for an area or city
     * @param number the telephone number
     * @param extension phone extension
     * @param physicalType type of phone device
     */
    public TelecomAddress(String validFrom, String validTo, String countryCode, String nationalDirectDialingPrefix,
                          int areaCode, long number, int extension, String physicalType) {
        super(validFrom, validTo);
        this.countryCode = countryCode;
        this.nationalDirectDialingPrefix = nationalDirectDialingPrefix;
        this.areaCode = areaCode;
        this.number = number;
        this.extension = extension;
        this.physicalType = physicalType;
    }

    /**
     * copy constructor
     * @param copy a telecom address to be copied
     */
    public TelecomAddress(TelecomAddress copy) {
        super(copy.getValidFrom(), copy.getValidTo());
        countryCode = copy.countryCode;
        nationalDirectDialingPrefix = copy.nationalDirectDialingPrefix;
        areaCode = copy.areaCode;
        number = copy.number;
        extension = copy.extension;
        physicalType = copy.physicalType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNationalDirectDialingPrefix() {
        return nationalDirectDialingPrefix;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public long getNumber() {
        return number;
    }

    public int getExtension() {
        return extension;
    }

    public String getPhysicalType() {
        return physicalType;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setNationalDirectDialingPrefix(String nationalDirectDialingPrefix) {
        this.nationalDirectDialingPrefix = nationalDirectDialingPrefix;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public void setPhysicalType(String physicalType) {
        this.physicalType = physicalType;
    }

    /**
     * toString() override from superclass
     * @return a string with all the address' information
     */
    @Override
    public String toString(){
        return "The telecom address " + countryCode + " " + nationalDirectDialingPrefix + areaCode + " " +
                number + " ext. " + extension + " " + physicalType + " is valid from " +
                getValidFrom() + " to " + getValidTo();
    }

    /**
     * equals() override from superclass
     * @param o an object to be compared
     * @return whether or not o is equal to the called object
     */
    @Override
    public boolean equals(Object o){

        if (o == null || getClass() != o.getClass()) return false;

        TelecomAddress telecomAddress = (TelecomAddress) o;

        if(telecomAddress.getValidFrom() != getValidFrom() || telecomAddress.getValidTo() != getValidTo())
            return false;

        if(telecomAddress.countryCode != countryCode || telecomAddress.nationalDirectDialingPrefix != nationalDirectDialingPrefix
            || telecomAddress.areaCode != areaCode || telecomAddress.number != number || telecomAddress.extension != extension
            || telecomAddress.physicalType != physicalType)
            return false;
        else
            return true;
    }
}
