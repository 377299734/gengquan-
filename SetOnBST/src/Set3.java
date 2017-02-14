import java.util.Iterator;

import components.binarytree.BinaryTree;
import components.binarytree.BinaryTree1;
import components.set.Set;
import components.set.SetSecondary;

/**
 * {@code Set} represented as a {@code BinaryTree} (maintained as a binary
 * search tree) of elements with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Set} elements
 * @mathdefinitions <pre>
 * IS_BST(
 *   tree: binary tree of T
 *  ): boolean satisfies
 *  [tree satisfies the binary search tree properties as described in the
 *   slides with the ordering reported by compareTo for T, including that
 *   it has no duplicate labels]
 * </pre>
 * @convention IS_BST($this.tree)
 * @correspondence this = labels($this.tree)
 */
public class Set3<T extends Comparable<T>> extends SetSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private BinaryTree<T> tree;

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
    private static <T extends Comparable<T>> boolean isInTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";

        boolean contains = false;

        if (t.height() > 0) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T name = t.disassemble(left, right);

            if (x.compareTo(name) == 0) {
                contains = true;
            } else if (x.compareTo(name) < 0) {
                contains = isInTree(left, x);
            } else if (x.compareTo(name) > 0) {
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
                BinaryTree<T> port = t.newInstance();
                BinaryTree<T> starboard = t.newInstance();

                left.assemble(name, port, starboard);
                name = left.replaceRoot(x);
            } else if (x.compareTo(name) > 0 && right.height() > 0) {
                insertInTree(right, x);
            } else if (x.compareTo(name) > 0 && right.height() == 0) {
                BinaryTree<T> port = t.newInstance();
                BinaryTree<T> starboard = t.newInstance();

                right.assemble(name, port, starboard);
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
    private static <T> T removeSmallest(BinaryTree<T> t) {
        assert t != null : "Violation of: t is not null";

        T smallest = null;

        if (t.height() > 1) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);

            if (left.height() == 0 && right.height() > 0) {
                smallest = root;
                BinaryTree<T> port = t.newInstance();
                BinaryTree<T> starboard = t.newInstance();
                T node = right.disassemble(port, starboard);

                t.assemble(node, port, starboard);
            } else if (left.height() > 1) {
                removeSmallest(left);
            } else if (left.height() == 1) {
                BinaryTree<T> port = t.newInstance();
                BinaryTree<T> starboard = t.newInstance();
                T node = left.disassemble(port, starboard);

                smallest = node;
                t.assemble(root, left, right);
            }
        } else {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            smallest = t.disassemble(left, right);
        }

        return smallest;
    }

    /**
     * Finds label {@code x} in {@code t}, removes it from {@code t}, and
     * returns it.
     *
     * @param <T>
     *            type of {@code BinaryTree} labels
     * @param t
     *            the {@code BinaryTree} from which to remove label {@code x}
     * @param x
     *            the label to be removed
     * @return the removed label
     * @updates t
     * @requires IS_BST(t) and x is in labels(t)
     * @ensures <pre>
     * IS_BST(t)  and  removeFromTree = x  and
     *  labels(t) = labels(#t) \ {x}
     * </pre>
     */
    private static <T extends Comparable<T>> T removeFromTree(BinaryTree<T> t,
            T x) {
        assert t != null : "Violation of: t is not null";
        assert x != null : "Violation of: x is not null";
        assert isInTree(t, x) : "Violation of: x is in t";

        T removed = null;

        if (t.height() > 1) {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            T root = t.disassemble(left, right);

            removed = root;

            if (x.compareTo(root) < 0) {
                removed = removeFromTree(left, x);
                t.assemble(root, left, right);
            } else if (x.compareTo(root) > 0) {
                removed = removeFromTree(right, x);
                t.assemble(root, left, right);
            } else {
                if (left.height() > 0 && right.height() > 0) {
                    while (right.size() > 0) {
                        insertInTree(left, removeSmallest(right));
                    }
                    BinaryTree<T> port = t.newInstance();
                    BinaryTree<T> starboard = t.newInstance();
                    T node = left.disassemble(port, starboard);

                    t.assemble(node, port, starboard);
                } else if (left.height() > 0) {
                    BinaryTree<T> port = t.newInstance();
                    BinaryTree<T> starboard = t.newInstance();
                    T node = left.disassemble(port, starboard);

                    t.assemble(node, port, starboard);
                } else if (right.height() > 0) {
                    BinaryTree<T> port = t.newInstance();
                    BinaryTree<T> starboard = t.newInstance();
                    T node = right.disassemble(port, starboard);

                    t.assemble(node, port, starboard);
                }
            }
        } else {
            BinaryTree<T> left = t.newInstance();
            BinaryTree<T> right = t.newInstance();
            removed = t.disassemble(left, right);
        }

        return removed;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.tree = new BinaryTree1<>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Set3() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Set<T> newInstance() {
        try {
            return this.getClass().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("Cannot construct object of type "
                    + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Set<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Set3<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Set3<T> localSource = (Set3<T>) source;
        this.tree = localSource.tree;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert !this.contains(x) : "Violation of: x is not in this";

        insertInTree(this.tree, x);
    }

    @Override
    public final T remove(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.contains(x) : "Violation of: x is in this";

        T removed = removeFromTree(this.tree, x);

        return removed;
    }

    @Override
    public final T removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";

        T removed = removeSmallest(this.tree);

        return removed;
    }

    @Override
    public final boolean contains(T x) {
        assert x != null : "Violation of: x is not null";

        boolean has = isInTree(this.tree, x);

        return has;
    }

    @Override
    public final int size() {

        return this.tree.size();
    }

    @Override
    public final Iterator<T> iterator() {
        return this.tree.iterator();
    }

}
