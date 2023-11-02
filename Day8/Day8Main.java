package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8Main {
    /*
    Input data into a 2x ArrayList forest
    Create new ArrayList treesVisible to store matches - size n and all zeros
    Go through array left to right, right to left, down, and up
        set first height as 'max'
        if next tree > max,
            set forestMatch to 1
            max = tree
     */
    public static void main(String[] args) throws FileNotFoundException {
        // variables
        ArrayList<ArrayList<Integer>> forest = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> treesVisible = new ArrayList<ArrayList<Integer>>();

        File myFile = new File("day8.txt");
        Scanner sc = new Scanner(myFile);

        // read the data
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            //add new row
            forest.add(new ArrayList<Integer>());

            // iterate through each number in the row
            for (int i = 0; i < line.length(); i++) {
                // add each number to forest
                int treeHeight = Integer.parseInt(line.substring(i, i + 1));
                forest.get(forest.size() - 1).add(treeHeight);
            }
        }
        sc.close();

        // setup the forest grids
        Day8Main.loadForest(forest, treesVisible);

        // calculate visible
        int numberVisibleTrees = Day8Main.findVisible(forest, treesVisible);

        System.out.println("Trees Visible: " + numberVisibleTrees);

        int maxViewingScore = Day8Main.findMaxScenicScore(forest);

        System.out.println("\nMax Score: " + maxViewingScore);

    }

    public static void loadForest(ArrayList<ArrayList<Integer>> forest, ArrayList<ArrayList<Integer>> treesVisible) {
        // setup the treesVisible array
        for (int i = 0; i < forest.size(); i++) {
            // add new row arrayList
            treesVisible.add(new ArrayList<Integer>());

            // fill the arrayList with zeros
            for (int j = 0; j < forest.get(0).size(); j++) {
                treesVisible.get(i).add(0);
            }

        }
    }

    public static int findVisible(ArrayList<ArrayList<Integer>> forest, ArrayList<ArrayList<Integer>> treesVisible) {
        // dummy vars
        int treeMax = 0;

        // traverse forest left to right

        for (int i = 0; i < forest.size(); i++) {
            treeMax = 0;
            for (int j = 0; j < forest.get(0).size(); j++) {
                if (forest.get(i).get(j) > treeMax || i == 0 || j == 0 || i == forest.size() - 1 || j == forest.get(0).size() - 1) {
                    treesVisible.get(i).set(j, 1);
                    treeMax = forest.get(i).get(j);
                }
            }
        }

        // traverse forest right to left

        for (int i = 0; i < forest.size(); i++) {
            treeMax = 0;

            // start at end
            for (int j = forest.get(0).size() - 1; j >= 0; j--) {
                if (forest.get(i).get(j) > treeMax || i == 0 || j == 0 || i == forest.size() - 1 || j == forest.get(0).size() - 1) {
                    treesVisible.get(i).set(j, 1);
                    treeMax = forest.get(i).get(j);
                }
            }
        }

        // traverse top to bottom

        for (int j = 0; j < forest.get(0).size(); j++) {
            treeMax = 0;

            for (int i = 0; i < forest.size(); i++) {
                if (forest.get(i).get(j) > treeMax || i == 0 || j == 0 || i == forest.size() - 1 || j == forest.get(0).size() - 1) {
                    treesVisible.get(i).set(j, 1);
                    treeMax = forest.get(i).get(j);
                }
            }
        }

        // traverse bottom to top

        for (int j = 0; j < forest.get(0).size(); j++) {
            treeMax = 0;

            // start at botom
            for (int i = forest.size() - 1; i >= 0; i--) {
                if (forest.get(i).get(j) > treeMax || i == 0 || j == 0 || i == forest.size() - 1 || j == forest.get(0).size() - 1) {
                    treesVisible.get(i).set(j, 1);
                    treeMax = forest.get(i).get(j);
                }
            }
        }

        // sum

        int sum = 0;

        for (int i = 0; i < treesVisible.size(); i++) {
            for (int j = 0; j < treesVisible.get(0).size(); j++) {
                sum += treesVisible.get(i).get(j);
            }
        }

        return sum;
    }

    public static int findMaxScenicScore(ArrayList<ArrayList<Integer>> forest) {
        // empty array to store scenic score for each location
        ArrayList<ArrayList<Integer>> scenicScores = new ArrayList<ArrayList<Integer>>();

        int maxScore = 0;

        // traverse each location in the forest
        for (int i = 0; i < forest.size(); i++) {
            // add new array into scenicScores
            scenicScores.add(new ArrayList<Integer>());


            // go to every location in the row
            for (int j = 0; j < forest.get(0).size(); j++) {
                int up = 0;
                int down = 0;
                int left = 0;
                int right = 0;
                // look up
                for (int pos = 1; pos <= i; pos++) {
                    up++;
                    if (forest.get(i - pos).get(j) >= forest.get(i).get(j)) {
                        break;
                    }
                }

                //look down
                for (int pos = 1; pos <= (forest.size() - i - 1); pos++) {
                    down++;
                    if (forest.get(i + pos).get(j) >= forest.get(i).get(j)) {
                        break;
                    }
                }
                // look left
                for (int pos = 1; pos <= j; pos++) {
                    left++;
                    if (forest.get(i).get(j - pos) >= forest.get(i).get(j)) {
                        break;
                    }
                }
                // look right
                for (int pos = 1; pos <= (forest.size() - j - 1); pos++) {
                    right++;
                    if (forest.get(i).get(j + pos) >= forest.get(i).get(j)) {
                        break;
                    }
                }
                //record the scenic score for i, j
                // score = up *down*left*right
                int score = up * down * left * right;
                if (score > maxScore) {
                    maxScore = score;
                }
                scenicScores.get(i).add(score);
            }
        }
        return maxScore;
    }
}
