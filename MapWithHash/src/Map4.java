import java.util.Iterator;
import java.util.NoSuchElementException;

import components.array.Array;
import components.array.Array1L;
import components.map.Map;
import components.map.Map1L;
import components.map.MapSecondary;

/**
 * {@code Map} represented as a hash table using {@code Map}s for the buckets,
 * with implementations of primary methods.
 *
 * @param <K>
 *            type of {@code Map} domain (key) entries
 * @param <V>
 *            type of {@code Map} range (associated value) entries
 * @convention <pre>
 *
 * 1.) |$this.hashTable.entries| > 0  and
 * */
//  - The length of the array has to be greater than 0, shouldn't have to worry about this too much

/** 2.) for all i: integer, pf: PARTIAL_FUNCTION, x: K
 *     where (0 <= i  and  i < |$this.hashTable.entries|  and
 *            <pf> = $this.hashTable.entries[i, i+1)  and
 *            x is in DOMAIN(pf))
 *   ([computed result of x.hashCode()] mod |$this.hashTable.entries| = i))  and
 */
//    if i is a valid index in the hash table, and its true we are using a map at that
//    index, and there is a key at that index, then x.hashCode mod lengthOfTable is i
//
//    basically says that you can only find the key at bucket the code hashes to,
//    The last line ([computed result of x.hashCode()] mod |$this.hashTable.entries| = i))
//    literally tells you how to hash it

/** 3.) |$this.hashTable.examinableIndices| = |$this.hashTable.entries|  and
 **/
//  - The size of the set is equal to the length of the string, means that every single
//  bucket has to have a Map inside of it.

/** 4.) $this.size = sum i: integer, pf: PARTIAL_FUNCTION
 *     where (0 <= i  and  i < |$this.hashTable.entries|  and
 *            <pf> = $this.hashTable.entries[i, i+1))
 *   (|pf|)
 * </pre>
 */
// - the size of the big map has to be the size of all of the smaller maps

/**
 * @correspondence
 *
 *                 <pre>
 * this = union i: integer, pf: PARTIAL_FUNCTION
 *            where (0 <= i  and  i < |$this.hashTable.entries|  and
 *                   <pf> = $this.hashTable.entries[i, i+1))
 *          (pf)
 *                 </pre>
 */

// - @correspondence basically says that the union of all of the small Maps
//is the big Map

public class Map4<K, V> extends MapSecondary<K, V> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Default size of hash table.
     */
    private static final int DEFAULT_HASH_TABLE_SIZE = 100;

    /**
     * Buckets for hashing.
     */
    private Array<Map<K, V>> hashTable;

    /**
     * Total size of abstract {@code this}.
     */
    private int size;

    /**
     * Computes {@code a} mod {@code b} as % should have been defined to work.
     *
     * @param a
     *            the number being reduced
     * @param b
     *            the modulus
     * @return the result of a mod b, which satisfies 0 <= {@code mod} < b
     * @requires b > 0
     * @ensures
     *
     *          <pre>
     * 0 <= mod  and  mod < b  and
     * there exists k: integer (a = k * b + mod)
     *          </pre>
     */
    private static int mod(int a, int b) {
        assert b > 0 : "Violation of: b > 0";

        int toReturn = a % b;
        if (toReturn < 0) {
            toReturn = b + toReturn;
        }

        return toReturn;
    }

    /**
     * Creator of initial representation.
     *
     * @param hashTableSize
     *            the size of the hash table
     * @requires hashTableSize > 0
     * @ensures
     *
     *          <pre>
     * |$this.hashTable.entries| = hashTableSize  and
     * for all i: integer
     *     where (0 <= i  and  i < |$this.hashTable.entries|)
     *   ($this.hashTable.entries[i, i+1) = <{}>  and
     *    i is in $this.hashTable.examinableIndices)  and
     * $this.size = 0
     *          </pre>
     */
    private void createNewRep(int hashTableSize) {
        //i think this code is the right idea, but i'm not quite sure how we're
        //supposed to initialize each of the Map2s in the array
        //fixed the initializing - Storm
        this.hashTable = new Array1L<Map<K, V>>(hashTableSize);
        for (int counter = 0; counter < hashTableSize; counter++) {
            Map<K, V> trash = new Map1L<K, V>();
            this.hashTable.setEntry(counter, trash);
        }

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Map4() {
        //should be right
        this.createNewRep(DEFAULT_HASH_TABLE_SIZE);

    }

    /**
     * Constructor resulting in a hash table of size {@code hashTableSize}.
     *
     * @param hashTableSize
     *            size of hash table
     * @requires hashTableSize > 0
     * @ensures this = {}
     */
    public Map4(int hashTableSize) {
        //should be right
        this.createNewRep(hashTableSize);

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @SuppressWarnings("unchecked")
    @Override
    public final Map<K, V> newInstance() {
        try {
            return this.getClass().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        //should be right
        this.createNewRep(DEFAULT_HASH_TABLE_SIZE);
    }

    @Override
    public final void transferFrom(Map<K, V> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Map4<?, ?> : ""
                + "Violation of: source is of dynamic type Map4<?,?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Map4<?,?>, and
         * the ?,? must be K,V or the call would not have compiled.
         */
        Map4<K, V> localSource = (Map4<K, V>) source;
        this.hashTable = localSource.hashTable;
        this.size = localSource.size;
        localSource.createNewRep(DEFAULT_HASH_TABLE_SIZE);
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(K key, V value) {
        assert key != null : "Violation of: key is not null";
        assert value != null : "Violation of: value is not null";
        assert!this.hasKey(key) : "Violation of: key is not in DOMAIN(this)";

        //i find the bucket number by doing (key.hashCode) mod (the number of buckets),
        //then add the pair to that bucket
        int bucketNumber = mod(key.hashCode(), this.hashTable.length());
        Map<K, V> currentBucket = this.hashTable.entry(bucketNumber);
        //these first two lines are in nearly every kernel method to find out which bucket
        //the pair we're dealing with is in

        currentBucket.add(key, value);
        this.size++;
    }

    @Override
    public final Pair<K, V> remove(K key) {
        assert key != null : "Violation of: key is not null";
        assert this.hasKey(key) : "Violation of: key is in DOMAIN(this)";

        int bucketNumber = mod(key.hashCode(), this.hashTable.length());
        Map<K, V> currentBucket = this.hashTable.entry(bucketNumber);

        Map.Pair<K, V> removed = currentBucket.remove(key);
        //Just put it before the removed statement and it works, shouldn't really
        //change anything about how the method works
        this.size--;
        return removed;

    }

    @Override
    public final Pair<K, V> removeAny() {
        assert this.size() > 0 : "Violation of: this /= empty_set";
//        Changed method so that it can't remove one of the empty maps
//        initalized in the beginning.
        boolean foundOne = false;
        int counter = 0;
        Map<K, V> currentBucket = this.hashTable.entry(counter);
        Map<K, V> emptyMap = new Map1L<K, V>();
        while (!foundOne) {
            currentBucket = this.hashTable.entry(counter);
            if (!currentBucket.equals(emptyMap)) {
                foundOne = true;
            }
            counter++;
        }
        Map.Pair<K, V> removed = currentBucket.removeAny();
        this.size--;
        return removed;

    }

    @Override
    public final V value(K key) {
        assert key != null : "Violation of: key is not null";
        assert this.hasKey(key) : "Violation of: key is in DOMAIN(this)";

        int bucketNumber = mod(key.hashCode(), this.hashTable.length());
        Map<K, V> currentBucket = this.hashTable.entry(bucketNumber);

        return currentBucket.value(key);
    }

    @Override
    public final boolean hasKey(K key) {
        assert key != null : "Violation of: key is not null";

        int bucketNumber = mod(key.hashCode(), this.hashTable.length());
        boolean hasKey = false;
        //Had to adjust this, it was throwing errors with constructorWithArgsTest
        if (this.hashTable.mayBeExamined(bucketNumber)) {
            Map<K, V> currentBucket = this.hashTable.entry(bucketNumber);
            hasKey = currentBucket.hasKey(key);
        }
        return hasKey;
    }

    @Override
    public final int size() {
        return this.size;
    }

    @Override
    public final Iterator<Pair<K, V>> iterator() {
        return new Map4Iterator();
    }

    /**
     * Implementation of {@code Iterator} interface for {@code Map4}.
     */
    private final class Map4Iterator implements Iterator<Pair<K, V>> {

        /**
         * Number of elements seen already (i.e., |~this.seen|).
         */
        private int numberSeen;

        /**
         * Bucket from which current bucket iterator comes.
         */
        private int currentBucket;

        /**
         * Bucket iterator from which next element will come.
         */
        private Iterator<Pair<K, V>> bucketIterator;

        /**
         * No-argument constructor.
         */
        public Map4Iterator() {
            this.numberSeen = 0;
            this.currentBucket = 0;
            this.bucketIterator = Map4.this.hashTable.entry(0).iterator();
        }

        @Override
        public boolean hasNext() {
            return this.numberSeen < Map4.this.size;
        }

        @Override
        public Pair<K, V> next() {
            assert this.hasNext() : "Violation of: ~this.unseen /= <>";
            if (!this.hasNext()) {
                /*
                 * Exception is supposed to be thrown in this case, but with
                 * assertion-checking enabled it cannot happen because of assert
                 * above.
                 */
                throw new NoSuchElementException();
            }
            this.numberSeen++;
            if (this.bucketIterator.hasNext()) {
                return this.bucketIterator.next();
            }
            while (!this.bucketIterator.hasNext()) {
                this.currentBucket++;
                this.bucketIterator = Map4.this.hashTable
                        .entry(this.currentBucket).iterator();
            }
            return this.bucketIterator.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "remove operation not supported");
        }

    }

}
