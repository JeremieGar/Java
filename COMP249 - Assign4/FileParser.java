// -----------------------------------------------------
// Part: 1
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FileParser {

    public static void fileParse(String path, String fileName) throws IOException{

        Scanner read;
        PrintWriter write1;
        PrintWriter write2;
        PrintWriter write3;
        String file = path + fileName;

        try{

            String line;
            write1 = new PrintWriter(new FileOutputStream(path + "vowel_verbiage.txt"));
            write2 = new PrintWriter(new FileOutputStream(path + "obsessive_o.txt"));
            write3 = new PrintWriter(new FileOutputStream(path + "distinct_data.txt"));
            read = new Scanner(new FileInputStream(file));

            ArrayList<String> vowels = new ArrayList<>();
            ArrayList<String> obsessive_o = new ArrayList<>();
            ArrayList<String> distinct = new ArrayList<>();

            do{
                line = read.nextLine();
                String[] words = line.split("\\s+");

                for(int i=0; i< words.length; i++) {

                    words[i] = words[i].replaceAll("[^a-zA-Z0-9]", "").trim();

                    if(threeVowels(words[i]))
                        vowels.add(words[i]);

                    if(startsWithO(words[i]))
                        obsessive_o.add(words[i]);

                    if(!distinct.contains(words[i]))
                        distinct.add(words[i]);

                }

            }while(read.hasNextLine());

            //filling vowel_verbiage.txt
            write1.println("Word Count: " + vowels.size());
            for(int i=0; i<vowels.size(); i++)
                write1.println(vowels.get(i));

            //filling obsessive_o.txt
            write2.println("Word Count: " + obsessive_o.size());
            for(int i=0; i<obsessive_o.size(); i++)
                write2.println(obsessive_o.get(i));

            //filling distinct_data.txt
            write3.println("Word Count: " + distinct.size());
            for(int i=0; i<distinct.size(); i++)
                write3.println(distinct.get(i));

        }
        catch (FileNotFoundException e){
            throw new IOException(  "Could not open \"" + file + "\"\n" +
                                    "Please check if file exists! Program will terminate after closing all open files.");
        }

        read.close();
        write1.close();
        write2.close();
        write3.close();
    }

    public static boolean threeVowels(String word){

        int vowels = 0;
        word = word.toLowerCase();

        for(int i=0; i<word.length(); i++)
            if(word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u')
                vowels++;

        if(vowels > 3)
            return true;
        else
            return false;
    }

    public static boolean startsWithO(String word){

        word = word.toLowerCase();
        if(word == "")
            return false;
        if(word.charAt(0) == 'o')
            return true;
        else
            return false;
    }

}
