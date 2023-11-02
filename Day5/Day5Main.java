package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5Main {
    public static void main(String[] args) throws FileNotFoundException {
        /*
            initiate an instance of a Ship class
                ArrayList<LinkedList<char>> stacks
                public void add(String line)
                public void move(int number, int from, into to)
                public String topCrates();

            read the first 8 lines
            pass each line to the ship class
                myShip.add(String line)
                    put each letter into an array of linked lists:
                    ArrayList<LinkedList<char>> stacks;

            read and parse each instruction
                public static int[] readinstructions()
            execute each instruction
                myShip.move(int number, int from, int to)

            get the message with the top crate on each stack
                myShip.topCrates()
         */
        File myFile = new File("day5.txt");

        Scanner sc = new Scanner(myFile);

        Ship myShip = new Ship();

        // read each line of plays
        for (int i = 0; i < 8; i++) {
            try {
                // read the line
                String line = sc.nextLine();

                // add stack to the ship
                myShip.add(line);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        System.out.println("Start: " + myShip.topCrates());

        while (sc.hasNextLine()) {
            try {
                String line = sc.nextLine();

                int[] instructions = Day5Main.readInstructions(line);

                if (instructions != null) {
                    // execute instructions
                    myShip.move2(instructions[0], instructions[2], instructions[1]);
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        System.out.println("Finish: " + myShip.topCrates());


    }

    public static int[] readInstructions(String line) {
        int[] instr = new int[3];

        //regex for a the input strings
        String regex = "\\bmove\\s{1}([1-9][0-9]?)\\s{1}from\\s{1}([1-9])\\s{1}to\\s{1}([1-9])\\b";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);

        while (m.find()) {
            for (int i = 0; i < 3; i++) {
                instr[i] = Integer.parseInt(m.group(i + 1));
            }
            return instr;
        }

        return null;

    }
}
