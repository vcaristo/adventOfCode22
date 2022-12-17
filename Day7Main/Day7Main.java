package Day7Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7Main {
    /*
    create root directory
    read each line in the file
    execute the lines
    print out size of root
     */
    public static void main(String[] args) throws FileNotFoundException {
        // open file for input
        File myFile = new File("day7.txt");

        Scanner sc = new Scanner(myFile);

        // create root and set as 'active'
        sc.nextLine();  // read the first line
        Directory root = new Directory("root", null);
        Directory active = root;

        // read each line in the file

        while (sc.hasNextLine()) {
            try {
                String line = sc.nextLine();

                // parse the line
                String[] instr = Day7Main.parseStatement(line);

                // execute the instructions
                // cd
                if (instr[0].equals("cd")) {
                    // set active to parent for 'cd ..'
                    if (instr[1].equals("..")) {
                        if (active.getParent() != null) {
                            active = active.getParent();
                        }
                    }
                    // set active to given directory name
                    else {
                        if (!active.dirs.containsKey(instr[1])) {
                            active.addDir(instr[1]);
                        }
                        active = active.getDir(instr[1]);
                    }
                } else if (instr[0].equals("dir")) {
                    // if directory doesn't exist, then create it
                    if (!active.dirs.containsKey(instr[1])) {
                        active.addDir(instr[1]);
                    }
                } else if (instr[0].equals("ls")) {
                    // do nothing
                } else {
                    // it's a file
                    int newInt = Integer.parseInt(instr[1]);
                    if (!active.files.contains(newInt)) {   // will mess up if files have the same size
                        active.addFile(newInt);
                    }
                }


            } catch (Exception e) {
                System.out.println(e);
            }
        }
        sc.close();

        // print the size of root
        int sizeOfRoot = root.getSize();

        System.out.println("Size of file system: " + sizeOfRoot);

        //Day7Main.printFileSizes(root);

        // Find all under 100,000

        ArrayList<Integer> dirsUnder100k = new ArrayList<>();
        int sum = 0;

        Day7Main.findDirs(root, 100000, dirsUnder100k, "<=");

        for (int i = 0; i < dirsUnder100k.size(); i++) {
            sum += dirsUnder100k.get(i);
        }

        System.out.println("Sum of dirs under 100k: " + sum);

        // find folder to remove
        ArrayList<Integer> dirsOverSpaceNeeded = new ArrayList<>();
        int spaceNeeded = 30000000 - (70000000 - sizeOfRoot);

        System.out.println("\nSpace Needed: " + spaceNeeded);

        Day7Main.findDirs(root, spaceNeeded, dirsOverSpaceNeeded, ">=");

        Collections.sort(dirsOverSpaceNeeded);

        System.out.println("Smallest directory to delete: " + dirsOverSpaceNeeded.get(0));


    }

    public static void findDirs(Directory directory, int fileSize, ArrayList<Integer> dirsResult, String dir) {
        if (dir.equals("<=")) {
            if (directory.size <= fileSize) {
                dirsResult.add(directory.size);
            }
            for (String name : directory.dirs.keySet()) {
                Day7Main.findDirs(directory.dirs.get(name), fileSize, dirsResult, dir);
            }
        } else if (dir.equals(">=")) {
            if (directory.size >= fileSize) {
                dirsResult.add(directory.size);
            }
            for (String name : directory.dirs.keySet()) {
                Day7Main.findDirs(directory.dirs.get(name), fileSize, dirsResult, dir);
            }
        }
    }

    public static void printFileSizes(Directory directory) {
        System.out.print(directory.name + ": " + directory.size);
        if (directory.size >= 30000000) {
            System.out.print(" *** \n");
        } else {
            System.out.print("\n");
        }
        for (String name : directory.dirs.keySet()) {
            Day7Main.printFileSizes(directory.dirs.get(name));
        }
    }

    public static String[] parseStatement(String line) {

        // ls, cd , dir, file
        String[] regexes = {".*ls", "(.*)\\s{1}cd\\s{1}(.*)", "dir\\s{1}(.*)", "([0-9]*)\\s{1}(.*)"};

        Pattern p = Pattern.compile("");
        Matcher m = p.matcher("");

        int pattern = 0;

        for (int i = 0; i < 4; i++) {
            p = Pattern.compile(regexes[i]);
            m = p.matcher(line);

            if (m.matches()) {
                pattern = i;
                break;
            }
        }

        String[] returnValue = {"", ""};

        if (pattern == 0) {
            returnValue[0] = "ls";
        } else if (pattern == 1) {
            returnValue[0] = "cd";
            returnValue[1] = m.group(2);
        } else if (pattern == 2) {
            returnValue[0] = "dir";
            returnValue[1] = m.group(1);
        } else if (pattern == 3) {
            returnValue[0] = "file";
            returnValue[1] = m.group(1);
        }

        return returnValue;

    }
}
