package Chapter3Find;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class BinarySearchST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {


    private Key[] keys;
    private Value[] values;
    private int size;
    private int capacity;

    public BinarySearchST() {

        keys = (Key[]) new Comparable[5];
        values = (Value[]) new Object[5];
        this.capacity = capacity;
    }

    public BinarySearchST(BinarySearchST that) {
        this.keys = (Key[]) that.keys;
        this.values = (Value[]) that.values;
    }


    @Override
    public void put(Key key, Value value) {
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        for (int j = size; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        size++;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) return null;
        check(key);
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) return values[i];
        else
            return null;
    }

    @Override
    public void delete(Key key) {
        check(key);
//        int i = rank(key);
        if (!isEmpty()) {
            for (int j = rank(key); j < size - 1; j++) {
                this.keys[j] = this.keys[j + 1];
                this.values[j] = this.values[j + 1];
            }
            this.size--;
            this.keys[this.size] = null;
            this.values[this.size] = null;
        }
    }

    @Override
    public boolean contains(Key key) {
        check(key);
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Key> iterator() {
        return null;
    }

    @Override
    public int rank(Key key) {
        check(key);
        return rank(key, 0, size - 1);
    }

    @Override
    public Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[size - 1];
    }

    public Key floor(Key key) {
        check(key);
        int i = rank(key);
        if (i < size && key.compareTo(keys[i]) == 0) {
            return keys[i];
        } else
            return i == size ? null : keys[i - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    private void check(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
    }

    public Key ceiling(Key key) {
        check(key);
        int i = rank(key);
        return i == size ? null : keys[i];
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        LinkedList<Key> list = new LinkedList<>(Arrays.asList(keys).subList(rank(lo), rank(hi)));
        if (contains(hi)) {
            list.add(keys[rank(hi)]);
        }
        return list;
    }

    private int rank(Key key, int lo, int hi) {
        if (hi < lo) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp < 0) {
            return rank(key, lo, mid - 1);
        } else if (cmp > 0) {
            return rank(key, mid + 1, hi);
        } else return mid;
    }

    //non recursive
    /*
     * int rank(Key key){
     *   int lo = 0, hi = size -1;
     *   while(lo <= hi){
     *      int mid = lo + (hi - lo) / 2;
     *      int cmp = key.compareTo(keys[mid]);
     *      if(cmp < 0) hi = mid - 1;
     *      else if(cmp > 0) lo = mid + 1;
     *      else return mid;
     *   }
     *   return lo;
     * }*/
}
