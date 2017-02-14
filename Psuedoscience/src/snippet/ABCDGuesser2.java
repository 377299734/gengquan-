package snippet;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * User inputs a desired positive number to round to, then inputs four more
 * positive numbers (that when added together do not equal 1), the program will
 * put each of these numbers to an exponent and multiply them together so that
 * the approximated number is less than 1% away from the first desired constant
 * that was inputed. The program displays the exponent numbers used to achieve
 * this and the approximated number.
 *
 * @JakeAlvord
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * errorCalculation
     *
     * @param poserror
     *            the error from the approximation of the constant
     * @param usercon
     *            user's first entered constant
     * @param result
     *            approximation from algorithm
     * @requires all user input > 0
     * @ensures <pre>
     * error < 0.1%
     * [the approximation's error is less than or equal to 0.1%]
     * </pre>
     *
     */
    private static double errorCalculation(double poserror, double usercon,
            double result) {

        poserror = (Math.abs(100 * ((usercon - result) / result)));
        // returns the percentage of error

        return poserror;
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Please input some (positive) constant: ");
        double u = in.nextDouble(); // user enters desired positive constant
        out.println();

        double min = u; // setting a basic minimum number

        out.println("Please enter four positive numbers that do not equal 1: ");

        out.print("Enter w: ");
        double w = in.nextDouble();
        out.println();
        out.print("Enter x: ");
        double x = in.nextDouble();
        out.println();
        out.print("Enter y: ");
        double y = in.nextDouble();
        out.println();
        out.print("Enter z: ");
        double z = in.nextDouble();
        out.println();
        // user enters other four desired constants

        double[] exponents = { -5, -4, -3, -2, -1, -0.5, -(1.0 / 3), -(0.25),
                0, .25, (1.0 / 3), 0.5, 1, 2, 3, 4, 5 };
        // exponent array

        double total = 0; // total amount when de Jager algorithm used
        double a = 0, b = 0, c = 0, d = 0; // possible exponents
        int first, second, third, fourth; // counter variables

        for (first = 0; first < exponents.length; first++) {
            for (second = 0; second < exponents.length; second++) {
                for (third = 0; third < exponents.length; third++) {
                    for (fourth = 0; fourth < exponents.length; fourth++) {

                        total = Math.pow(w, exponents[first])
                                * Math.pow(x, exponents[second])
                                * Math.pow(y, exponents[third])
                                * Math.pow(z, exponents[fourth]);
                        // de Jager algorithm

                        if (Math.abs(u - total) < min) {
                            a = exponents[first];
                            b = exponents[second];
                            c = exponents[third];
                            d = exponents[fourth];
                            // sets exponents in array to blank variables

                            min = Math.abs(u - total); // new minimum for error
                        }
                    }
                    fourth = 0;
                }
                third = 0;
            }
            second = 0;
        }

        double end = (Math.pow(w, a) * Math.pow(x, b) * Math.pow(y, c) * Math
                .pow(z, d)); // approximated number

        out.println("You entered the positive constant value : " + u);
        out.print("a is: ");
        out.println(a, 2, false);
        out.print("b is: ");
        out.println(b, 2, false);
        out.print("c is: ");
        out.println(c, 2, false);
        out.print("d is: ");
        out.println(d, 2, false);

        double error = 0;

        out.println();

        out.print("The approximated number using de Jager's formula is about: ");
        out.println(end, 2, false); // outputs rounded integer of approximated number
        out.println();
        out.print("The error of the approximation is about: ");
        out.print(errorCalculation(error, u, end), 2, false); // outputs truncated error of approximation
        out.print("%.");

        in.close();
        out.close();
    }
}
