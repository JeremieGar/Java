// -----------------------------------------------------
// Part: 2
// Written by: Jeremie Garzon 40062316
// -----------------------------------------------------

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class ProcessWishlist {

    public static void main(String[] args) throws IOException{

        ShowList list1 = new ShowList();
        ShowList list2 = new ShowList();

        Scanner guide, interests;
        Scanner input = new Scanner(System.in);
        System.out.print("What is the directory of the files you want accessed? ");
        String url = input.nextLine();
        System.out.print("What is the name of the TVGuide file you want accessed? ");
        String file1 = input.nextLine();
        System.out.print("What is the name of the Interests file you want accessed? ");
        String file2 = input.nextLine();


        try{

            guide = new Scanner(new FileInputStream(url+file1));
            String line;

            while(guide.hasNextLine()){

                line = guide.nextLine();
                String[] arr1 = line.split(" ");
                line = guide.nextLine();
                String[] arr2 = line.split(" ");
                line = guide.nextLine();
                String[] arr3 = line.split(" ");
                guide.nextLine();

                list1.addToEnd(new TVShow(arr1[0],arr1[1], Double.parseDouble(arr2[1]), Double.parseDouble(arr3[1])));

            }

            interests = new Scanner(new FileInputStream(url+file2));
            String line2;

            while(interests.hasNextLine()){



            }


        }catch(FileNotFoundException e) {
            throw new IOException("Make sure a TVGuide.txt file exists in your directory.");
        }
    }

}
