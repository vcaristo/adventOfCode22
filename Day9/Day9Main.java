package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Day9Main {
    // setup and initailze array of knots
    // head = 0
    public static ArrayList<Integer[]> rope = new ArrayList<Integer[]>();

    public static void main(String[] args) throws FileNotFoundException {

        // initialize rope with 10 knots
        // knot 0 is the 'head'
        for (int i = 0; i < 10; i++) {
            Integer[] start = {0, 0};
            rope.add(start);
        }

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
                Day9Main.moveHead(dir);
                for (int j = 1; j < rope.size(); j++) {
                    Day9Main.moveTail(j - 1, j);
                }
                // update places visited
                String newTailPos = Integer.toString(rope.get(9)[0]) + " " + Integer.toString(rope.get(9)[1]);
                if (!placesVisited.contains(newTailPos)) {
                    placesVisited.add(newTailPos);
                }
            }


        }

        // print grid
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                String pos = Integer.toString(i) + " " + Integer.toString(j);
                if (placesVisited.contains(pos)) {
                    System.out.print("# ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
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

    public static void moveHead(String dir) {
        Integer[] headPos = rope.get(0);

        if (dir.equals("U")) {
            headPos[0]++;
            rope.set(0, headPos);
        } else if (dir.equals("D")) {
            headPos[0]--;
            rope.set(0, headPos);
        } else if (dir.equals("L")) {
            headPos[1]--;
            rope.set(0, headPos);
        } else if (dir.equals("R")) {
            headPos[1]++;
            rope.set(0, headPos);
        }

    }

    public static void moveTail(int leader, int follower) {
        Integer[] headPos = rope.get(leader);
        Integer[] tailPos = rope.get(follower);

        // only move if it's greater than 1 away
        if (Math.abs(headPos[0] - tailPos[0]) > 1 || Math.abs(headPos[1] - tailPos[1]) > 1) {
            if (headPos[0] > tailPos[0]) {
                // move one closer
                tailPos[0]++;
                rope.set(follower, tailPos);
            } else if (headPos[0] < tailPos[0]) {
                tailPos[0]--;
                rope.set(follower, tailPos);
            }
            if (headPos[1] > tailPos[1]) {
                tailPos[1]++;
                rope.set(follower, tailPos);
            } else if (headPos[1] < tailPos[1]) {
                tailPos[1]--;
                rope.set(follower, tailPos);
            }
        }
    }

}
t 