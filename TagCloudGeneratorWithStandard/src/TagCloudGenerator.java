import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    private static class EntrySLT implements Serializable,
    Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {

            String s1 = o1.getKey().toLowerCase();
            String s2 = o2.getKey().toLowerCase();

            return s1.compareTo(s2);
        }
    }

    /**
     * Compare {@code Map.Pair<String, Integer>}s by decreasing order by value.
     */
    private static class EntryVLT implements Serializable,
    Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {

            Integer i1 = o1.getValue();
            Integer i2 = o2.getValue();
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
    public static final int BOT = 64;
    /**
     * Top number of the ASCII table for capital numbers.
     */
    public static final int LMI = 91;
    /**
     * Bottom number of the ASCII table for lowercase numbers.
     */
    public static final int TMI = 96;
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
    private static Map<String, Integer> reader(BufferedReader in) {
        assert in != null : "Violation of: in is not null";

        Map<String, Integer> terms = new HashMap<String, Integer>();

        try {
            while (in.ready()) {
                String line = in.readLine();
                String word = "";

                for (int i = 0; i < line.length(); i++) {
                    char character = line.charAt(i);
                    int num = character;
                    if ((num > BOT && num < LMI) || (num > TMI && num <= TOP)) {
                        /**
                         * checks if the character is not a whitespace
                         * character, if it is not then it is added to a String
                         * that is fulfilling a word
                         */
                        word += character;
                        word = word.toLowerCase();
                    } else {
                        if (terms.containsKey(word)) {
                            /**
                             * if the word is already in the Map, then the value
                             * is merely incremented
                             */
                            int count = terms.get(word);
                            terms.put(word, count + 1);

                        } else {
                            /**
                             * if the word is not in the Map, then it gets added
                             * in with an initial of one
                             */
                            terms.put(word, 1);
                        }
                        /**
                         * word gets replaced as an empty String
                         */
                        word = "";
                    }

                    int l = line.length() - 1;

                    if (i == l
                            && ((num > BOT && num < LMI) || (num > TMI && num <= TOP))) {
                        if (terms.containsKey(word)) {
                            int count = terms.get(word);
                            terms.put(word, count + 1);

                        } else {
                            terms.put(word, 1);
                        }
                        word = "";
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file");
        }

        if (terms.containsKey("")) {
            terms.remove("");
        }

        return terms;
    }

    /**
     * Sorts the Map.Entrys by value and returns a sorted set.
     *
     * @param terms
     *            map of all of the words and their counts
     * @requires terms is not null
     * @ensures all Map.Entries in terms will be sorted in decreasing order
     *          based on their value
     * @return List<Map.Entry<String, Integer>> of words of the file and their
     *         counts
     */
    public static List<Map.Entry<String, Integer>> valueSort(
            Map<String, Integer> terms) {
        assert terms != null : "Violation of: terms is not null";

        /**
         * declare the comparator
         */
        Comparator<Map.Entry<String, Integer>> comp = new EntryVLT();

        /**
         * instantiate a list of Map.Entries
         */
        List<Map.Entry<String, Integer>> l = new LinkedList<Map.Entry<String, Integer>>();

        /**
         * add all of the elements from terms into the linked list
         */
        for (Map.Entry<String, Integer> m : terms.entrySet()) {
            l.add(m);
        }

        /**
         * sort the list numerically in descending order by their values
         */
        Collections.sort(l, comp);

        return l;

    }

    /**
     * Reduces the size of the Sorting Machine down to the number entered by the
     * user in order to increase efficiency, and then sorts alphabetically.
     *
     * @param l
     *            List<Map.Entry<String, Integer>> of all of the words and their
     *            counts
     * @param number
     *            size the user put in
     * @requires l is not null && number > 0 && number < l.size()
     * @ensures all of the top n value in s will be extracted
     * @return List<Map.Entry<String, Integer>> of resized top value Map.Entry
     */
    public static List<Map.Entry<String, Integer>> sample(
            List<Map.Entry<String, Integer>> l, int number) {
        assert l != null : "Violation of: s is not null";
        assert number <= l.size() : "Violation of: s <= size of s";

        /**
         * declare the comparator
         */
        Comparator<Map.Entry<String, Integer>> comp = new EntrySLT();

        /**
         * declare a list of Map.Entries
         */
        List<Map.Entry<String, Integer>> s = new LinkedList<Map.Entry<String, Integer>>();

        int k = 0;

        for (Map.Entry<String, Integer> p : l) {
            /**
             * add elements to the sorted list, but only up to the users desired
             * number
             */
            if (k < number) {
                s.add(p);
            }
            k++;
        }

        /**
         * sort the list alphabetically by their keys
         */
        Collections.sort(s, comp);

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
    public static void output(List<Map.Entry<String, Integer>> counts,
            PrintWriter out, String title, int number) {
        assert counts != null : "Violation of: counts is not null";
        assert out != null : "Violation of: out is not null";

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
        for (Map.Entry<String, Integer> p : counts) {
            average += p.getValue();
        }

        average /= number;

        for (Map.Entry<String, Integer> m : counts) {
            out.print("<span style=\"cursor:default\" class=\"f");

            double percentage = (double) (m.getValue() * 5) / average;

            percentage += 11;

            if (percentage > 48) {
                percentage = 48;
            } else if (percentage < 11) {
                percentage = 11;
            }

            out.print((int) percentage);

            out.print("\" title=\"count: ");
            out.println(m.getValue() + "\">" + m.getKey() + "</span>");
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
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("CLOUD TAG GENERATOR");
        System.out.print("Input file: ");
        String file = "";
        try {
            file = in.readLine();
        } catch (IOException e1) {
            System.err.print("Error reading line" + e1);
        }

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

        BufferedReader filein;
        try {
            filein = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            System.err.print("Error opening file" + e);
            return;
        }

        System.out.println();

        System.out.print("How many words should be displayed? ");
        int number = 0;
        try {
            number = Integer.parseInt(in.readLine());
        } catch (NumberFormatException e) {
            System.err.println("Error: incorrect number format");
        } catch (IOException e) {
            System.err.println("Error reading line");
        }

        if (number < 0) {
            number = 0;
        }

        Map<String, Integer> terms = reader(filein);

        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(name
                    + ".html")));
        } catch (IOException e) {
            System.err.println("Error opening output file" + e);
            return;
        }

        List<Map.Entry<String, Integer>> map = valueSort(terms);
        map = sample(map, number);
        output(map, out, file, number);

        try {
            in.close();
        } catch (IOException e) {
            System.err.println("Error closing file" + e);
        }
        out.close();
    }
}
