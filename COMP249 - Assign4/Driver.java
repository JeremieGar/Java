// -----------------------------------------------------
// Part: 1
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------

import java.io.IOException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws IOException {

        // ----------- Part 1 -----------
        System.out.println("File Parser (Part 1)");

        Scanner scan1 = new Scanner(System.in);
        System.out.print("What is the directory path of the file you want to convert? ");
        String path = scan1.nextLine();

        Scanner scan2 = new Scanner(System.in);
        System.out.print("What file do you want to convert? ");
        String fileName = scan2.nextLine();

        FileParser.fileParse(path, fileName);
        System.out.println("\n\"distinct_data.txt\", \"obsessive_o.txt\" and \"vowel_verbiage.txt\" have been successfully created.\n");


        scan1.close();
        scan2.close();
    }
}
