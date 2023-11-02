package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Day6Main {
    /*
    read first 4 characters and add to linked list
        if 4th is repeated, return the marker
    read next character
    check if its in the last 3 of the linked list
        if it's repeated, return the position


     */
    public static void main(String[] args) throws FileNotFoundException {
        /* test reading single characters
         */
        File myFile = new File("day6.txt");

        Scanner sc = new Scanner(myFile).useDelimiter("(?<=.)");

        System.out.println("Start of packet: " + Day6Main.findStart(sc));

    }

    public static int findStart(Scanner s) {
        int counter = 0;

        // set a linked list. will be size 4
        LinkedList<Character> packet = new LinkedList<Character>();

        // counter
        int i = 0;

        while (s.hasNext()) {
            try {
                if (packet.size() < 14) {
                    packet.add(s.next().charAt(0));
                } else {
                    // check if packet is unique
                    if (Day6Main.checkUnique(packet)) {
                        return i;
                    } else {
                        Character item = s.next().charAt(0);
                        packet.remove(0);
                        packet.add(item);

                    }
                }
                i++;
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return -1;
    }

    public static boolean checkUnique(LinkedList<Character> packet) {
        for (int i = 0; i < packet.size() - 1; i++) {
            if (packet.subList(i + 1, packet.size()).contains(packet.get(i))) {
                return false;
            }
        }

        return true;
    }
}
