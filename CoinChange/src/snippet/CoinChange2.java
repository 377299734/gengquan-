package snippet;

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
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange2() {
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

        out.print("How many cents would you like to make change for? ");
        int amount = in.nextInteger();

        int denominations[] = { 100, 50, 25, 10, 5, 1 };
        int coinCount[] = new int[6];

        for (int count = 0; count < denominations.length; count++) {

            coinCount[count] = amount / denominations[count];
            amount = amount % denominations[count];

        }

        out.println("Number of dollars: " + coinCount[0]);

        out.println("Number of half dollars: " + coinCount[1]);

        out.println("Number of quarters: " + coinCount[2]);

        out.println("Number of dimes: " + coinCount[3]);

        out.println("Number of nickel: " + coinCount[4]);

        out.println("Number of pennies: " + coinCount[5]);

        in.close();
        out.close();
    }

}
