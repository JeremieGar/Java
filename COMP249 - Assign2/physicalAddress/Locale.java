// -----------------------------------------------------
// Part: 1
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------
package physicalAddress;

public class Locale{

    private String letterCountryCode;
    private int numericCountryCode;
    private String countryName;

    /**
     * default constructor
     */
    public Locale(){
    }

    /**
     * parameterized constructor
     * @param letterCountryCode letter country code
     * @param numericCountryCode numeric country code
     * @param countryName name of the country
     */
    public Locale(String letterCountryCode, int numericCountryCode, String countryName){
        this.letterCountryCode = letterCountryCode;
        this.numericCountryCode = numericCountryCode;
        this.countryName = countryName;
    }

    /**
     * copy constructor
     * @param copy an address to be copied
     */
    public Locale(Locale copy){
        letterCountryCode = copy.letterCountryCode;
        numericCountryCode = copy.numericCountryCode;
        countryName = copy.countryName;
    }

    protected String getLetterCountryCode() {
        return letterCountryCode;
    }

    protected int getNumericCountryCode() {
        return numericCountryCode;
    }

    protected String getCountryName() {
        return countryName;
    }

    protected void setLetterCountryCode(String letterCountryCode) {
        this.letterCountryCode = letterCountryCode;
    }

    protected void setNumericCountryCode(int numericCountryCode) {
        this.numericCountryCode = numericCountryCode;
    }

    protected void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * toString() overload
     * @return a string with all the locale's information
     */
    public String toString(){
        return "\"" + letterCountryCode + "\" " + numericCountryCode + " \"" + countryName + "\"";
    }

    /**
     * equals() overload
     * @param o an object to be compared
     * @return whether or not o is equal to the called object
     */
    public boolean equals(Object o){

        if (o == null || getClass() != o.getClass()) return false;

        Locale locale = (Locale) o;

        if(locale.letterCountryCode != letterCountryCode || locale.numericCountryCode != numericCountryCode || locale.countryName != countryName)
            return false;
        else
            return true;

    }
}
