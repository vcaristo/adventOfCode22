package Day5;

import java.util.ArrayList;
import java.util.LinkedList;

/*
Represents a ship with stacks of crates
Ability to add new stacks
Ability to move between stacks
Function to return the top crate in each stack
 */
public class Ship {

    // stores an array of stacks on the ship
    public ArrayList<LinkedList<Character>> stacks;

    //constructor
    public Ship() {

        // initialize the 9 stacks
        stacks = new ArrayList<LinkedList<Character>>();

        for (int i = 0; i < 9; i++) {
            stacks.add(new LinkedList<Character>());
        }
    }

    /*
    adds a new stacks
    receives string input from the file
    parses the input to add a new linkedlist to stacks
     */
    public void add(String line) {
        
        // iterate through each of the values in the 9 stacks
        for (int i = 1; i < 35; i += 4) {
            // if it's a character, add to a list
            if (Character.isAlphabetic(line.charAt(i))) {
                // add the item to stack i/4
                LinkedList<Character> newStack = this.stacks.get(i / 4);
                newStack.add(line.charAt(i));
                this.stacks.set(i / 4, newStack);
            }
        }
    }

    /*
    moves crates between stacks
     */
    public void move(int number, int to, int from) {

        LinkedList<Character> toStack = stacks.get(to - 1);
        LinkedList<Character> fromStack = stacks.get(from - 1);

        for (int i = 0; i < number; i++) {
            toStack.add(0, fromStack.get(0));

            //remove first from the from stack
            fromStack.remove(0);
        }

        stacks.set(to - 1, toStack);
        stacks.set(from - 1, fromStack);
    }

    /*
    moves crates between stacks
     */
    public void move2(int number, int to, int from) {

        LinkedList<Character> toStack = stacks.get(to - 1);
        LinkedList<Character> fromStack = stacks.get(from - 1);

        for (int i = number - 1; i >= 0; i--) {
            toStack.add(0, fromStack.get(i));

            //remove first from the from stack
            fromStack.remove(i);
        }

        stacks.set(to - 1, toStack);
        stacks.set(from - 1, fromStack);
    }

    /*
    Returns the top crate in each stack
     */
    public String topCrates() {
        String topCratesString = "";

        for (int i = 0; i < 9; i++) {
            topCratesString = topCratesString + stacks.get(i).get(0);
        }

        return topCratesString;
    }

}
