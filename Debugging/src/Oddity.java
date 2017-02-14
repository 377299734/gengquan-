import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Oddity {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */

    private Oddity() {
    }

    /**
     * Returns a modulo b using "clock arithmetic".
     *
     * @param a
     *            the first operand
     * @param b
     *            the modulus
     * @return a modulo b
     * @requires b > 0
     * @ensures mod = a mod b
     */
    private static int mod(int a, int b) {
        return (24 - Math.abs(a % b));
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        double x = 456.0;
        double y = 100.0 * 4.56; // not the integer 456 but a close approx
        if (x == y) {
            out.println("equal");
        } else {
            out.println("not equal");
        }

        final int microsPerDay = 24 * 60 * 60 * 1000 * 1000;
        final int millisPerDay = 24 * 60 * 60 * 1000;
        out.println(microsPerDay / millisPerDay);

        out.println(12345 + 54321);

        out.print(mod(-67, 24));

        out.close();

    }
}
