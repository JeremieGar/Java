// -----------------------------------------------------
// COMP249 - Winter 2021 Assignment 3
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Assignment3 {

    /**
     * The program's main uses a scanner to ask for how many, and which files need to be converted. Then uses a for loop to use the CSV2JSON method as many times as needed
     * @param args
     * @throws InvalidException
     * @throws IOException
     */

    public static void main(String[] args) throws InvalidException, IOException{

        Scanner scan1 = new Scanner(System.in);
        System.out.print("How many files will be converted? ");
        int fileAmount = scan1.nextInt();

        Scanner scan3 = new Scanner(System.in);
        System.out.print("\nWhat is the directory path of all the files? ");
        String filePath = scan3.nextLine();

        Scanner scan2 = new Scanner(System.in);
        for(int i=0;i<fileAmount; i++) {
            System.out.print("\nWhat file do you want to convert? ");
            String fileName = scan2.nextLine();

            String csvName = (filePath + fileName + ".csv");
            String jsonName = (filePath + fileName + ".json");

            CSV2JSON(csvName, jsonName, filePath);
        }

        System.out.println("\nAll files have been converted, check your folder for the JSON files!");
        scan1.close();
        scan2.close();
        scan3.close();
    }

    /**
     * Takes as a parameter the csv, json file names and the filepath to both
     * @param csv CSV file name
     * @param json JSON file name to be created
     * @param filePath directory path of all files involved
     * @throws InvalidException
     * @throws IOException
     */
    public static void CSV2JSON(String csv, String json, String filePath) throws InvalidException, IOException {

        BufferedReader read;
        PrintWriter write;

        try{

            String line;
            read = new BufferedReader(new FileReader(csv));
            write = new PrintWriter (new BufferedWriter(new PrintWriter(json)));

            //Reads the first line in the file and uses a regex split to create a String array of the categories
            line = read.readLine();
            String[] carRecordCategories = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            //Checks if any categories are missing and throws a CSVFileInvalidException if need be
            int missingCategoryCount = 0;
            for(int i=0; i<carRecordCategories.length; i++)
                if(carRecordCategories[i].equals(""))
                    missingCategoryCount++;

            if(missingCategoryCount>0)
                throw new CSVFileInvalidException( "File \"" + csv + "\" is invalid: field is missing.\n File is not converted to JSON.", filePath,
                                                    csv, carRecordCategories.length, missingCategoryCount, carRecordCategories);

            //start of the json file
            write.println("[");

            int lineCount = 1;
            line = read.readLine();

            while(line != null){

                //splits each line's data into a string array
                String[] carRecordInfo = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                lineCount++;

                //if any data is found to be empty, a CSVDataMissing exception is thrown
                for(int i=0; i<carRecordInfo.length; i++)
                    if(carRecordInfo[i].equals(""))
                        throw new CSVDataMissing("In file \"" + csv + "\" line " + lineCount + " not converted to JSON: missing data", filePath,
                                csv, lineCount, carRecordInfo);

                write.println("\t{");

                for(int i = 0; i<carRecordCategories.length; i++){
                    if(i<(carRecordCategories.length-1))
                        write.println("\t\t\"" + carRecordCategories[i] + "\": " + carRecordInfo[i] + ",");
                    else
                        write.println("\t\t\"" + carRecordCategories[i] + "\": " + carRecordInfo[i]);
                }

                if((line = read.readLine()) != null)
                    write.println("\t},");
                else
                    write.println("\t}");
            }
            write.println("]");
            System.out.println("\"" + json + "\" has been successfully created");

        }
        catch(FileNotFoundException e){
            throw new InvalidException( "Could not open \"" + csv + "\" for reading. \n" +
                                        "Please check if file exists! Program will terminate after closing all open files.");
        }

        read.close();
        write.close();

    }
}





