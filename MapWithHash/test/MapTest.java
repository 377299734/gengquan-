import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires
     *
     *           <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     *           </pre>
     *
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert!map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires
     *
     *           <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     *           </pre>
     *
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert!map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    @Test
    public final void emptyTestCase() {
        Map<String, String> testMap = this.constructorTest();
        Map<String, String> refMap = this.constructorRef();

        assertEquals(refMap, testMap);
    }

    @Test
    public final void constructorWith1MapArgsTest() {
        Map<String, String> testMap = this.createFromArgsTest("meep", "moop");
        Map<String, String> refMap = this.createFromArgsRef("meep", "moop");

        assertEquals(refMap, testMap);
    }

    @Test
    public final void constructorWithMultipleMapsArgsTest() {
        Map<String, String> testMap = this.createFromArgsTest("meep", "moop",
                "sheep", "shoop");
        Map<String, String> refMap = this.createFromArgsRef("meep", "moop",
                "sheep", "shoop");

        assertEquals(refMap, testMap);
    }

    @Test
    public final void addToEmptyTest() {
        Map<String, String> testMap = this.constructorTest();
        Map<String, String> refMap = this.createFromArgsRef("meep", "moop");
        testMap.add("meep", "moop");

        assertEquals(refMap, testMap);
    }

    @Test
    public final void addToFullTest() {
        Map<String, String> testMap = this.createFromArgsTest("meep", "moop");
        Map<String, String> refMap = this.createFromArgsRef("meep", "moop",
                "sheep", "shoop");
        testMap.add("sheep", "shoop");

        assertEquals(refMap, testMap);
    }

    @Test
    public final void removeToEmptyTest() {
        Map<String, String> refMap = this.constructorRef();
        Map<String, String> testMap = this.createFromArgsTest("meep", "moop");
        testMap.remove("meep");

        assertEquals(refMap, testMap);
    }

    @Test
    public final void removeToNotEmptyTest() {
        Map<String, String> refMap = this.createFromArgsRef("meep", "moop",
                "sheep", "shoop");
        Map<String, String> testMap = this.createFromArgsTest("meep", "moop",
                "sheep", "shoop");
        Map.Pair<String, String> removed = testMap.remove("sheep");
        Map.Pair<String, String> toMatch = refMap.remove("sheep");

        assertEquals(refMap, testMap);
        assertEquals(toMatch, removed);
    }

    @Test
    public final void removeAnyToEmptyTest() {
        Map<String, String> refMap = this.createFromArgsRef("meep", "moop");
        Map<String, String> testMap = this.createFromArgsTest("meep", "moop");
        Map.Pair<String, String> removed = testMap.removeAny();
        Map.Pair<String, String> toMatch = refMap.remove("meep");

        assertEquals(refMap, testMap);
        assertEquals(toMatch, removed);

    }

    @Test
    public final void testHasKey() {
        Map<String, String> testPair = this.createFromArgsTest("meep", "moop");
        Map<String, String> expectedPair = this.createFromArgsRef("meep",
                "moop");
        boolean testBool = testPair.hasKey("meep");

        assertEquals(expectedPair, testPair);
        assertTrue(testBool);
    }

    @Test
    public final void testSize() {
        Map<String, String> testPair = this.createFromArgsTest("meep", "moop");
        Map<String, String> expectedPair = this.createFromArgsRef("meep",
                "moop");
        int testSize = testPair.size();
        int expectedSize = 1;

        assertEquals(expectedPair, testPair);
        assertEquals(expectedSize, testSize);
    }

    @Test
    public final void testValue() {
        Map<String, String> testPair = this.createFromArgsTest("meep", "moop");
        Map<String, String> expectedPair = this.createFromArgsRef("meep",
                "moop");
        String testValue = testPair.value("meep");
        String expectedValue = "moop";

        assertEquals(expectedPair, testPair);
        assertEquals(expectedValue, testValue);
    }

}
