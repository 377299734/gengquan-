import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hailstone3() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {

        out.print(n + " ");

        NaturalNumber ONE = new NaturalNumber2(1);
        NaturalNumber TWO = new NaturalNumber2(2);
        NaturalNumber THREE = new NaturalNumber2(3);
        NaturalNumber r;
        NaturalNumber max = new NaturalNumber2(n);

        int count = 1;

        while (!n.equals(ONE)) {
            r = n.divide(TWO);
            if (r.equals(ONE)) {
                n.multiply(TWO);
                n.add(ONE);
                n.multiply(THREE);
                n.add(ONE);
                out.print(n + " ");
                count++;
                if (n.compareTo(max) > 0) {
                    max.copyFrom(n);
                }
            } else {
                out.print(n + " ");
                count++;
            }
        }

        out.println();
        out.println("The length of the series is: " + count);
        out.print("The max of the series is: " + max);
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
        String userIn = in.nextLine();
        out.println();

        NaturalNumber input = new NaturalNumber2(userIn);

        generateSeries(input, out);

        in.close();
        out.close();
    }
}
