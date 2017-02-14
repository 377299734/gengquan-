import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Utility class with implementation of binary search tree static, generic
 * methods isInTree (and removeSmallest).
 *
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 *
 * @author Put your name here
 *
 */
public final class BinarySearchTreeMethods {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private BinarySearchTreeMethods() {
    }

    /**
     * Returns whether {@code x} is in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be searched for
     * @return true if t contains x, false otherwise
     * @requires IS_BST(t)
     * @ensures isInTree = (x is in labels(t))
     */
    public static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {

        boolean contains = false;

        if (t.height() > 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();

            T name = t.disassemble(left, right);

            if (x.compareTo(name) == 0) {
                contains = true;
            } else if (left.height() > 0 && x.compareTo(name) <= 0) {
                contains = isInTree(left, x);
            } else if (right.height() > 0 && x.compareTo(name) >= 0) {
                contains = isInTree(right, x);
            }

            t.assemble(name, left, right);
        }

        return contains;
    }

    /**
     * Inserts {@code x} in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} to be searched
     * @param x
     *            the label to be inserted
     * @aliases reference {@code x}
     * @updates t
     * @requires IS_BST(t) and x is not in labels(t)
     * @ensures IS_BST(t) and labels(t) = labels(#t) union {x}
     */
    private static <T extends Comparable<T>> void insertInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        if (t.height() > 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();

            T name = t.disassemble(left, right);

            if (x.compareTo(name) < 0 && left.height() > 0) {
                insertInTree(left, x);
            } else if (x.compareTo(name) < 0 && left.height() == 0) {
                BinaryTree<T> newLeft = t.newInstance();
                BinaryTree<T> newRight = t.newInstance();

                left.assemble(name, newLeft, newRight);
                name = left.replaceRoot(x);
            } else if (x.compareTo(name) > 0 && right.height() > 0) {
                insertInTree(right, x);
            } else if (x.compareTo(name) > 0 && right.height() == 0) {
                BinaryTree<T> newLeft = t.newInstance();
                BinaryTree<T> newRight = t.newInstance();

                right.assemble(name, newLeft, newRight);
                name = right.replaceRoot(x);
            }
            t.assemble(name, left, right);
        } else {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            t.assemble(x, left, right);
        }

    }

    /**
     * Removes and returns the smallest (left-most) label in {@code t}.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove the label
     * @return the smallest label in the given {@code BinaryTree}
     * @updates t
     * @requires IS_BST(t) and |t| > 0
     * @ensures <pre>
     * IS_BST(t)  and  removeSmallest = [the smallest label in #t]  and
     *  labels(t) = labels(#t) \ {removeSmallest}
     * </pre>
     */
    public static <T> T removeSmallest(BinaryTree<T> t) {

        T small = null;

        if (t.height() > 1) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();

            T root = t.disassemble(left, right);

            if (left.height() == 0 && right.height() > 0) {
                small = root;
                BinaryTree<T> rLeft = t.newInstance();
                BinaryTree<T> rRight = t.newInstance();

                T name = right.disassemble(rLeft, rRight);
                t.assemble(name, rLeft, rRight);
            } else if (left.height() > 1) {
                removeSmallest(left);
            } else if (left.height() == 1) {
                BinaryTree<T> lLeft = t.newInstance();
                BinaryTree<T> lRight = t.newInstance();

                T name = left.disassemble(lLeft, lRight);
                small = name;
                t.assemble(root, left, right);
            }
        } else {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            small = t.disassemble(left, right);
        }

        return small;
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

        /*
         * Input tree labels and construct BST.
         */
        out.println("Input the distinct labels for a binary search tree "
                + "in the order in which you want them inserted.");
        out.println("Press Enter on an empty line to terminate your input.");
        out.println();
        out.print("Next label: ");
        String str = in.nextLine();
        BinaryTree<String> t = new BinaryTree1<String>();
        while (str.length() > 0) {
            insertInTree(t, str);
            out.println();
            out.println("t = " + BinaryTreeUtility.treeToString(t));
            out.println();
            out.print("Next label: ");
            str = in.nextLine();
        }
        /*
         * Input strings and check whether each is in the BST or not.
         */
        out.println();
        out.print("  Input a label to search "
                + "(or just press Enter to input a new tree): ");
        String label = in.nextLine();
        while (label.length() > 0) {
            if (isInTree(t, label)) {
                out.println("    \"" + label + "\" is in the tree");
            } else {
                out.println("    \"" + label + "\" is not in the tree");
            }
            out.print("  Input a label to search "
                    + "(or just press Enter to terminate the program): ");
            label = in.nextLine();
        }
        /*
         * Output BST labels in order.
         */
        out.println();
        out.println("Labels in BST in order:");
        while (t.size() > 0) {
            out.println();
            label = removeSmallest(t);
            out.println("  " + label);
            out.println("t = " + BinaryTreeUtility.treeToString(t));
        }

        in.close();
        out.close();
    }
}
