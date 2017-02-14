import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * Takes a file from a user as well as a positive number (n). It then generates
 * a tag cloud from the top n most used words and displays them in alphabetical
 * order.
 *
 * @author Jake Alvord and Shannon O'Toole
 *
 */
public final class TagCloudGenerator {

    /**
     * Compare {@code Map.Pair<String, Integer>}s in alphabetical order
     * according to key value.
     */
    private static class PairSLT implements
            Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {

            String s1 = o1.key().toLowerCase();
            String s2 = o2.key().toLowerCase();

            return s1.compareTo(s2);
        }
    }

    /**
     * Compare {@code Map.Pair<String, Integer>}s by decreasing order by value.
     */
    private static class PairVLT implements
            Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {

            Integer i1 = o1.value();
            Integer i2 = o2.value();
            return i2.compareTo(i1);
        }
    }

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGenerator() {
    }

    /**
     * Bottom number of the ASCII table for capital numbers.
     */
    public static final int BOTTOM = 64;
    /**
     * Top number of the ASCII table for capital numbers.
     */
    public static final int LMIDDLE = 91;
    /**
     * Bottom number of the ASCII table for lowercase numbers.
     */
    public static final int TMIDDLE = 96;
    /**
     * Top number of the ASCII table for lowercase numbers.
     */
    public static final int TOP = 123;

    /**
     * This method creates a map the words of the user's given file and,
     * correspondingly, the number of times each word is used. The file will not
     * be changed.
     *
     * @param in
     *            is an SimpleReader file given by the user in main
     * @requires in is a valid, open file
     * @ensures all words from in will appear in the returned map exactly once
     * @return Map<String, Integer> of words of the file and their counts
     */
    private static Map<String, Integer> reader(SimpleReader in) {
        assert in != null : "Violation of: in is not null";

        Map<String, Integer> terms = new Map1L<String, Integer>();

        String line;
        while (!in.atEOS()) {
            line = in.nextLine();

            int i = 0;
            String word = "";
            while (i < line.length()) {
                char character = line.charAt(i);
                int num = character;
                if ((num > BOTTOM && num < LMIDDLE)
                        || (num > TMIDDLE && num <= TOP)) {
                    word += character;
                    word = word.toLowerCase();
                } else {
                    if (terms.hasKey(word)) {
                        int count = terms.value(word);
                        terms.replaceValue(word, count + 1);

                    } else {
                        terms.add(word, 1);
                    }
                    word = "";
                }
                int l = line.length();
                if (i == l - 1
                        && ((num > BOTTOM && num < LMIDDLE) || (num > TMIDDLE && num <= TOP))) {
                    if (terms.hasKey(word)) {
                        int count = terms.value(word);
                        terms.replaceValue(word, count + 1);

                    } else {
                        terms.add(word, 1);
                    }
                    word = "";
                }
                i++;
            }
        }

        if (terms.hasKey("")) {
            terms.remove("");
        }

        return terms;
    }

    /**
     * Method for sorting by value.
     *
     * @param terms
     *            map of all of the words and their counts
     * @requires terms is not null
     * @ensures all Map.Pairs in terms will be sorted in decreasing order based
     *          on their value
     * @return SortingMachine<Map.Pair<String, Integer>> of words of the file
     *         and their counts
     */
    public static SortingMachine<Map.Pair<String, Integer>> valueSort(
            Map<String, Integer> terms) {
        assert terms != null : "Violation of: terms is not null";

        Comparator comp = new PairVLT();

        SortingMachine<Map.Pair<String, Integer>> s = new SortingMachine1L(comp);

        for (Map.Pair<String, Integer> p : terms) {
            s.add(p);
        }

        terms.clear();

        s.changeToExtractionMode();

        return s;

    }

    /**
     * Reduces the size of the SOrting Machine down to the number entered by the
     * user in order to increase efficiency.
     *
     * @param s
     *            SortingMachine<Map.Pair<String, Integer>> of all of the words
     *            and their counts
     * @param number
     *            size the user put in
     * @requires s is not null && number > 0 && number < s.size()
     * @ensures all of the top n value in s will be extracted
     * @return SortingMachine<Map.Pair<String, Integer>> of resized top value
     *         Map.Pairs
     */
    public static SortingMachine<Map.Pair<String, Integer>> sample(
            SortingMachine<Map.Pair<String, Integer>> s, int number) {
        assert s != null : "Violation of: s is not null";
        assert number <= s.size() : "Violation of: s <= size of s";

        SortingMachine<Map.Pair<String, Integer>> counts = s.newInstance();

        int k = 0;

        for (Map.Pair<String, Integer> p : s) {
            if (k < number) {
                counts.add(p);
            }
            k++;
        }

        return counts;
    }

    /**
     * Method for sorting by value.
     *
     * @param counts
     *            SortingMachine<Map.Pair<String, Integer>> of all of the words
     *            and their counts
     * @requires counts is not null && number is not null
     * @ensures all Map.Pairs in terms will be sorted in decreasing order based
     *          on their value
     * @return SortingMachine<Map.Pair<String, Integer>> of words of the file
     *         and their counts
     */
    public static SortingMachine<Map.Pair<String, Integer>> alphabetSort(
            SortingMachine<Map.Pair<String, Integer>> counts) {
        assert counts != null : "Violation of: counts is not null";

        Comparator comp = new PairSLT();

        SortingMachine<Map.Pair<String, Integer>> s = new SortingMachine1L<>(
                comp);

        for (Map.Pair<String, Integer> p : counts) {
            s.add(p);
        }

        s.changeToExtractionMode();

        return s;

    }

    /**
     * This method creates a map the words of the user's given file and,
     * correspondingly, the number of times each word is used. The file will not
     * be changed.
     *
     * @param out
     *            is a SimpleWriter to write out to the file
     * @param counts
     *            a SortingMachine of all the words and their counts
     * @param title
     *            title of the page
     * @param number
     *            number input by the user
     * @requires counts is not null && number > 0 && out is not null
     * @ensures an HTML page of the tag cloud will be generated
     */
    public static void output(SortingMachine<Map.Pair<String, Integer>> counts,
            SimpleWriter out, String title, int number) {
        assert counts != null : "Violation of: counts is not null";
        assert out != null : "Violation of: out is not null";
        assert number > 0 : "Violation of: number > 0";

        out.println("<html>");
        out.println("<head>");
        out.print("<title>");
        out.print("Top " + number + " Words in " + title);
        out.println("</title>");
        out.println("<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println("</head>");
        out.println("<body>");
        out.print("<h2>");
        out.print("Top " + number + " Words in " + title + "");
        out.println("</h2>");
        out.println("<hr>");
        out.println("<div class=\"cdiv\">");
        out.println("<p class=\"cbox\">");

        int average = 0;
        for (Map.Pair<String, Integer> p : counts) {
            average += p.value();
        }

        average /= number;

        for (Map.Pair<String, Integer> m : counts) {
            out.print("<span style=\"cursor:default\" class=\"f");

            double percentage = (double) (m.value() * 5) / average;

            percentage += 11;

            if (percentage > 48) {
                percentage = 48;
            } else if (percentage < 11) {
                percentage = 11;
            }

            out.print((int) percentage);

            out.print("\" title=\"count: ");
            out.println(m.value() + "\">" + m.key() + "</span>");
        }

        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
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

        out.println("CLOUD TAG GENERATOR");
        out.print("Input file: ");
        String file = in.nextLine();
        String name = file;

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '/') {
                for (int j = i; j < name.length(); j++) {
                    if (name.charAt(j) == '.') {
                        name = name.substring(i + 1, j);
                    }
                }
            }
        }

        SimpleReader filein = new SimpleReader1L(file);

        out.println();

        out.print("How many words should be displayed? ");
        int number = in.nextInteger();

        while (number < 0) {
            out.println();
            out.println("The size of the number needs to be a positive integer.");
            out.print("Please try again: ");
            number = in.nextInteger();
        }

        Map<String, Integer> terms = reader(filein);

        SortingMachine list = valueSort(terms);

        SimpleWriter fileout = new SimpleWriter1L("data/" + number
                + "_words_in_" + name + ".html");

        if (list.size() == 0) {
            output(list, fileout, file, number);
        } else {

            list = sample(list, number);
            list = alphabetSort(list);

            output(list, fileout, file, number);
        }

        in.close();
        out.close();
    }
}
