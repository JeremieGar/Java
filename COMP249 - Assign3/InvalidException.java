// -----------------------------------------------------
// COMP249 - Winter 2021 Assignment 3
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------

import java.io.FileWriter;
import java.io.IOException;

/**
 * Basic exception which doesn't seem to have any use in the program, more specific child exception classes
 */
public class InvalidException extends Exception{

    public InvalidException(){
        super("Error: Input row cannot be parsed due to missing information.");
    }

    public InvalidException(String message){
        super(message);
    }
}

/**
 * Exception which is called when a category is found to be missing
 */
class CSVFileInvalidException extends InvalidException{

    /**
     * Parametrized Constructor
     * @param message error message to be displayed to the user
     * @param filePath directory path of all files
     * @param csv CSV file name
     * @param len amount of categories
     * @param missing amount of missing categories
     * @param categories String array of categories
     * @throws IOException
     */
    public CSVFileInvalidException(String message, String filePath, String csv, int len, int missing, String[] categories) throws IOException {

        super(message);
        FileWriter log = new FileWriter(filePath + "log.txt", true);
        log.write("File \"" + csv + "\" is invalid.\n" +
                    "Missing field: " + len + " detected, " + missing + " missing\n -- ");

        for(int i=0; i<categories.length;i++){
            if(!categories[i].equals(""))
                log.write(categories[i] + " -- ");
            else
                log.write("*** -- ");
        }

        log.write("\n\n");
        log.close();
    }
}

/**
 * Exception which is called when data is found to be missing
 */
class CSVDataMissing extends InvalidException{

    /**
     * Parametrized constructor
     * @param message error message to be displayed to the user
     * @param filePath directory path of all files
     * @param csv CSV file name
     * @param line line number where the data is found missing
     * @param data String array of the data
     * @throws IOException
     */
    public CSVDataMissing(String message, String filePath, String csv, int line, String[] data) throws IOException{

        super(message);
        FileWriter log = new FileWriter(filePath + "log.txt", true);
        log.write("In file \"" + csv + "\" line " + line +"\n");

        for(int i=0; i<data.length; i++){
            if(!data[i].equals(""))
                log.write(data[i] + " -- ");
            else
                log.write("*** -- ");
        }

        log.write("\n\n");
        log.close();
    }
}
