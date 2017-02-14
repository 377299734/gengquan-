import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

public class NNtoStringWithCommasTest {
    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas5.toStringWithCommas(n);
    }

    /**
     * Test redirectToMethodUnderTest with input 1. Reason: challenging if
     * method works with single digit
     */
    @Test
    public void commaTest1() {

        // setting up variables

        NaturalNumber comma1 = new NaturalNumber2(1);
        String commaNumber1 = redirectToMethodUnderTest(comma1);

        String expected1 = "1";

        System.out.println(commaNumber1);

        // making sure variables meet expectations

        assertEquals(expected1, commaNumber1);

    }

    /**
     * Test redirectToMethodUnderTest with input 100. Reason: challenging if
     * method works with three digits
     */
    @Test
    public void commaTest2() {

        // setting up variables

        NaturalNumber comma2 = new NaturalNumber2(100);
        String commaNumber2 = redirectToMethodUnderTest(comma2);

        String expected2 = "100";

        System.out.println(commaNumber2);

        // making sure variables meet expectations

        assertEquals(expected2, commaNumber2);

    }

    /**
     * Test redirectToMethodUnderTest with input 9999. Reason: challenging if
     * method works
     */
    @Test
    public void commaTest3() {

        // setting up variables

        NaturalNumber comma3 = new NaturalNumber2(9999);
        String commaNumber3 = redirectToMethodUnderTest(comma3);

        String expected3 = "9,999";

        System.out.println(commaNumber3);

        // making sure variables meet expectations

        assertEquals(expected3, commaNumber3);

    }

    /**
     * Test redirectToMethodUnderTest with input 555,555. Reason: challenging if
     * method works with perfect multiple of 3
     */
    @Test
    public void commaTest4() {

        // setting up variables

        NaturalNumber comma4 = new NaturalNumber2(5000);
        String commaNumber4 = redirectToMethodUnderTest(comma4);

        String expected4 = "5,000";

        System.out.println(commaNumber4);

        // making sure variables meet expectations

        assertEquals(expected4, commaNumber4);

    }

    /**
     * Test redirectToMethodUnderTest with input 12,345,678,987,654,321. Reason:
     * routine
     */
    @Test
    public void commaTest5() {

        // setting up variables

        NaturalNumber comma5 = new NaturalNumber2("12345678987654321");
        String commaNumber5 = redirectToMethodUnderTest(comma5);

        System.out.println(commaNumber5);

        String expected5 = "12,345,678,987,654,321";

        NaturalNumber copy5 = new NaturalNumber2("12345678987654321");

        // making sure variables meet expectations

        assertEquals(expected5, commaNumber5);
        assertEquals(copy5, comma5);
    }

    /**
     * Test redirectToMethodUnderTest with input 12,345,678,987,654,321. Reason:
     * routine
     */
    @Test
    public void commaTest6() {

        // setting up variables

        NaturalNumber comma6 = new NaturalNumber2(0);
        String commaNumber6 = redirectToMethodUnderTest(comma6);

        System.out.println(commaNumber6);

        String expected6 = "0";

        // making sure variables meet expectations

        assertEquals(expected6, commaNumber6);

    }

    /**
     * Test redirectToMethodUnderTest with input 12,345,678,987,654,321. Reason:
     * routine
     */
    @Test
    public void commaTest7() {

        // setting up variables

        NaturalNumber comma7 = new NaturalNumber2(7);
        String commaNumber7 = redirectToMethodUnderTest(comma7);

        System.out.println(commaNumber7);

        String expected7 = "7";

        NaturalNumber copy7 = new NaturalNumber2(7);

        // making sure variables meet expectations

        assertEquals(expected7, commaNumber7);
        assertEquals(copy7, comma7);
    }
}
