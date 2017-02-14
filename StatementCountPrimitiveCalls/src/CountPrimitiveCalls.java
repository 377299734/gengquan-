import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Put your name here
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}.
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     *  [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: {
                /*
                 * Add up the number of calls to primitive instructions in each
                 * nested statement in the BLOCK.
                 */

                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement m = s.newInstance();
                    m = s.removeFromBlock(i);

                    count += countOfPrimitiveCalls(m);

                    s.addToBlock(i, m);
                }

                break;
            }
            case IF: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the IF.
                 */

                Statement m = s.newInstance();
                Condition c = s.disassembleIf(m);

                count += countOfPrimitiveCalls(m);

                s.assembleIf(c, m);

                break;
            }
            case IF_ELSE: {
                /*
                 * Add up the number of calls to primitive instructions in the
                 * "then" and "else" bodies of the IF_ELSE.
                 */

                Statement s1 = s.newInstance();
                Statement s2 = s.newInstance();
                Condition c = s.disassembleIfElse(s1, s2);

                count += countOfPrimitiveCalls(s1);

                count += countOfPrimitiveCalls(s2);

                s.assembleIfElse(c, s1, s2);

                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in the
                 * body of the WHILE.
                 */

                Statement m = s.newInstance();
                Condition c = s.disassembleWhile(m);

                count += countOfPrimitiveCalls(m);

                s.assembleWhile(c, m);

                break;
            }
            case CALL: {
                /*
                 * This is a leaf: the count can only be 1 or 0. Determine
                 * whether this is a call to a primitive instruction or not.
                 */

                String call = s.disassembleCall();

                if (call.equals("move") || call.equals("infect")
                        || call.equals("skip") || call.equals("turnleft")
                        || call.equals("turnright")) {
                    count = 1;
                }

                s.assembleCall(call);

                break;
            }
            default: {
                // this will never happen...can you explain why?
                // because it will never happen, the only possible cases
                // are stated above
                break;
            }
        }
        return count;
    }
}
