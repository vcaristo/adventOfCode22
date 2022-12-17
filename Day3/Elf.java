package Day3;

public class Elf {

    //stores the characters in the rucksack
    String rucksack;

    // the item that is shared in both sides of the rucksack
    char sharedItem;

    // constructor
    // set the
    public Elf(String sack) {
        // set the string to sack
        this.rucksack = sack;

        // find the sharedItem
        this.sharedItem = findSharedItem(sack);
    }

    // find the shared item
    private char findSharedItem(String rucksack) {

        // save the first half of the rucksack
        String firstHalf = rucksack.substring(0, rucksack.length() / 2);

        // iterate through the 2nd half
        for (int i = rucksack.length() / 2; i < rucksack.length(); i++) {
            if (firstHalf.contains(rucksack.substring(i, i + 1))) {
                return rucksack.charAt(i);
            }
        }

        // needed to compile
        return '0';
    }

    public char getSharedItem() {
        return this.sharedItem;
    }
}
