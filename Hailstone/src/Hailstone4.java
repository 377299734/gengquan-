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
public final class Hailstone4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone4() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     *
     * @param n
     *            the starting number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(int n, SimpleWriter out) {

        out.print(n + " ");
        int count = 1;
        int max = n;

        while (n > 1) {
            if (n % 2 == 1) {
                n = 3 * n + 1;
                if (n > max) {
                    max = n;
                }
                count++;
                out.print(n + " ");
            } else if (n % 2 == 0) {
                n = n / 2;
                if (n > max) {
                    max = n;
                }
                count++;
                out.print(n + " ");
            }
        }
        out.println();
        out.println("The length of the series is " + count + " numbers long.");
        out.println("The maximum value of the series is " + max + ".");
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

        out.print("Please enter a positive integer: ");
        int input = in.nextInteger();

        generateSeries(input, out);

        out.print("Do you wish to compute a new series? Enter y for yes.  ");
        String userAnswer = in.nextLine();
        int newInput;

        while (userAnswer.equals("y")) {
            out.print("Please enter a positive integer: ");
            newInput = in.nextInteger();
            out.println();
            generateSeries(newInput, out);

            out.print("Do you wish to compute a new series? Enter y for yes.  ");
            userAnswer = in.nextLine();
        }

        in.close();
        out.close();
    }
}
