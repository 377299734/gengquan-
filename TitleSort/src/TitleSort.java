import java.util.Comparator;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class TitleSort {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TitleSort() {
    }

    private static class EntrySLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static SortingMachine myMethod(SimpleReader in) {
        Comparator<String> comp = new EntrySLT();
        SortingMachine<String> m = new SortingMachine1L<String>(comp);

        while (!in.atEOS()) {
            String line = in.nextLine();
            m.add(line);
        }

        m.changeToExtractionMode();

        return m;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();

        System.out.println("Insert file name: ");
        String file = in.nextLine();

        SimpleReader filein = new SimpleReader1L(file);

        System.out.println();
        System.out.print("Output file name: ");
        String filename = in.nextLine();

        SimpleWriter fileout = new SimpleWriter1L("data/" + filename + ".txt");

        /*
         * Put your main program code here; it may call myMethod as shown
         */
        SortingMachine<String> m = myMethod(filein);

        while (m.size() > 0) {
            fileout.println(m.removeFirst());
        }

        /*
         * Close input and output streams
         */
        in.close();
        filein.close();
        fileout.close();
    }

}
