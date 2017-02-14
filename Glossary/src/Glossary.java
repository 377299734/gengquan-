import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program accepts a file and generates a glossary in HTML terms.
 *
 * @JakeAlvord
 *
 */

public final class Glossary {

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Reads the file and generates two Queues, one with both words and
     * definitions, while the other contains the words only.
     *
     * @param file
     *            the input file from user
     * @param glossary
     *            the generated list of terms
     * @param terms
     *            the individual words given by the file
     * @updates glossary, keys
     * @requires <pre>
     * file != empty
     * </pre>
     * @ensures <pre>
     * glossary contains list of words and their definitions
     * terms contains list of words
     * </pre>
     */
    public static void reader(SimpleReader file,
            Queue<Map<String, String>> glossary, Queue<String> terms) {

        assert glossary != null : "Violation of: glossary is not null";
        assert terms != null : "Violation of: terms is not null";
        assert file != null : "Violation of: file is not null";

        while (!file.atEOS()) {
            Map<String, String> line = new Map1L<>();

            String word = "";

            if (!file.atEOS()) {
                word = file.nextLine();
            }

            // takes each imported word and puts into into the Queue wordList

            String definition = "";
            if (!file.atEOS()) {
                definition = file.nextLine();
            }

            if (!word.isEmpty() && !definition.isEmpty()) {
                terms.enqueue(word);
            }

            String temp = "";

            if (!file.atEOS()) {
                temp = file.nextLine();
            }

            while (!temp.isEmpty()) {
                definition = definition + " " + temp;
                if (!file.atEOS()) {
                    temp = file.nextLine();
                } else {
                    temp = "";
                }
            }
            // takes each definition no matter the length and puts it into a string

            if (!word.isEmpty() && !definition.isEmpty()) {
                line.add(word, definition); // adds each word and definition to a map
                glossary.enqueue(line); // adds each map to the Queue glossary
            }
        }
    }

    /**
     * Sorts the words in the glossary into lexicographical order.
     *
     * @param glossary
     *            the generated list of terms
     * @param terms
     *            the individual words given by the file
     * @updates glossary
     * @requires <pre>
     * terms = first String in each Map in glossary
     * </pre>
     * @ensures <pre>
     * glossary contains list of words and their definitions in lexicographical
     *       order
     * terms contains list of words in lexicographical order
     * </pre>
     */
    public static void sorter(Queue<Map<String, String>> glossary,
            Queue<String> terms) {
        assert glossary != null : "Violation of: glossary is not null";
        assert terms != null : "Violation of: keys is not null";

        Queue<Map<String, String>> temp = new Queue1L<>();

        while (glossary.length() != 0) {
            temp.enqueue(glossary.dequeue());
        }
        // puts the Queue glossary content and puts into Queue temp

        while (temp.length() != 0) {

            String term = terms.dequeue();
            // keys come in lexicographical order

            int k = 0;
            while (k < temp.length()) {
                Map<String, String> possible = temp.dequeue();
                if (possible.hasKey(term)) {
                    glossary.enqueue(possible);
                } else {
                    temp.enqueue(possible);
                }
                // finds each Map with the correct key and
                // adds them back into Queue glossary in
                // lexicographical order

                k++;
            }

            terms.enqueue(term);
            // enters the word back into the Queue
        }
    }

    /**
     * Generates the main index page, listing each word in lexicographical order
     * with links to their individual definition pages
     *
     * @param terms
     *            the individual words given by the file
     * @param path
     *            the file the HTML files will be saved to
     * @requires <pre>
     * terms = first String in each Map in glossary
     * </pre>
     * @ensures <pre>
     * generates a main page index with a lexicographical list with
     * links to individual pages with unique words and their definitions
     * </pre>
     */
    public static void generateIndex(Queue<String> terms, String path) {
        assert terms != null : "Violation of: terms is not null";

        SimpleWriter file = new SimpleWriter1L(path + "/index.html");
        // writes to the file

        file.println("<html>");
        file.println("<head>");
        file.println("<title>Glossary</title>");
        file.println("</head>");
        file.println("<body>");
        file.println("<h2>Glossary</h2>");
        file.println("<hr>");
        file.println("<h3>Index</h3>");
        file.println("<ul>");
        // prints HTML code

        for (int j = 0; j < terms.length(); j++) {
            file.println("<li>");

            String name = terms.front();

            SimpleWriter fileOut = new SimpleWriter1L(path + "/" + name
                    + ".html");
            // generates different HTML pages for each word

            file.println("<a href=\"" + name + ".html\">" + name + "</a>");

            file.println("</li>");

            terms.rotate(1);

            fileOut.close();
        }

        file.println("</ul>");
        file.println("</body>");
        file.println("</html>");
        // finishes off the HTML index code

        file.close();
    }

    /**
     * Generates individual HTML pages containing a word and its definition with
     * a link back to the index.
     *
     * @param glossary
     *            the generated list of terms
     * @param terms
     *            the individual words given by the file
     * @param path
     *            the file the HTML files will be saved to
     * @requires <pre>
     * terms != empty
     * </pre>
     * @ensures <pre>
     * individual pages with each word and definition will be generated
     * with a link back to the index page
     * </pre>
     */
    public static void generatePages(Queue<Map<String, String>> glossary,
            Queue<String> terms, String path) {
        assert glossary != null : "Violation of: glossary is not null";
        assert terms != null : "Violation of: terms is not null";

        for (int k = 0; k < terms.length(); k++) {
            String name = terms.front();
            SimpleWriter file = new SimpleWriter1L(path + "/" + name + ".html");

            file.println("<html>");
            file.println("<head>");
            file.println("<title>" + name + "</title>");
            file.println("</head>");
            file.println("<body>");
            file.println("<h2>");
            file.println("<b>");
            file.println("<i>");
            file.println("<font color=\"red\">" + name + "</font>");
            file.println("</i>");
            file.println("</b>");
            file.println("</h2>");
            file.println("<blockquote>");

            definitionPrint(name, file, glossary, terms);
            // calls function definitionPrint to print the definition of a word

            file.println("</blockquote>");
            file.println("<hr>");
            file.println("<p>");
            file.println("Return to ");
            file.println("<a href=\"index.html\">index</a>");
            file.println(".");
            file.println("</p>");
            file.println("</body>");
            file.println("</html>");
            // prints HTML code for each individual page

            terms.rotate(1);
            glossary.rotate(1);

            file.close();
        }
    }

    /**
     * Generates individual definitions for each each unique word.
     *
     * @param name
     *            name of the word tested against each definition
     * @param file
     *            file to print to
     * @param glossary
     *            the generated list of terms
     * @param terms
     *            the individual words given by the file
     * @requires <pre>
     * terms != empty, glossary != empty, name != empty, file is valid
     * </pre>
     * @ensures <pre>
     * each definition is printed with links to other unique words used in the
     * definition
     * </pre>
     */
    public static void definitionPrint(String name, SimpleWriter file,
            Queue<Map<String, String>> glossary, Queue<String> terms) {
        assert glossary != null : "Violation of: glossary is not null";
        assert terms != null : "Violation of: terms is not null";
        assert name != null : "Violation of: name is not null";
        assert file != null : "Violation of: file is not null";

        String definition = glossary.front().value(name);
        // definition of the unique word

        String total = "";

        for (int n = 0; n < terms.length(); n++) {
            String word = terms.front();
            total = isTerm(word, definition);
            // calls the function isTerm to alter the definition

            if (!total.isEmpty()) {
                definition = total;
            }

            terms.rotate(1);
        }

        file.println(definition);
        // prints the definition
    }

    /**
     * Alters a particular definition in order to provide links to unique words
     * found in particular definition.
     *
     * @param term
     *            name of the word tested against each definition
     * @param definition
     *            definition of a particular unique word
     * @requires <pre>
     * term != empty, definition != valid
     * </pre>
     * @ensures <pre>
     * an altered definition with appropriate code is added in order to provide
     * a link to a unique word if found in the definition
     * </pre>
     */
    public static String isTerm(String term, String definition) {
        assert term != null : "Violation of: term is not null";
        assert definition != null : "Violation of: definition is not null";

        int length = term.length();

        String total = "";

        for (int k = 0; k <= definition.length() - length; k++) {
            String test = definition.substring(k, length + k);
            if (test.equals(term)) {
                String first = definition.substring(0, k - 1) + " <a href=\""
                        + term + ".html\">" + term + "</a>";
                String last = definition.substring(k + term.length(),
                        definition.length());
                // tests word against phrase and alters it if it found a
                // unique word in the definition in order to provide a link to
                // it
                total = first + last;
            }

        }

        return total;
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

        out.print("Please enter a path for the files to be stored (i.e. data, doc, lib, etc.): ");
        String path = in.nextLine();

        out.println();

        out.print("Hello, please enter a file to add to the glossary: ");
        String fileName = in.nextLine();
        // user inputs file to be analyzed

        SimpleReader file = new SimpleReader1L(fileName);

        Queue<Map<String, String>> glossary = new Queue1L<>();
        // main storage of terms and definitions
        Queue<String> terms = new Queue1L<>();
        // extra list of terms

        reader(file, glossary, terms);
        // calls reader function to update Queues glossary and keys

        Comparator<String> comp = new StringLT();
        // calls comparator

        terms.sort(comp);
        // uses sort to order the terms in Queue keys lexicographically

        sorter(glossary, terms);
        // call sorter function to sort Queue glossary lexicographically

        generateIndex(terms, path);
        // calls function generateIndex in order to generate main index page
        generatePages(glossary, terms, path);
        // calls function generatePages in order to generate individual
        // term pages and definitions

        file.close();
        in.close();
        out.close();
    }

}
