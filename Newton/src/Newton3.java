import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * User is prompted repeatedly if they want to compute a square root, the user
 * enters a number and the program will output the square root with less than
 * user entered error.
 *
 * @JakeAlvord
 *
 */
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
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
                // program repeatedly changes the guess until it less than user entered epsilon squared
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

        out.print("Do you wish to compute a square root? Enter y for yes.  ");
        String userAnswer = in.nextLine(); // user enters if they want to compute a square root

        out.println();
        out.print("Please enter Epsilon: ");
        double epsilon = in.nextDouble(); // user enters desired error checking number
        out.println();

        while (userAnswer.equals("y")) {

            out.print("Please enter a positive number: ");
            double possibleNumber = in.nextDouble(); // user enters number to square root

            out.println();
            out.print("The square root of " + possibleNumber + " is "
                    + sqrt(possibleNumber, epsilon));
            // calls the function to output the approximate square root

            out.println();

            out.print("Do you wish to compute another square root? Enter y for yes.  ");
            userAnswer = in.nextLine();
            // prompts user whether they want to keep computing more square roots
        }

        in.close();
        out.close();
    }

}
