package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3Main {

    // list with all of the elves
    //ArrayList<Elf> listOfElves = new ArrayList<Elf>();

    // create an instance of the class and run the program
    public static void main(String[] args) throws FileNotFoundException {
        new Day3Main().run();
    }

    // runs the program
    public void run() throws FileNotFoundException {
        /*
            open the file
            read each line
            create a new elf with each stirng
            add the priority to the counter
            print the counter
         */

        // create file object
        File myFile = new File("day3.txt");

        // open scanner with the file object
        Scanner sc = new Scanner(myFile);

        // set a counter
        int sumOfPriorities = 0;

        // read each line of plays
        while (sc.hasNextLine()) {
            try {
                // read the line
                String rucksack = sc.nextLine();

                //create a new elf
                Elf newElf = new Elf(rucksack);

                // sum the priorities
                sumOfPriorities += this.itemPriority(newElf.getSharedItem());


            } catch (Exception e) {
                System.out.println(e);
            }
        }

        //close the scanner
        sc.close();

        //print the sum of priorities
        System.out.println("Sum of priorities: " + sumOfPriorities);


    }

    // returns the priority of the item
    // know values are a-z or A-Z
    public int itemPriority(char item) {
        // A-Z 27 to 52
        if (item < 97) {
            return item - 38;
        } else {
            return item - 96;
        }
    }

}
