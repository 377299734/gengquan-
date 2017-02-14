import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
 *
 *
 * @Jake
 */
public final class Initiative {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Initiative() {

    }

    public final static class Com {

        public final static class CharacterValueSort implements Serializable,
        Comparator<Map.Entry<Character, Integer>> {
            @Override
            public int compare(Map.Entry<Character, Integer> e1,
                    Map.Entry<Character, Integer> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }

        }

        private final static class CharacterKeySort implements Serializable,
        Comparator<Map.Entry<Character, Integer>> {
            @Override
            public int compare(Map.Entry<Character, Integer> o1,
                    Map.Entry<Character, Integer> o2) {

                char c1 = o1.getKey();
                char c2 = o2.getKey();

                return Character.compare(c1, c2);
            }
        }

        public final static class StringValueSort implements Serializable,
        Comparator<Map.Entry<String, Integer>> {
            @Override
            public int compare(Map.Entry<String, Integer> e1,
                    Map.Entry<String, Integer> e2) {
                return e2.getValue().compareTo(e1.getValue());
            }

        }

        private final static class StringKeySort implements Serializable,
        Comparator<Map.Entry<String, Integer>> {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {

                String s1 = o1.getKey().toLowerCase();
                String s2 = o2.getKey().toLowerCase();

                return s1.compareTo(s2);
            }
        }

    }

    public final static class Helper {
        public static char charReader(String line, int position) {
            char c = line.charAt(position);
            if (!Character.isAlphabetic(c)) {
                c = ' ';
            }
            return c;
        }

        public static String wordReader(String line, int position) {
            String word = "";
            int i = position + 1;
            boolean test = alpha(line.charAt(position));

            while (i < line.length() && alpha(line.charAt(i)) == test) {
                word = line.substring(position, i);
                i++;
            }

            return word + line.substring(i - 1, i);
        }

        public static boolean alpha(char c) {
            boolean a = false;

            if (c > 64 && c < 91) {
                a = true;
            } else if (c > 96 && c < 123) {
                a = true;
            }

            return a;
        }
    }

    public static void counter(BufferedReader in,
            Map<Character, Integer> characters, Map<String, Integer> words) {

        try {
            while (in.ready()) {
                String line = "";

                try {
                    line = in.readLine();
                } catch (IOException e) {
                    System.err.println("Weird... cannot read the line");
                }

                if (line != null && line.length() > 0) {
                    for (int i = 0; i < line.length(); i++) {
                        char c = Helper.charReader(line, i);
                        c = Character.toLowerCase(c);
                        if (Helper.alpha(c)) {
                            if (characters.containsKey(c)) {
                                int value = characters.get(c);
                                characters.put(c, value + 1);
                            } else {
                                characters.put(c, 1);
                            }
                        }
                    }

                    int position = 0;
                    String word = "";
                    while (position < line.length()) {
                        word = Helper.wordReader(line, position);
                        position += word.length();

                        word = word.toLowerCase();

                        if (Helper.alpha(word.charAt(0))) {
                            if (words.containsKey(word)) {
                                int value = words.get(word);
                                words.put(word, value + 1);
                            } else {
                                words.put(word, 1);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("The line is not ready for you yet.");
        }
    }

    public final static class Sort {

        public static List<Map.Entry<Character, Integer>> charValSort(
                Map<Character, Integer> characters) {
            Comparator comp = new Com.CharacterValueSort();
            List<Map.Entry<Character, Integer>> list = new LinkedList<>();

            for (Map.Entry<Character, Integer> m : characters.entrySet()) {
                list.add(m);
            }

            Collections.sort(list, comp);

            return list;
        }

        public static List<Map.Entry<String, Integer>> stringValSort(
                Map<String, Integer> words) {
            Comparator comp = new Com.StringValueSort();
            List<Map.Entry<String, Integer>> list = new LinkedList<>();

            for (Map.Entry<String, Integer> m : words.entrySet()) {
                list.add(m);
            }

            Collections.sort(list, comp);

            return list;
        }

        public static List<Map.Entry<Character, Integer>> charKeySort(
                Map<Character, Integer> characters) {
            Comparator comp = new Com.CharacterKeySort();
            List<Map.Entry<Character, Integer>> list = new LinkedList<>();

            for (Map.Entry<Character, Integer> m : characters.entrySet()) {
                list.add(m);
            }

            Collections.sort(list, comp);

            return list;
        }

        public static List<Map.Entry<String, Integer>> stringKeySort(
                Map<String, Integer> words) {
            Comparator comp = new Com.StringKeySort();
            List<Map.Entry<String, Integer>> list = new LinkedList<>();

            for (Map.Entry<String, Integer> m : words.entrySet()) {
                list.add(m);
            }

            Collections.sort(list, comp);

            return list;
        }

    }

    public static void htmlOutput(PrintWriter out, int number,
            Map<Character, Integer> characters, Map<String, Integer> words,
            String title) {

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
//        out.println("<div class=\"cdiv\">");
//        out.println("<p class=\"cbox\">");
//
//        out.println("</div>");

        List<Map.Entry<Character, Integer>> charKeySorted = Sort
                .charKeySort(characters);

        for (int i = 0; i < 26; i++) {
            out.print("<p>");

            String next = Integer.toString('a' + i);

            out.print(next);
            out.print(": ");

            int value = 0;

            for (Map.Entry<Character, Integer> m : charKeySorted) {
                if (m.getKey() == next.charAt(0)) {
                    value = m.getValue();
                }
            }

            if (value > 0) {
                for (int j = 0; j < value; j++) {
                    out.print("|");
                }
            }

            out.println("</p>");

        }

        out.println("<p> List of characters by descending value: </p>");

        List<Map.Entry<Character, Integer>> charValSorted = Sort
                .charValSort(characters);

        out.println("<p>         ");

        for (Map.Entry<Character, Integer> m : charValSorted) {
            out.print(m.getValue());
        }
        out.println("</p>");

        out.println("</body>");
        out.println("</html>");

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

        System.out
        .println("Welcome to the custom character counter generator.");
        System.out.print("Please enter a file name: ");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String filename = "generic";
        try {
            filename = in.readLine();
        } catch (IOException e) {
            System.err
            .println("We had an error reading your file, try again next time.");
            return;
        }

        String title = "file";

        if (filename != null) {
            int spot = filename.length();
            for (int i = 0; i < filename.length(); i++) {
                if (filename.charAt(i) == '.') {
                    spot = i;
                }
                title = filename.substring(0, spot);
            }
        }

        BufferedReader filein = null;
        try {
            filein = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.err.println("Fuck your file is not there. Bummer.");
        }

        if (filein == null) {
            System.err.println("You cannot pass a null file dumbo.");
            return;
        }

        Map<Character, Integer> characters = new HashMap<>();
        Map<String, Integer> words = new HashMap<>();

        counter(filein, characters, words);

        System.out.println();
        System.out
        .println("Alright a few things we need to know before we print.");

        System.out.print("How many words would you like in the tag cloud? ");
        int number = 0;
        try {
            number = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            System.err.println("Crap cannot read that.");
        } catch (NumberFormatException e) {
            System.err.println("Really? Messed up putting in a single number?");
        }

        System.out.println();
        System.out.print("Where would you like to print to? ");

        String fileout = "";
        try {
            fileout = in.readLine();
        } catch (IOException e) {
            System.err.println("Horrible place to print to mate.");
        }

        if (fileout != null) {
            PrintWriter out = null;
            try {
                out = new PrintWriter(new FileWriter(fileout));
            } catch (IOException e) {
                System.err.println("Something has gone fucky.");
            }

            if (out != null) {
                htmlOutput(out, number, characters, words, title);
            }
        }

        System.out.println();
        System.out.print("There ya go ya filthy animal, it's printing now.");
    }
}
