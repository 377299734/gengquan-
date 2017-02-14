import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Program to copy a text file into another file.
 *
 * @author Put your name here
 *
 */
public final class CopyFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CopyFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {

        int i = 0;
        while (args.length > i) {
            BufferedReader in;
            try {
                in = new BufferedReader(new FileReader(args[i]));
            } catch (IOException e) {
                System.err.println("Error opening file");
                return;
            }

            i++;

            PrintWriter out;
            try {
                out = new PrintWriter(new BufferedWriter(
                        new FileWriter(args[i])));
            } catch (IOException e) {
                System.err.println("Error opening file");
                return;
            }

            try {
                while (in.ready()) {
                    String line = in.readLine();
                    out.println(line);
                }
            } catch (IOException e) {
                System.err.println("Error reading file");
            }

            try {
                in.close();
            } catch (IOException e) {
                System.err.println("Error closing file");
            }
            out.close();

            i++;
        }

    }

}
