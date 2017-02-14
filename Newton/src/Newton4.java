import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * User is prompted repeatedly to enter a positive number to compute the square
 * root, the user enters a positive number and the program will output the
 * square root with less than user entered error. If the user enters a negative
 * number then the program will quit.
 *
 * @JakeAlvord
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x, double e) {

        double newGuess = x; // initial guess set to users enters
        if (x == 0) {
            newGuess = 0;
        } else {
            while ((Math.abs(newGuess * newGuess - x)) / x > e * e) {
                newGuess = (newGuess + x / newGuess) / 2;
                // program repeatedly changes the guess until it less than 0.000000001
            }
        }

        return newGuess; // returns the refined square root approximate

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

        out.print("Please enter a positive number, negative number to quit: ");
        double possibleNumber = in.nextDouble(); // user enters number to square root

        out.print("Please enter Epsilon: ");
        double epsilon = in.nextDouble();
        out.println();

        while (possibleNumber > 0) {

            out.println();
            out.print("The square root of " + possibleNumber + " is "
                    + sqrt(possibleNumber, epsilon));
            // calls the function to output the approximate square root

            out.println();

            out.print("Please enter a new positive value (to exit enter a negative number): ");
            possibleNumber = in.nextDouble();
            // prompts user whether they want to keep computing more square roots
        }

        in.close();
        out.close();
    }

}
