package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4Main {
    public static void main(String[] args) throws FileNotFoundException {
        /*
            initialize a counter
            read each pair
            check if one fully contains the other
            add to counter if it does

         */

        // counter
        int counter = 0;

        // open file
        File myFile = new File("day4.txt");

        // open scanner
        Scanner sc = new Scanner(myFile);

        String str = "57-45,90-57 8-9,5-7";

        // read each line of plays
        while (sc.hasNextLine()) {
            try {
                // read the line
                String pair = sc.nextLine();

                // check if pairs fully contain
                if (Day4Main.overlaps(pair)) {
                    counter++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        System.out.println("Number of pairs that fully contain: " + counter);
    }

    public static boolean overlaps(String pair) {
        //regex for a the input strings
        String regex = "\\b([1-9][0-9]?)-([1-9][0-9]?),([1-9][0-9]?)-([1-9][0-9]?)\\b";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pair);

        int[] seats = new int[4];

        while (m.find()) {
            for (int i = 0; i < 4; i++) {
                seats[i] = Integer.parseInt(m.group(i + 1));
            }
        }

        if (((seats[1] >= seats[2]) && (seats[0] <= seats[3]))) {
            return true;
        }

        return false;
    }

    public static boolean fullyContains(String pair) {
        //regex for a the input strings
        String regex = "\\b([1-9][0-9]?)-([1-9][0-9]?),([1-9][0-9]?)-([1-9][0-9]?)\\b";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pair);

        int[] seats = new int[4];

        while (m.find()) {
            for (int i = 0; i < 4; i++) {
                seats[i] = Integer.parseInt(m.group(i + 1));
            }
        }

        if (((seats[0] >= seats[2]) && (seats[1] <= seats[3])) ||
                ((seats[0] <= seats[2]) && (seats[1] >= seats[3]))) {
            return true;
        }

        return false;
    }

}
