import components.map.Map;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Jake Alvord
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * First name of client.
     */
    private static String first;

    /**
     * Last name of client.
     */
    private static String last;
    /**
     * List of names and their numbers;
     */
    private static Map<String, Integer> numbers;
    /**
     * Saved dot number
     */
    private static int num;

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName,
            Map<String, Integer> map) {

        this.first = firstName;
        this.last = lastName;
        this.numbers = map;

    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {

        String title = this.first + " " + this.last;

        return title;
    }

    @Override
    public String emailAddress() {

        int number = 0;

        if (this.numbers.hasKey(this.last.toLowerCase())) {
            Map.Pair<String, Integer> p = this.numbers.remove(this.last
                    .toLowerCase());
            number = p.value() + 1;
            this.numbers.add(this.last.toLowerCase(), number);
        } else {
            number = 1;
            this.numbers.add(this.last.toLowerCase(), number);
        }
        
        this.num = number;

        String email = this.last.toLowerCase() + "." + number + "@osu.edu";

        return email;
    }

    @Override
    public String toString() {

        String name = "Name: " + this.first + this.last;
        String email = "Email: " + this.last.toLowerCase() + "." + this.num + "@osu.edu";
        return name + ",  " + email;
    }
}
