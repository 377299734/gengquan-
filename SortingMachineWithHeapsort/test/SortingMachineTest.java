import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    /*
     * Test cases for constructors
     */

    @Test
    public final void testConstructorEmpty() {
        SortingMachine<String> test = this.constructorTest(ORDER);
        SortingMachine<String> ref = this.constructorRef(ORDER);

        assertEquals(test, ref);
    }

    @Test
    public final void testConstructorOne() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,
                "test 1");
        SortingMachine<String> ref = this.createFromArgsRef(ORDER, true,
                "test 1");

        assertEquals(test, ref);
    }

    @Test
    public final void testConstructorMultiple() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,
                "test 1", "test 2", "test 3");
        SortingMachine<String> ref = this.createFromArgsRef(ORDER, true,
                "test 1", "test 2", "test 3");

        assertEquals(test, ref);
    }

    /*
     * Test cases for add
     */

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> ref = this.createFromArgsRef(ORDER, true,
                "test 1");

        test.add("test 1");

        assertEquals(test, ref);
    }

    @Test
    public final void testAddMultiple() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,
                "test 1", "test 2", "test 3");
        SortingMachine<String> ref = this.createFromArgsRef(ORDER, true,
                "test 1", "test 2", "test 3", "test 4");

        test.add("test 4");

        assertEquals(test, ref);
    }

    /*
     * Test cases for changeToExtractionMode
     */

    @Test
    public final void testChangeToExtractionModeTrue() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true);

        test.changeToExtractionMode();

        assertFalse(test.isInInsertionMode());
    }

    /*
     * Test cases for removeFirst
     */

    @Test
    public final void testRemoveFirstEmpty() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, false,
                "test 1");
        SortingMachine<String> ref = this.createFromArgsRef(ORDER, false);

        String result = test.removeFirst();

        assertEquals(test, ref);
        assertEquals(result, "test 1");
    }

    @Test
    public final void testRemoveFirstMultiple() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, false,
                "test 1", "test 2", "test 3");
        SortingMachine<String> ref = this.createFromArgsRef(ORDER, false,
                "test 2", "test 3");

        String result = test.removeFirst();

        assertEquals(test, ref);
        assertEquals(result, "test 1");
    }

    /*
     * Test cases for order
     */

    @Test
    public final void testOrder() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,
                "test 3", "test 4", "test 1", "test 2");
        SortingMachine<String> ref = this.createFromArgsRef(ORDER, true,
                "test 1", "test 2", "test 3", "test 4");

        test.order();

        assertEquals(test, ref);
    }

    /*
     * Test cases for size
     */

    @Test
    public final void testSizeEmptyInsertionMode() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true);

        int size = test.size();

        assertEquals(size, 0);
    }

    @Test
    public final void testSizeMultipleInsertionMode() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true,
                "test 1", "test 2", "test 3");

        int size = test.size();

        assertEquals(size, 3);
    }

    @Test
    public final void testSizeEmptyExtractionMode() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, false);

        int size = test.size();

        assertEquals(size, 0);
    }

    @Test
    public final void testSizeMultipleExtractionMode() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, false,
                "test 1", "test 2", "test 3");

        int size = test.size();

        assertEquals(size, 3);
    }

    /*
     * Test cases for isInInsertionMode
     */

    @Test
    public final void testIsInInsertionModeTrue() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, true);

        assertTrue(test.isInInsertionMode());
    }

    @Test
    public final void testIsInInsertionModeFalse() {
        SortingMachine<String> test = this.createFromArgsTest(ORDER, false);

        assertFalse(test.isInInsertionMode());
    }

}
