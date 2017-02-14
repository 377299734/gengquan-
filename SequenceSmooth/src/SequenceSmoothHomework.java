import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmoothHomework {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmoothHomework() {
    }

    /**
     * Returns the integer average of two given {@code int}s.
     *
     * @param j
     *            the first of two integers to average
     * @param k
     *            the second of two integers to average
     * @return the integer average of j and k
     * @ensures average = (j+k)/2
     */
    public static int average(int j, int k) {

        if (j % 2 == 0 || k % 2 == 0) {

            j = j / 2;
            k = k / 2;

        } else {

            j = j / 2;
            k = k / 2;

            if (k < 0) {

                k = k - 1;

            } else {

                k = k + 1;

            }
        }

        int average = j + k;

        return average;

    }

    public static void main(String[] args) {

        SimpleWriter out = new SimpleWriter1L();

        int j = -3;
        int k = -5;

        out.println(average(j, k));

        out.close();
    }

}