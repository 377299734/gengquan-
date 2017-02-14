import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    /*
     * Test cases for constructors
     */

    /**
     * Test the no argument constructor.
     */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest();
        Set<String> ref = this.createFromArgsRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, ref);
    }

    /**
     * Test the one argument constructor.
     */
    @Test
    public final void testOneArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest("test 1");
        Set<String> ref = this.createFromArgsRef("test 1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, ref);
    }

    /**
     * Test the multiple argument constructor.
     */
    @Test
    public final void testMultipleArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this
                .createFromArgsTest("test 1", "test 2", "test 3");
        Set<String> ref = this.createFromArgsRef("test 1", "test 2", "test 3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, ref);
    }

    /*
     * Test cases for add
     */

    /**
     * Test empty add with empty String.
     */
    @Test
    public final void addEmptyWithEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest();
        Set<String> ref = this.createFromArgsRef();
        /*
         * Call method under test
         */
        test.add("");
        ref.add("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, ref);
    }

    /**
     * Test empty add with non empty String.
     */
    @Test
    public final void addEmptyWithNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest();
        Set<String> ref = this.createFromArgsRef();
        /*
         * Call method under test
         */
        test.add("test 1");
        ref.add("test 1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, ref);
    }

    /**
     * Test non empty add with non empty String.
     */
    @Test
    public final void addNonEmptyWithNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest("test 1");
        Set<String> ref = this.createFromArgsRef("test 1");
        /*
         * Call method under test
         */
        test.add("test 2");
        ref.add("test 2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, ref);
    }

    /**
     * Test multiple non empty add with non empty String.
     */
    @Test
    public final void addMultipleNonEmptyWithNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this
                .createFromArgsTest("test 1", "test 2", "test 3");
        Set<String> ref = this.createFromArgsRef("test 1", "test 2", "test 3");
        /*
         * Call method under test
         */
        test.add("test 4");
        ref.add("test 4");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, ref);
    }

    /*
     * Test cases for remove
     */

    /**
     * Test remove one leaving empty.
     */
    @Test
    public final void removeOneLeavingEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest("test 1");
        Set<String> ref = this.createFromArgsRef("test 1");
        /*
         * Call method under test
         */
        String testT = test.remove("test 1");
        String testR = ref.remove("test 1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, ref);
        assertEquals(testT, testR);
    }

    /**
     * Test remove one leaving one.
     */
    @Test
    public final void removeOneLeavingOneNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest("test 1", "test 2");
        Set<String> ref = this.createFromArgsRef("test 1", "test 2");
        /*
         * Call method under test
         */
        String testT = test.remove("test 2");
        String testR = ref.remove("test 2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, ref);
        assertEquals(testT, testR);
    }

    /**
     * Test remove one leaving multiple non empty.
     */
    @Test
    public final void removeOneLeavingMultipleNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest("test 1", "test 2",
                "test 3", "test 4");
        Set<String> ref = this.createFromArgsRef("test 1", "test 2", "test 3",
                "test 4");
        /*
         * Call method under test
         */
        String testT = test.remove("test 3");
        String testR = ref.remove("test 3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, ref);
        assertEquals(testT, testR);
    }

    /*
     * Test cases for removeAny
     */

    /**
     * Test removeAny one leaving empty.
     */
    @Test
    public final void removeAnyOneLeavingEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest("test 1");
        Set<String> ref = this.createFromArgsRef("test 1");
        /*
         * Call method under test
         */
        String testT = test.removeAny();
        String testR = ref.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(test, ref);
        assertEquals(testT, testR);
    }

    /**
     * Test removeAny one leaving one.
     */
    @Test
    public final void removeAnyOneLeavingOneNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest("test 1", "test 2");

        /*
         * Call method under test
         */
        String testT = test.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        if (testT.equals("test 1")) {
            assertTrue(testT.equals("test 1"));
        } else {
            assertTrue(testT.equals("test 2"));
        }
    }

    /**
     * Test removeAny one leaving multiple non empty.
     */
    @Test
    public final void removeAnyOneLeavingMultipleNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest("test 1", "test 2",
                "test 3", "test 4");
        /*
         * Call method under test
         */
        String testT = test.removeAny(); /*
                                          * Assert that values of variables
                                          * match expectations
                                          */
        if (testT.equals("test 1")) {
            assertTrue(testT.equals("test 1"));
        } else if (testT.equals("test 2")) {
            assertTrue(testT.equals("test 2"));
        } else if (testT.equals("test 3")) {
            assertTrue(testT.equals("test 3"));
        } else {
            assertTrue(testT.equals("test 4"));
        }
    }

    /*
     * Test cases for contains
     */

    /**
     * Test contains one.
     */
    @Test
    public final void containsOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest("test 1");
        /*
         * Call method under test
         */
        boolean contains = test.contains("test 1");
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(contains);
    }

    /**
     * Test contains on multiple.
     */
    @Test
    public final void containsMultiple() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this
                .createFromArgsTest("test 1", "test 2", "test 3");
        /*
         * Call method under test
         */
        boolean contains = test.contains("test 2");
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(contains);
    }

    /**
     * Test contains to test false.
     */
    @Test
    public final void containsFalse() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this
                .createFromArgsTest("test 1", "test 2", "test 3");
        /*
         * Call method under test
         */
        boolean contains = test.contains("test 4");
        /*
         * Assert that values of variables match expectations
         */
        assertTrue(!contains);
    }

    /*
     * Test cases for size
     */

    /**
     * Test size on empty set.
     */
    @Test
    public final void sizeEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest();
        /*
         * Call method under test
         */
        int size = test.size();
        int length = 0;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, length);
    }

    /**
     * Test size on set size 1.
     */
    @Test
    public final void sizeOneNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this.createFromArgsTest("test 1");
        /*
         * Call method under test
         */
        int size = test.size();
        int length = 1;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, length);
    }

    /**
     * Test size on multiple size set.
     */
    @Test
    public final void sizeMultipleNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> test = this
                .createFromArgsTest("test 1", "test 2", "test 3");
        /*
         * Call method under test
         */
        int size = test.size();
        int length = 3;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(size, length);
    }

}
