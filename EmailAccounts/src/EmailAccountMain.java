import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple program to exercise EmailAccount functionality.
 */
public final class EmailAccountMain {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private EmailAccountMain() {
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
        Map<String, Integer> number = new Map1L<>();
        EmailAccount myAccount = new EmailAccount1("Brutus", "Buckeye", number);
        EmailAccount myAccount1 = new EmailAccount1("Brutus", "Buckeye", number);
        EmailAccount myAccount2 = new EmailAccount1("Brutus", "Buckeye", number);
        EmailAccount myAccount3 = new EmailAccount1("Brutus", "Buckeye", number);
        EmailAccount myAccount4 = new EmailAccount1("Brutus", "Buckeye", number);
        EmailAccount myAccount5 = new EmailAccount1("Brutus", "Buckeye", number);
        /*
         * Should print: Brutus Buckeye
         */
        out.println(myAccount.name());
        /*
         * Should print: buckeye.1@osu.edu
         */
        out.println(myAccount.emailAddress());
        /*
         * Should print: Name: Brutus Buckeye, Email: buckeye.1@osu.edu
         */
        out.println(myAccount);

        /*
         * Should print: Brutus Buckeye
         */
        out.println(myAccount1.name());
        /*
         * Should print: buckeye.1@osu.edu
         */
        out.println(myAccount1.emailAddress());
        /*
         * Should print: Name: Brutus Buckeye, Email: buckeye.1@osu.edu
         */
        out.println(myAccount1);

        /*
         * Should print: Brutus Buckeye
         */
        out.println(myAccount2.name());
        /*
         * Should print: buckeye.1@osu.edu
         */
        out.println(myAccount2.emailAddress());
        /*
         * Should print: Name: Brutus Buckeye, Email: buckeye.1@osu.edu
         */
        out.println(myAccount2);

        /*
         * Should print: Brutus Buckeye
         */
        out.println(myAccount3.name());
        /*
         * Should print: buckeye.1@osu.edu
         */
        out.println(myAccount3.emailAddress());
        /*
         * Should print: Name: Brutus Buckeye, Email: buckeye.1@osu.edu
         */
        out.println(myAccount3);

        /*
         * Should print: Brutus Buckeye
         */
        out.println(myAccount4.name());
        /*
         * Should print: buckeye.1@osu.edu
         */
        out.println(myAccount4.emailAddress());
        /*
         * Should print: Name: Brutus Buckeye, Email: buckeye.1@osu.edu
         */
        out.println(myAccount4);

        /*
         * Should print: Brutus Buckeye
         */
        out.println(myAccount5.name());
        /*
         * Should print: buckeye.1@osu.edu
         */
        out.println(myAccount5.emailAddress());
        /*
         * Should print: Name: Brutus Buckeye, Email: buckeye.1@osu.edu
         */
        out.println(myAccount5);

        in.close();
        out.close();
    }

}
