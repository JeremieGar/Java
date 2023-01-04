// -----------------------------------------------------
// Part: 1
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------
package digitalAddress;
import address.*;

/**
 * Child of superclass Address
 * represents a digital webpage address
 */
public class WebPageAddress extends Address{

    private String url;

    /**
     * default constructor
     */
    public WebPageAddress(){
        super();
    }

    /**
     * parameterized constructor
     * @param validFrom address valid after
     * @param validTo address valid before
     * @param url the webpage address
     */
    public WebPageAddress(String validFrom, String validTo, String url){
        super(validFrom,validTo);
        this.url = url;
    }

    /**
     * copy constructor
     * @param copy an address to be copied
     */
    public WebPageAddress(WebPageAddress copy){
        super(copy.getValidFrom(), copy.getValidTo());
        url = copy.url;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String newURL) {
        url = newURL;
    }

    /**
     * toString() override from superclass
     * @return a string with all the address' information
     */
    @Override
    public String toString(){
        return "The webpage's URL " + url + " is valid from " + getValidFrom() + " to " + getValidTo();
    }

    /**
     * equals() override from superclass
     * @param o an object to be compared
     * @return whether or not o is equal to the called object
     */
    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;

        WebPageAddress webAddress = (WebPageAddress) o;

        if(webAddress.getValidFrom() != getValidFrom() || webAddress.getValidTo() != getValidTo())
            return false;

        return url.equals(webAddress.url);

    }


}
