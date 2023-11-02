package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3Main_2 {

    // list with all of the groups of elves
    ArrayList<ArrayList<Elf>> elfGroups = new ArrayList<ArrayList<Elf>>();

    // groups of 3 elves
    ArrayList<Elf> groupOf3;

    // create an instance of the class and run the program
    public static void main(String[] args) throws FileNotFoundException {
        new Day3Main_2().run();
    }

    public void run() throws FileNotFoundException {
        /*
            open the file
            read each line
            create a new elf with each string
            add each elf to its group in the list
            go through each group in the list and find the common item
            add its priority to the counter
            print the counter
         */

        File myFile = new File("day3.txt");

        Scanner sc = new Scanner(myFile);

        int sumOfPriorities = 0;

        // read each line of plays
        while (sc.hasNextLine()) {

            //initalize group of elves
            groupOf3 = new ArrayList<Elf>();

            for (int i = 0; i < 3; i++) {
                try {
                    String rucksack = sc.nextLine();

                    Elf newElf = new Elf(rucksack);

                    groupOf3.add(newElf);

                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            // add group to the elf list
            elfGroups.add(groupOf3);
        }

        // go through group of elves and add their common items
        for (ArrayList<Elf> a : elfGroups) {
            sumOfPriorities += itemPriority(findCommon(a));
        }

        sc.close();

        System.out.println("Sum of priorities: " + sumOfPriorities);


    }

    // find the common item in a group of 3 elves
    public char findCommon(ArrayList<Elf> group) {
        for (int i = 0; i < group.get(0).rucksack.length(); i++) {
            if (group.get(1).rucksack.contains(group.get(0).rucksack.substring(i, i + 1))) {
                if (group.get(2).rucksack.contains(group.get(0).rucksack.substring(i, i + 1))) {
                    return group.get(0).rucksack.charAt(i);
                }
            }
        }

        //needed to compile
        return 0;
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
