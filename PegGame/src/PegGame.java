import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @JakeAlvord
 *
 */
public final class PegGame {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private PegGame() {
    }

    /**
     * outputs short intro message, and rules if user desires it.
     */
    private static void intro(SimpleReader in, SimpleWriter out) {

        out.println("Hello, and welcome to the PegGame.");
        out.println();
        out.println("If you would like to read the rules, please enter 'y'.");
        out.println("Else, enter anything else and we can continue with the game.");

        String userChoice = in.nextLine();

        if (userChoice.equals("y")) {
            out.println("The rules of the game are simple: ");
            out.println("The players will choose how many pegs they both want to play with, and the number of discs for each peg.");
            out.println("The players will then alternate turns choosing a number of discs to choose from eah peg.");
            out.println("The number of discs chosen may not exceed the number of discs on the peg.");
            out.println("Also, a player cannot choose a peg that does not exist.");
            out.println("The player to take the last disc from the last peg wins.");
            out.println("Have fun!");
        } else {
            out.println("Alright then, have fun!");
        }

        out.println();

    }

    /**
     * asks user how many pegs and discs they want to play with and display grid
     * accordingly
     */
    private static int[] pegDiscChoice(SimpleReader in, SimpleWriter out) {

        out.print("Please enter the number of pegs to play with: ");
        int number = in.nextInteger();

        while (number <= 0 || number > 15) {
            out.println("Sorry, the number of pegs must be at least 1, and less than 16. Try again: ");
            number = in.nextInteger();
        }

        int[] array = new int[number];

        for (int i = 0; i < number; i++) {
            out.print("How many discs would you like for peg " + i + "? ");
            int discs = in.nextInteger();
            while (discs <= 0 || discs > 15) {
                out.println("Sorry, the number of discs must be at least 1, and less than 16. Try again: ");
                discs = in.nextInteger();
            }
            array[i] = discs;
        }

        display(out, array);
        out.println();

        return array;

    }

    /**
     * asks how many discs a user would like to remove from each peg
     */
    private static void turns(SimpleWriter out, SimpleReader in, int array[]) {

        out.println("The game is ready to begin!");
        boolean isEmpty = false;

        int player = 1;

        while (!isEmpty) {

            out.println();

            out.print("Player " + player(player, out)
                    + ", choose a peg to draw from: ");
            int choice = in.nextInteger();
            while (choice < 0 || choice > array.length) {
                out.println("Sorry, the peg number must be above 0 and less than or equal to the number of pegs you chose. Try again: ");
                choice = in.nextInteger();
            }
            out.print("And how many discs would like to remove from peg "
                    + choice + "? ");
            int discs = in.nextInteger();
            while (discs <= 0 || discs > array[choice]) {
                out.println("Sorry, the number of discs must be at least 1 and less than or equal to the discs on the set. Try again: ");
                discs = in.nextInteger();
            }

            remover(array, choice, discs);

            if (empty(array) == true) {
                isEmpty = true;
                out.println();
                out.print("Congratulations! Player " + player(player, out)
                        + " wins!");
            } else {
                display(out, array);
                player++;
            }

        }

    }

    /**
     * checks if array is empty
     */
    private static boolean empty(int array[]) {

        boolean empty = false;

        int test = 0;

        for (int j = 0; j < array.length - 1; j++) {
            if (array[j] == 0) {
                test++;
            }
        }
        if (test == array.length - 1) {
            empty = true;
        }

        return empty;
    }

    /**
     * displays grid off discs on pegs
     */
    private static void display(SimpleWriter out, int array[]) {

        for (int m = 0; m < array.length; m++) {
            out.print("Peg " + m + ": ");
            for (int p = 0; p < array[m]; p++) {
                out.print("#");
            }
            out.println();
        }

    }

    /**
     * removes discs from peg
     */
    private static int[] remover(int array[], int peg, int discs) {

        array[peg] = array[peg] - discs;

        return array;
    }

    /**
     * switches player
     */
    private static int player(int player, SimpleWriter out) {

        int number = 0;

        if (player % 2 != 0) {
            number = 1;
        } else {
            number = 2;
        }

        return number;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        intro(in, out);

        turns(out, in, pegDiscChoice(in, out));

        in.close();
        out.close();
    }

}
