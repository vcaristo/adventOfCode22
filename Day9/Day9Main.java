package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Day9Main {
    public static void main(String[] args) throws FileNotFoundException {

        //vars
        int[] head = new int[2];  // inits to 0
        int[] tail = new int[2];


        head[0] = 0;
        tail[0] = 0;
        tail[1] = 0;
        tail[0] = 0;

        // read file
        File myFile = new File("day9.txt");

        Scanner sc = new Scanner(myFile);

        // initialize tracker for places visited by the tail
        // (x, y), 1
        ArrayList<String> placesVisited = new ArrayList<String>();
        placesVisited.add("0 0");

        while (sc.hasNextLine()) {
            // read and parse a line
            Map<String, Integer> move = Day9Main.readLine(sc.nextLine());

            String dir = (String) move.keySet().toArray()[0];
            int numMoves = move.get(dir);

            // move the head and tail
            for (int i = 0; i < numMoves; i++) {
                Day9Main.moveHead(dir, head);
                Day9Main.moveTail(head, tail);

                // update places visited
                String newTailPos = Integer.toString(tail[0]) + " " + Integer.toString(tail[1]);
                if (!placesVisited.contains(newTailPos)) {
                    placesVisited.add(newTailPos);
                }

            }

        }

        for (String s : placesVisited) {
            System.out.println(s);
        }
        System.out.println("Number locations visited:" + placesVisited.size());

    }

    public static Map<String, Integer> readLine(String line) {
        String dir = line.substring(0, 1);
        int num = Integer.parseInt(line.substring(2));

        Map<String, Integer> dirs = new HashMap<>();
        dirs.put(dir, num);

        return dirs;
    }

    public static void moveHead(String dir, int[] headPos) {
        if (dir.equals("U")) {
            headPos[0]++;
        } else if (dir.equals("D")) {
            headPos[0]--;
        } else if (dir.equals("L")) {
            headPos[1]--;
        } else if (dir.equals("R")) {
            headPos[1]++;
        }

    }

    public static void moveTail(int[] headPos, int[] tailPos) {
        // only move if it's greater than 1 away
        if (Math.abs(headPos[0] - tailPos[0]) > 1 || Math.abs(headPos[1] - tailPos[1]) > 1) {
            if (headPos[0] > tailPos[0]) {
                // move one closer
                tailPos[0]++;
            } else if (headPos[0] < tailPos[0]) {
                tailPos[0]--;
            }
            if (headPos[1] > tailPos[1]) {
                tailPos[1]++;
            } else if (headPos[1] < tailPos[1]) {
                tailPos[1]--;
            }
        }
    }

}
