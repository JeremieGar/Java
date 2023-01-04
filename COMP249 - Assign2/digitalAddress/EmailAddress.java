// -----------------------------------------------------
// Part: 1
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------
package digitalAddress;
import address.*;

/**
 * Child of superclass Address
 * represents a digital electronic mail address
 */
public class EmailAddress extends Address{

    private String userName;
    private String sign;
    private String domainName;
    private String punctuation;
    private String tld; //TLD represents a Top Level Domain

    /**
     * default constructor
     */
    public EmailAddress(){
        super();
    }

    /**
     * parameterized constructor
     * @param validFrom valid after
     * @param validTo valid before
     * @param user username
     * @param sign symbol seperating the username and domain
     * @param domain domain name
     * @param punc punctuation
     * @param topLevelDomain top level domain (com, org, gov, etc...)
     */
    public EmailAddress(String validFrom, String validTo, String user, String sign, String domain, String punc, String topLevelDomain){
        super(validFrom, validTo);
        userName = user;
        this.sign = sign;
        domainName = domain;
        punctuation = punc;
        tld = topLevelDomain;
    }

    /**
     * copy constructor
     * @param copy an address to be copied
     */
    public EmailAddress(EmailAddress copy){
        super(copy.getValidFrom(), copy.getValidTo());
        userName = copy.userName;
        sign = copy.sign;
        domainName = copy.domainName;
        punctuation = copy.punctuation;
        tld = copy.tld;
    }

    public String getUserName(){
        return userName;
    }

    public String getSign(){
        return sign;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getPunctuation() {
        return punctuation;
    }

    public String getTLD() {
        return tld;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setPunctuation(String punctuation) {
        this.punctuation = punctuation;
    }

    public void setTLD(String tld) {
        this.tld = tld;
    }

    /**
     * toString() override from superclass
     * @return a string with all the address' information
     */
    @Override
    public String toString() {
        return "The email address " + userName + sign + domainName + punctuation + tld + " is valid from " +
                getValidFrom() + " to " + getValidTo();
    }

    /**
     * equals() override from superclass
     * @param o an object to be compared
     * @return whether or not o is equal to the called object
     */
    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;

        EmailAddress emailAddress = (EmailAddress) o;

        if(emailAddress.getValidFrom() != getValidFrom() || emailAddress.getValidTo() != getValidTo())
            return false;

        if(emailAddress.userName != userName || emailAddress.sign != sign || emailAddress.domainName != domainName ||
                emailAddress.punctuation != punctuation || emailAddress.tld != tld)
            return false;
        else
            return true;

    }
}
