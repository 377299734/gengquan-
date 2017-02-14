import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Implements method to smooth a {@code Sequence<Integer>}.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private SequenceSmooth() {
    }

    /**
     * Smooths a given {@code Sequence<Integer>}.
     *
     * @param s1
     *            the sequence to smooth
     * @param s2
     *            the resulting sequence
     * @updates s2
     * @requires |s1| >= 1
     * @ensures <pre>
     * |s2| = |s1| - 1  and
     *  for all i, j: integer, a, b: string of integer
     *      where (s1 = a * <i> * <j> * b)
     *    (there exists c, d: string of integer
     *       (|c| = |a|  and
     *        s2 = c * <(i+j)/2> * d))
     * </pre>
     */
    public static void smooth(Sequence<Integer> s1, Sequence<Integer> s2) {
        assert s1 != null : "Violation of: s1 is not null";
        assert s2 != null : "Violation of: s2 is not null";
        assert s1.length() >= 1 : "|s1| >= 1";

        s2.clear();

        for (int i = 0; i < s1.length() - 1; i++) {

            int before = s1.remove(i);
            int next = s1.remove(i);

            s2.add(i, (before + next) / 2);

            s1.add(i, before);
            s1.add(i + 1, next);

        }

//        if (s1.length() > 1) {
//
//            int before = s1.remove(0);
//            int next = s1.remove(0);
//
//            int ave = (next + before) / 2;
//
//            s1.add(0, next);
//
//            if (s1.length() > 0) {
//                smooth(s1, s2);
//            }
//
//            s1.add(0, before);
//            s2.add(0, ave);
//
//        }

    }

    public static void main(String[] args) {

        SimpleWriter out = new SimpleWriter1L();

        Sequence<Integer> s1 = new Sequence1L<Integer>();
        Sequence<Integer> s2 = new Sequence1L<Integer>();

        s1.add(0, 2);
        s1.add(1, 11);
        s1.add(2, 39);

        s2.add(0, 3);
        s2.add(1, 5);

        smooth(s1, s2);

        out.println(s1);
        out.println(s2);

        out.close();
    }

}