import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @JakeAlvord
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("0", n.toString());
        assertEquals("0", m.toString());
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber m = new NaturalNumber2(21);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals("3", n.toString());
        assertEquals("0", m.toString());
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("0", n.toString());
        assertTrue(result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals("1", n.toString());
        assertTrue(!result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("0", p.toString());
        assertEquals("2", m.toString());
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals("1", n.toString());
        assertEquals("18", p.toString());
        assertEquals("19", m.toString());
    }

    /*
     * Test of isPrime1
     */
    @Test
    // boundary
    public void testIsPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean truth = true;
        assertEquals(truth, CryptoUtilities.isPrime1(n));
    }

    // routine
    @Test
    public void testIsPrime1_15() {
        NaturalNumber n = new NaturalNumber2(15);
        boolean wrong = false;
        assertEquals(wrong, CryptoUtilities.isPrime1(n));
    }

    // large number routine
    @Test
    public void testIsPrime1_3788923469() {
        NaturalNumber n = new NaturalNumber2("3788923469");
        boolean truth = true;
        assertEquals(truth, CryptoUtilities.isPrime1(n));
    }

    /*
     * Test of isPrime2
     */
    @Test
    // boundary
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        boolean truth = true;
        assertEquals(truth, CryptoUtilities.isPrime2(n));
    }

    @Test
    // routine
    public void testIsPrime2_27() {
        NaturalNumber n = new NaturalNumber2(27);
        boolean wrong = false;
        assertEquals(wrong, CryptoUtilities.isPrime2(n));
    }

    @Test
    // large routine
    public void testIsPrime2_3788923469() {
        NaturalNumber n = new NaturalNumber2("3788923469");
        boolean truth = true;
        assertEquals(truth, CryptoUtilities.isPrime2(n));
    }

    /*
     * Test of isWitnessToCompositeness
     */
    @Test
    // boundary
    public void testIsWitnessToCompositeness_2_30() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(30);
        assertEquals("2", w.toString());
        assertEquals("30", n.toString());
        boolean truth = true;
        assertEquals(truth, CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    @Test
    // routine
    public void testIsWitnessToCompositeness_15_65() {
        NaturalNumber w = new NaturalNumber2(15);
        NaturalNumber n = new NaturalNumber2(65);
        assertEquals("15", w.toString());
        assertEquals("65", n.toString());
        boolean wrong = false;
        assertEquals(wrong, CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    @Test
    // large routine
    public void testIsWitnessToCompositeness_30_990() {
        NaturalNumber w = new NaturalNumber2(30);
        NaturalNumber n = new NaturalNumber2(990);
        assertEquals("30", w.toString());
        assertEquals("990", n.toString());
        boolean truth = true;
        assertEquals(truth, CryptoUtilities.isWitnessToCompositeness(w, n));
    }

    /*
     * Test of generateNextLikelyNumber
     */
    @Test
    // boundary
    public void testGenerateNextLikelyPrime_4() {
        NaturalNumber n = new NaturalNumber2(4);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("5", n.toString());
    }

    // routine
    public void testGenerateNextLikelyPrime_20() {
        NaturalNumber n = new NaturalNumber2(20);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("23", n.toString());
    }

    // large routine
    public void testGenerateNextLikelyPrime_3788923467() {
        NaturalNumber n = new NaturalNumber2("3788923467");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals("3788923469", n.toString());
    }

}