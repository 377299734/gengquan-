import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.statement.Statement;
import components.utilities.Tokenizer;

/**
 * JUnit test fixture for {@code Statement}'s constructor and kernel methods.
 *
 * @author Shannon O'Toole
 *
 */
public abstract class StatementTest {

    /**
     * The name of a file containing a sequence of BL statements.
     */
    private static final String FILE_NAME_1 = "test/statement1.bl",
            FILE_NAME_2 = "test/statement2.bl";

    /**
     * statement with the wrong condition in WHILE statement.
     */
    private static final String FILE_NAME_INVALID_WHILE_CONDITION = "test/statement13.bl";

    /**
     * statement with IF missing.
     */
    private static final String FILE_NAME_INVALID_IF_MISSING = "test/statement5.bl";

    /**
     * statement with THEN missing.
     */
    private static final String FILE_NAME_INVALID_THEN_MISSING = "test/statement10.bl";

    /**
     * statement with END missing.
     */
    private static final String FILE_NAME_INVALID_END_MISSING = "test/statement7.bl";

    /**
     * statement with the final IF missing.
     */
    private static final String FILE_NAME_INVALID_FINAL_IF_MISSING = "test/statement11.bl";

    /**
     * statement with the END missing in an IF_ELSE statement.
     */
    private static final String FILE_NAME_INVALID_ELSE_MISSING_END = "test/statement4.bl";

    /**
     * An invalid statement with WHILE missing.
     */
    private static final String FILE_NAME_INVALID_WHILE_MISSING = "test/statement5.bl";

    /**
     * An invalid statement with the DO missing in a WHILE statement.
     */
    private static final String FILE_NAME_INVALID_DO_MISSING = "test/statement3.bl";

    /**
     * statement with the END missing in a WHILE statement.
     */
    private static final String FILE_NAME_INVALID_END_MISSING_IN_WHILE = "test/statement6.bl";

    /**
     * statement with the final WHILE missing in a WHILE statement.
     */
    private static final String FILE_NAME_INVALID_FINAL_WHILE_MISSING = "test/statement12.bl";

    /**
     * statement with the final IF missing in the IF statement.
     */
    private static final String FILE_NAME_INVALID_FINAL_IF_MISSING_IF_BLOCK = "test/statement8.bl";

    /**
     * Invokes the {@code Statement} constructor for the implementation under
     * test and returns the result.
     *
     * @return the new statement
     * @ensures constructorTest = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Statement constructorTest();

    /**
     * Invokes the {@code Statement} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new statement
     * @ensures constructorRef = compose((BLOCK, ?, ?), <>)
     */
    protected abstract Statement constructorRef();

    /**
     * Test of parse on syntactically valid input.
     */
    @Test
    public final void testParseValidExample() {
        /*
         * Setup
         */
        Statement sRef = this.constructorRef();
        SimpleReader file = new SimpleReader1L(FILE_NAME_1);
        Queue<String> tokens = Tokenizer.tokens(file);
        sRef.parse(tokens);
        file.close();
        Statement sTest = this.constructorTest();
        file = new SimpleReader1L(FILE_NAME_1);
        tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call
         */
        sTest.parse(tokens);
        /*
         * Evaluation
         */
        assertEquals(sRef, sTest);
    }

    /**
     * Test of parse on syntactically invalid input.
     */
    @Test(expected = RuntimeException.class)
    public final void testParseErrorExample() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_2);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * IF statement with the END missing
     */
    @Test(expected = RuntimeException.class)
    public final void testInvalidMissingEND() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_END_MISSING);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * WHILE statement with invalid condition
     */
    @Test(expected = RuntimeException.class)
    public final void testInvalidWhileCondition() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_INVALID_WHILE_CONDITION);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * IF statement with the THEN missing
     */
    @Test(expected = RuntimeException.class)
    public final void testInvalidMissingTHEN() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_THEN_MISSING);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * IF statement with the IF missing
     */
    @Test(expected = RuntimeException.class)
    public final void testInvalidMissingIF() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_IF_MISSING);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * IF statement with the final IF missing
     */
    @Test(expected = RuntimeException.class)
    public final void testInvalidMissingFinalIF() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_INVALID_FINAL_IF_MISSING);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * invalid statement with WHILE missing
     */
    @Test(expected = RuntimeException.class)
    public final void testInvalidMissingWHILE() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_WHILE_MISSING);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * final IF missing in the IF block
     */
    @Test(expected = RuntimeException.class)
    public final void testInvalidMissingIFInIFBlock() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_INVALID_FINAL_IF_MISSING_IF_BLOCK);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);

    }

    /**
     * DO missing in an WHILE statement
     */
    @Test(expected = RuntimeException.class)
    public final void testInvalidDOMissingInWHILE() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(FILE_NAME_INVALID_DO_MISSING);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * END missing in an WHILE statement
     */
    @Test(expected = RuntimeException.class)
    public final void testInvalidENDMissingInWHILE() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_INVALID_END_MISSING_IN_WHILE);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * Final WHILE missing in an WHILE statement
     */
    @Test(expected = RuntimeException.class)
    public final void testInvalidFinalWHILEMissingInWHILE() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_INVALID_FINAL_WHILE_MISSING);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

    /**
     * END missing in an IF_ELSE statement
     */
    @Test(expected = RuntimeException.class)
    public final void testInvalidMissingIFWithELSE() {
        /*
         * Setup
         */
        Statement sTest = this.constructorTest();
        SimpleReader file = new SimpleReader1L(
                FILE_NAME_INVALID_ELSE_MISSING_END);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        /*
         * The call--should result in an error being caught
         */
        sTest.parse(tokens);
    }

}