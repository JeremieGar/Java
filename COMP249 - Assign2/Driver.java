// -----------------------------------------------------
// Part: 1 and 2
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------
import address.*;
import digitalAddress.*;
import physicalAddress.*;
import static java.lang.Integer.parseInt;

/**
 * Driver class for both Part 1 and 2.
 */
public class Driver {

    /**
     * Takes in an array, copies its elements into a copy array and returns it;
     * @param arr array of addresses
     * @return Address[] copy
     */
    static Address[] copyAddresses(Address[] arr){

        Address[] copy = new Address[arr.length];

        for(int i = 0; i<arr.length; i++)
            copy[i] = new Address(arr[i]);

        return copy;
    }

    /**
     * @param arr array of addresses
     * @param message a string which is displayed before the list
     */
    static void displayArray(Address[] arr, String message){
        System.out.println(message);
        for(int i=0; i<arr.length; i++)
            System.out.println((i+1)+") " + arr[i].toString());
        System.out.println();
    }

    public static void main(String[] args){
        //Part I
        System.out.println("Part 1");
        System.out.println("====================================================================");
        WebPageAddress a1 = new WebPageAddress("2001-09-23","2001-12-30", "www.bing.ca");
        EmailAddress a2 = new EmailAddress("2003-01-12","2045-04-13","jeremiegarzon","@","gmail",".","com");
        TelecomAddress a3 = new TelecomAddress("2005-02-22","2010-02-22","+1","(0)",208,1234567,789,"mobile");
        GeographicAddress a4 = new GeographicAddress("1999-11-05","2050-11-05","1234 Cool Avenue","Montreal","QC, Canada","A1B 2C3", new Locale("CA",124,"Canada"));
        PostOfficeAddress a5 = new PostOfficeAddress("1900-01-01","2100-01-01","5678 NotSoCool Avenue","Montreal","QC, Canada","D4E 5F6", new Locale("CA",124,"Canada"),0354);
        GeneralDeliveryAddress a6 = new GeneralDeliveryAddress("2010-03-09","2040-06-06","7492 Another Avenue","Miami","FL, USA","F4E 5N6", a3);
        EmailAddress a7 = new EmailAddress("2003-01-12","2045-04-13","jeremiegarzon","@","gmail",".","com");
        WebPageAddress a8 = new WebPageAddress("2001-09-23","2001-12-30", "www.amazon.ca");
        WebPageAddress a9 = new WebPageAddress("2001-10-23","2001-12-30", "www.amazon.ca");
        WebPageAddress a10 = new WebPageAddress("2000-01-23","2025-12-30", "www.tumblr.com");
        WebPageAddress a11 = new WebPageAddress("2002-09-23","2022-12-30", "www.reddit.com");
        WebPageAddress a12 = new WebPageAddress("2003-09-23","2003-12-30", "www.facebook.ca");
        WebPageAddress a13 = new WebPageAddress("2004-09-23","2004-12-30", "www.deviantart.com");
        WebPageAddress a14 = new WebPageAddress("2005-09-23","2005-12-30", "www.moodle.ca");
        WebPageAddress a15 = new WebPageAddress("2006-09-23","2026-12-30", "www.youtube.ca");
        WebPageAddress a16 = new WebPageAddress("2008-09-23","2039-12-30", "www.hotmail.ca");
        WebPageAddress a17 = new WebPageAddress("2011-09-23","2011-12-30", "www.google.ca");
        printWithObsolete(a1,isObsolete(a1,2021,3,8)); printWithObsolete(a2,isObsolete(a2,2021,3,8));
        printWithObsolete(a3,isObsolete(a3,2021,3,8)); printWithObsolete(a4,isObsolete(a4,2021,3,8));
        printWithObsolete(a5,isObsolete(a5,2021,3,8)); printWithObsolete(a6,isObsolete(a6,2021,3,8));
        printWithObsolete(a7,isObsolete(a7,2021,3,8)); printWithObsolete(a8,isObsolete(a8,2021,3,8));
        printWithObsolete(a9,isObsolete(a9,2021,3,8)); printWithObsolete(a10,isObsolete(a10,2021,3,8));
        printWithObsolete(a11,isObsolete(a11,2021,3,8)); printWithObsolete(a12,isObsolete(a12,2021,3,8));
        printWithObsolete(a13,isObsolete(a13,2021,3,8)); printWithObsolete(a14,isObsolete(a14,2021,3,8));
        printWithObsolete(a15,isObsolete(a15,2021,3,8)); printWithObsolete(a16,isObsolete(a16,2021,3,8));
        printWithObsolete(a17,isObsolete(a17,2021,3,8));
        System.out.println();

        if(a2.equals(a7)) System.out.println("The second and seventh addresses are equal."); else System.out.println("The second and seventh addresses are not equal.");
        if(a1.equals(a6)) System.out.println("The first and sixth addresses are equal."); else System.out.println("The first and sixth addresses are not equal.");
        if(a8.equals(a9)) System.out.println("The eighth and ninth addresses are equal."); else System.out.println("The eighth and ninth addresses are not equal.");
        System.out.println();

        Address[] arr1 = {a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17};
        traceObsoleteAddresses(arr1,2021,3,8);
        System.out.println();

        //PART II
        Address[] arr2 = {a17,a16,a15,a14,a13,a12,a11,a10,a9,a8,a7,a6,a5,a4,a3,a2,a1};
        displayArray(arr2,"The contents of the second array of Addresses are:");
        Address[] arr3 = copyAddresses(arr2);
        displayArray(arr3,"The contents of the third array of Addresses are:");
        /*  without the ability to check the Object type and use the specific copy constructor based of the Object type of the Address,
            we are essentially calling the toString method from the Address Object. It works since all of Address' child classes have a
            super constructor. Thus, this copy is not correct
         */

    }

    /**
     * checks if an address is obsolete, and prints its information if yes
     * @param arr array of addresses
     * @param year year of checked date
     * @param month month of checked date
     * @param day day of checked date
     */
    static void traceObsoleteAddresses(Address[] arr, int year, int month, int day){

        System.out.println("Out of all the addresses, here are obsolete ones:");

        for(int i =0; i<arr.length; i++){
            if(isObsolete(arr[i],year, month, day)) {
                System.out.print((i+1)+") ");
                printWithObsolete(arr[i], true);
            }
        }
    }

    /**
     * Checks if the validFrom date is before the parametrized date (year, month, day)
     * @param validFrom
     * @param year year of checked date
     * @param month month of checked date
     * @param day day of checked date
     * @return
     */
    static boolean isAfter(String validFrom, int year, int month, int day){

        int validFromYear = parseInt(validFrom.substring(0,4));
        int validFromMonth = parseInt(validFrom.substring(5,7));
        int validFromDay = parseInt(validFrom.substring(8,validFrom.length()));

        if(year < validFromYear)
            return false;
        else if(year == validFromYear) {
            if (month < validFromMonth)
                return false;
            else if(month == validFromMonth){
                if(day < validFromDay)
                    return false;
                else return true;
            }
            else
                return true;
        }
        else
            return true;
    }

    /**
     * Checks if the validTo date is after the parametrized date (year, month, day)
     * @param validTo
     * @param year year of checked date
     * @param month month of checked date
     * @param day day of checked date
     * @return
     */
    static boolean isBefore(String validTo, int year, int month, int day){

        int validToYear = parseInt(validTo.substring(0,4));
        int validToMonth = parseInt(validTo.substring(5,7));
        int validToDay = parseInt(validTo.substring(8,validTo.length()));

        if(year > validToYear)
            return false;
        else if(year == validToYear) {
            if (month > validToMonth)
                return false;
            else if(month == validToMonth){
                if(day > validToDay)
                    return false;
                else return true;
            }
            else
                return true;
        }
        else
            return true;
    }


    /**
     * If a date if after the ValidFrom date, and before the ValidTo date, it is considered valid.
     * If not, it is considered obsolete and true is returned.
     * @param a Address to be checked
     * @param year year of checked date
     * @param month month of checked date
     * @param day day of checked date
     * @return
     */
    static boolean isObsolete(Address a, int year, int month, int day) {

        String validFrom = a.getValidFrom();
        String validTo = a.getValidTo();

        if(isAfter(validFrom, year, month, day) && isBefore(validTo, year, month, day))
            return false;
        else
            return true;
    }


    /**
     * Uses the toString() method to print out the address' information and whether or not the
     * address is obsolete.
     * @param a an address
     * @param obsolete whether or not the address is obsolete
     */
    static void printWithObsolete(Address a, boolean obsolete){
        if(obsolete)
            System.out.println(a.toString() + ". This address is currently obsolete.");
        else
            System.out.println(a.toString() + ". This address is currently valid.");
    }
}
