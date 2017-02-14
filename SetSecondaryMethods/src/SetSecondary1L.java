import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * Default constructor.
     */
    public SetSecondary1L() {
        super();
    }

    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        if (s.size() != 0) {

            Set<T> copy = new Set1L<>();

            while (copy.size() != s.size()) {
                T first = s.removeAny();
                copy.add(first);
                s.add(first);
            }

            while (copy.size() != 0) {
                T random = copy.removeAny();
                if (this.contains(random)) {
                    this.remove(random);
                }
            }
        }
        return this;

    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        if (s.size() != 0) {

            while (s.size() != 0) {
                T next = s.removeAny();
                if (!this.contains(next)) {
                    this.add(next);
                }
            }
        }

    }

}