import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Day2_RockPaperScissor {
    /*
    stores my total score
     */
    int myTotal;

    /*
    my score for each single round combination of R, P, and C
    score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
    plus the score for the outcome of the round (0 if you lost, 3 if the round was
    a draw, and 6 if you won)
     */
    static public int[][] scoreGrid = {{4, 8, 3}, {1, 5, 9}, {7, 2, 6}};

    static public Map<String, Integer> playCodes = Map.of("A", 0, "X", 0, "B", 1, "Y", 1, "C", 2, "Z", 2);

    // Part 2. My play given the codes, from playCodes1
    static public int[][] myPlay = {{2, 0, 1}, {0, 1, 2}, {1, 2, 0}};

    /*
    returns an array with the rolls for a round
    index 0 is your roll
    index 1 is my rolls
     */
    public int[] readNextPlay(Scanner c) {

        // read two plays and maps them to their playcode
        int[] rolls = {playCodes.get(c.next()), playCodes.get(c.next())};

        return rolls;

    }

    // add the score total for a round of plays
    public void oneRound(int[] plays) {

        this.myTotal += scoreGrid[plays[0]][plays[1]];
    }

    //constructor
    public Day2_RockPaperScissor() {
        this.myTotal = 0;

    }

    public static void main(String[] args) throws FileNotFoundException {
        File myFile = new File("day2.txt");

        Scanner sc = new Scanner(myFile);

        Day2_RockPaperScissor game = new Day2_RockPaperScissor();

        // read each line of plays
        while (sc.hasNext()) {
            try {
                int[] plays = game.readNextPlay(sc);

                // adjust my play for part 2
                plays[1] = Day2_RockPaperScissor.myPlay[plays[0]][plays[1]];

                // add the score total for the play codes
                game.oneRound(plays);

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        sc.close();

        //print the final score
        System.out.println("Your score: " + game.myTotal);
    }
}
