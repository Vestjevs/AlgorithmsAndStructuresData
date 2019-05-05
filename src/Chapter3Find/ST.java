package Chapter3Find;

import java.util.Iterator;

/**
 * Interface for ST
 * <p>
 * Include al basic methods
 */

public interface ST<Key, Value> extends Iterable<Key> {

    /**
     * Put new pair (key, value)
     * delete Key key, if value is null
     */
    void put(Key key, Value value);

    /**
     * Get value linked with key
     */
    Value get(Key key);

    /**
     * Delete key and value
     */
    void delete(Key key);

    /**
     * True if present, false if not
     */
    boolean contains(Key key);

    /**
     * True if size == 0, else false
     */
    boolean isEmpty();

    /**
     * Return size of pair in ST
     */
    int size();

    /**
     * Method for realisation Iterable
     */
    Iterator<Key> iterator();

//    Iterable<Key> keys();

    /**
     * Return k keys which less than current key
     */
    int rank(Key key);

    /**
     * Return minimum key
     */
    Key min();

    /**
     * Return maximum key
     */
    Key max();
}
