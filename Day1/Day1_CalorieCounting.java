import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Day1_CalorieCounting {

    /**
     * Read the text file line by line
     * Updates max elf along the way
     * uses a flag variable to get around limit var scope of catch
     */

    public static int topElf() throws FileNotFoundException {

        // create file object
        File myFile = new File("day1.txt");

        // open scanner with the file object
        Scanner sc = new Scanner(myFile);

        /**
         *  variable to hold the sum of each elf's calories
         *  resets to zero when a line break is encountered
         */
        int counter = 0;

        /**
         *  holds the value of the elf with the most calories
         */
        int max = 0;

        /**
         * Flag variable to indicate the catch block was triggered
         */
        int valid = 1;

        while (sc.hasNextLine()) {
            try {
                valid = 0;
                int next = Integer.parseInt(sc.nextLine());
                counter += next;    // it's an int - update counter and set valid to 1
                valid = 1;
            } catch (Exception e) {
            } finally {
                if (valid == 0) {   // it was a blank line
                    
                    if (counter > max) {
                        max = counter;
                    }
                    //reset counter
                    counter = 0;
                }
            }
        }

        sc.close();

        return max;

    }

    public static int top3Elves() throws FileNotFoundException {

        File myFile = new File("day1.txt");

        Scanner sc = new Scanner(myFile);

        /**
         * list to store the values of the top 3 elves (in descending order)
         * updated whenever a new elf has a total that's greater than one of them
         */
        ArrayList<Integer> topElves = new ArrayList<>();

        /**
         *  variable to hold the sum of each elf's calories
         *  resets to zero when a line break is encountered
         */
        int counter = 0;

        /**
         * Flag variable to indicate the catch block was triggered
         */
        int valid = 1;

        // elves are differentiated by a blank line...need to
        while (sc.hasNextLine()) {
            // try to cast line to an int
            try {
                valid = 0;
                int next = Integer.parseInt(sc.nextLine());
                counter += next;        // it's an int - update counter and set valid to 1
                valid = 1;
            } catch (Exception e) {
            } finally {
                if (valid == 0) {  // it was a blank line
                    topElves = top3Recorder(counter, topElves);
                    counter = 0;
                }
            }
        }

        sc.close();

        // return calories of the top 3
        int sum = 0;
        for (Integer i : topElves) {
            sum += i;
        }

        return sum;

    }

    /**
     * tests whether an elf's calories are in the top 3
     * if yes, updates the 3x array
     */
    public static ArrayList<Integer> top3Recorder(int nextElf, ArrayList<Integer> topElves) {
        //initial adding
        if (topElves.size() < 3) {
            topElves.add(nextElf);

            // sort ascending 
            topElves.sort(Comparator.naturalOrder());

            return topElves;
        }

        //else check nextElf against the lowest of the top 3
        else {
            if (nextElf > topElves.get(0)) {
                topElves.set(0, nextElf);
                // re-sort
                topElves.sort(Comparator.naturalOrder());
            }
            return topElves;
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.out.println(top3Elves());
    }
}
