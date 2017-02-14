import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code NaturalNumber}.
 *
 * @JakeAlvord
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {

        NaturalNumber total = new NaturalNumber2();

        XMLTree left = exp.child(0);
        XMLTree right = exp.child(1);

        NaturalNumber leftValue = new NaturalNumber2(0);
        NaturalNumber rightValue = new NaturalNumber2(0);

        if (left.hasAttribute("value")) {
            NaturalNumber copyLeft = new NaturalNumber2(Integer.parseInt(left
                    .attributeValue("value")));
            leftValue.transferFrom(copyLeft);
        }
        if (right.hasAttribute("value")) {
            NaturalNumber copyRight = new NaturalNumber2(Integer.parseInt(right
                    .attributeValue("value")));
            rightValue.transferFrom(copyRight);
        }

        NaturalNumber placeRight = new NaturalNumber2(rightValue);
        NaturalNumber placeLeft = new NaturalNumber2(leftValue);

        if (exp.isTag()) {
            if (exp.label().equals("plus")) {
                if (!left.hasAttribute("value")) {
                    total = evaluate(left);
                    total.add(rightValue);
                }
                if (!right.hasAttribute("value")) {
                    total = evaluate(right);

                    total.add(leftValue);
                }
                if (left.hasAttribute("value") && right.hasAttribute("value")) {
                    rightValue.add(leftValue);
                    total.add(rightValue);
                }
            }
            if (exp.label().equals("minus")) {
                if (!left.hasAttribute("value")) {
                    total = evaluate(left);
                    if (total.compareTo(placeRight) < 0) {
                        components.utilities.Reporter
                                .fatalErrorToConsole("Error in subtraction, negative occured.");
                    } else {
                        total.subtract(rightValue);
                    }
                }
                if (!right.hasAttribute("value")) {
                    total = evaluate(right);
                    if (total.compareTo(placeLeft) < 0) {
                        components.utilities.Reporter
                                .fatalErrorToConsole("Error in subtraction, negative occured.");
                    } else {
                        leftValue.subtract(total);
                        total.transferFrom(leftValue);
                    }
                }
                if (left.hasAttribute("value") && right.hasAttribute("value")) {
                    if (placeLeft.compareTo(placeRight) < 0) {
                        components.utilities.Reporter
                                .fatalErrorToConsole("Error in subtraction, negative occured.");
                    } else {
                        leftValue.subtract(rightValue);
                        total.add(leftValue);
                    }
                }
            }
            if (exp.label().equals("divide")) {
                if (!left.hasAttribute("value")) {
                    total = evaluate(left);
                    if (rightValue.isZero()) {
                        components.utilities.Reporter
                                .fatalErrorToConsole("Error in division, cannot divide by 0.");
                    } else {
                        total.divide(rightValue);
                    }
                }
                if (!right.hasAttribute("value")) {
                    total = evaluate(right);
                    if (total.isZero()) {
                        components.utilities.Reporter
                                .fatalErrorToConsole("Error in division, cannot divide by 0.");
                    } else {
                        leftValue.divide(total);
                        total.transferFrom(leftValue);
                    }
                }
                if (left.hasAttribute("value") && right.hasAttribute("value")) {
                    if (rightValue.isZero()) {
                        components.utilities.Reporter
                                .fatalErrorToConsole("Error in division, cannot divide by 0.");
                    } else {
                        leftValue.divide(rightValue);
                        total.add(leftValue);
                    }
                }
            }
            if (exp.label().equals("times")) {
                if (!left.hasAttribute("value")) {
                    total = evaluate(left);
                    total.multiply(rightValue);
                }
                if (!right.hasAttribute("value")) {
                    total = evaluate(right);
                    total.multiply(leftValue);
                }
                if (left.hasAttribute("value") && right.hasAttribute("value")) {
                    rightValue.multiply(leftValue);
                    total.add(rightValue);
                }
            }

        }

        return total;

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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            XMLTree newexp = exp.child(0);
            out.println(evaluate(newexp));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}