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
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {
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

        int dollar = 100, halfDollar = 50, quarter = 25, dime = 10, nickel = 5, penny = 1;

        out.println("Number of dollars: " + amount / dollar);

        if (amount > dollar) {
            amount = amount % dollar;
        }

        out.println("Number of half dollars: " + amount / halfDollar);

        if (amount > halfDollar) {
            amount = amount % halfDollar;
        }

        out.println("Number of quarters: " + amount / quarter);

        if (amount > quarter) {
            amount = amount % quarter;
        }

        out.println("Number of dimes: " + amount / dime);

        if (amount > dime) {
            amount = amount % dime;
        }

        out.println("Number of nickel: " + amount / nickel);

        if (amount > nickel) {
            amount = amount % nickel;
        }

        out.println("Number of pennies: " + amount / penny);

        in.close();
        out.close();
    }

}
