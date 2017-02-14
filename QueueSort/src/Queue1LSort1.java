import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 */
public final class Queue1LSort1 extends Queue1L<String> {

    /**
     * No-argument constructor.
     */
    public Queue1LSort1() {
        super();
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * (q * <removeMin>) is permutation of #q  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";

        String small = "";
        boolean all = false;
        int tracker = 0;
        int teller = 0;

        while (all) {
            String trial = q.front();
            int count = 0;
            while (count != q.length()) {
                q.rotate(1);
                String second = q.front();
                count++;
                if (order.compare(trial, second) <= 0) {
                    teller++;
                }
            }
            if (teller == q.length() - 1) {
                all = true;
                small = q.dequeue();
                q.rotate(0 - tracker);
            } else {
                q.rotate(1);
                tracker++;
            }
        }

        return small;
    }

    @Override
    public void sort(Comparator<String> order) {
        assert order != null : "Violation of: order is not null";

        Queue<String> temp = new Queue1L<>();

        while (this.length() != 0) {
            temp.enqueue(removeMin(this, order));
        }
        while (temp.length() != 0) {
            this.enqueue(temp.dequeue());
        }

    }

}