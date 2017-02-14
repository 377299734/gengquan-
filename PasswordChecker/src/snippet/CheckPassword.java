package snippet;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * User inputs password and the program checks whether it meet requirements of
 * upper case letter, lower case letter, and a number.
 *
 * @JakeAlvord
 *
 */
public final class CheckPassword {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword() {
    }

    /**
     * Checks whether the given String satisfies the CSE department criteria for
     * a valid password. Prints an appropriate message to the given output
     * stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {

        int count = 0;

        if (!correctLength(s)) {
            out.println();
            out.print("Password does not meet requirements.");
        } else {
            if (!containsUpperCaseLetter(s)) {
                count++;
            }
            if (!containsLowerCaseLetter(s)) {
                count++;
            }
            if (!containsNumber(s)) {
                count++;
            }
            if (!containsSpecialLetter(s)) {
                count++;
            }
            if (count > 1) {
                out.print("Password does not meet requirements.");
            } else {
                out.println();
                out.print("Thank you for your password, all requirements were met.");
            }
        }
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String s) {

        int count = 0;
        boolean upper = false;

        while (count < s.length()) {

            if (Character.isUpperCase(s.charAt(count))) {
                upper = true;
            }
            count++;
        }
        return upper;
    }

    /**
     * Checks if the given String contains a lower case letter.
     *
     * @param s
     *            the String to check
     * @return true if s contains an lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String s) {

        int count = 0;
        boolean lower = false;

        while (count < s.length()) {

            if (Character.isLowerCase(s.charAt(count))) {
                lower = true;
            }
            count++;
        }
        return lower;

    }

    /**
     * Checks if the given String contains a number
     *
     * @param s
     *            the String to check
     * @return true if s contains a number, false otherwise
     */
    private static boolean containsNumber(String s) {

        int count = 0;
        boolean number = false;

        while (count < s.length()) {

            if (Character.isDigit(s.charAt(count))) {
                number = true;
            }
            count++;
        }
        return number;

    }

    /**
     * Checks if the given String is the correct length
     *
     * @param s
     *            the String to check
     * @return true if s is 6 or more chars, false otherwise
     */
    private static boolean correctLength(String s) {

        int count = 6;
        boolean length = false;

        if (s.length() >= count) {
            length = true;
        }
        return length;
    }

    /**
     * Checks if the given String contains a special character.
     *
     * @param s
     *            the String to check
     * @return true if s contains a special character, false otherwise
     */
    private static boolean containsSpecialLetter(String s) {

        int count = 0;
        boolean special = true;
        String options = "!@#$%^&*()_-+={}[]:;,.?";

        while (count < s.length()) {

            if (s.indexOf(options.charAt(count)) == -1) {
                special = false;
            }
            count++;
        }
        return special;

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

        out.print("Please enter a password with an upper case letter, lower case letter, special character, and number (3 of 4): ");
        String password = in.nextLine();
        out.println();

        checkPassword(password, out);

        while (!password.isEmpty()) {

            out.print("Please enter a password with an upper case letter, lower case letter, special character, and number (3 of 4)");
            password = in.nextLine();
            out.println();

            checkPassword(password, out);
            out.println();
        }

        in.close();
        out.close();
    }

}
