import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

public class StringReassemblyTest {

    @Test
    // boundary, should trip failure
    public void overlap1() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "";
        String str2 = "";
        int over = StringReassembly.overlap(str1, str2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, over);
    }

    @Test
    // boundary, empty strings
    public void overlap2() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "hello ";
        String str2 = "world";
        int over = StringReassembly.overlap(str1, str2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, over);
    }

    @Test
    // boundary, one overlap
    public void overlap3() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "hello w";
        String str2 = "world";
        int over = StringReassembly.overlap(str1, str2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(1, over);
    }

    @Test
    // routine, large overlap
    public void overlap4() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "hello world";
        String str2 = "world I am";
        int over = StringReassembly.overlap(str1, str2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(5, over);
    }

    @Test
    // boundary, no overlap
    public void combination1() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "hello world ";
        String str2 = "foo bar";
        int overlap = 0;
        String combo = StringReassembly.combination(str1, str2, overlap);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("hello world foo bar", combo);
    }

    @Test
    // boundary, one overlap
    public void combination2() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "hello w";
        String str2 = "world";
        int overlap = 1;
        String combo = StringReassembly.combination(str1, str2, overlap);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("hello world", combo);
    }

    @Test
    // routine, large overlap
    public void combination3() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "hello world";
        String str2 = "world, foo bar";
        int overlap = 5;
        String combo = StringReassembly.combination(str1, str2, overlap);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("hello world, foo bar", combo);
    }

    @Test
    // boundary, empty strings
    public void combination4() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "";
        String str2 = "";
        int overlap = 0;
        String combo = StringReassembly.combination(str1, str2, overlap);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("", combo);
    }

    @Test
    // boundary, should do nothing
    public void addToSetAvoidingSubstrings1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = new Set1L<>();
        String check = "";

        String str = "";
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        check = set.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(str, check);
    }

    @Test
    // routine, should add to the set
    public void addToSetAvoidingSubstrings2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = new Set1L<>();
        String check = "";

        String str = "hello world";
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        check = set.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(str, check);
    }

    @Test
    // error test, should fail, already in
    public void addToSetAvoidingSubstrings3() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = new Set1L<>();
        String check1 = "";
        String check2 = "";

        String str = "hello world";

        set.add(str);

        StringReassembly.addToSetAvoidingSubstrings(set, str);
        check1 = set.removeAny();
        check2 = set.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(str, check1);
        assertEquals(str, check2);
    }

}
