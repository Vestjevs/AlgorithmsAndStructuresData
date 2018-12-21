package Chapter3Find;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.IntStream;

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
        } else {
            if (this.size == this.keys.length) {
                resize(2 * this.keys.length);
            }
            for (int j = size; j > i; j--) {
                keys[j] = keys[j - 1];
                values[j] = values[j - 1];
            }
            keys[i] = key;
            values[i] = value;
            size++;
        }
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

        if (size > 0 && this.size == this.keys.length / 4) {
            resize(this.keys.length / 2);
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

    private void resize(int k) {
        Key[] newKeys = (Key[]) new Comparable[k];
        Value[] newValues = (Value[]) new Object[k];
        IntStream.range(0, this.size).forEach(i -> {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        });
        keys = newKeys;
        values = newValues;
    }

    public static void main(String[] args) {
        BinarySearchST<String, Double> st = new BinarySearchST<>();
        st.put("A+", 4.33);
        st.put("A", 4.0);
        st.put("A-", 3.67);
        st.put("B+", 3.33);
        st.put("B", 3.0);
        st.put("B-", 2.67);
        st.put("C+", 2.0);
        st.put("C", 1.67);
        st.put("C-", 1.33);
        st.put("D", 1.0);
        st.put("F", .0);

        int sum = 0;
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        while (!str.equals("X")) {
            count++;
//            System.out.println(st.get(str));
            sum += st.get(str);
            str = scanner.nextLine();
        }
        System.out.println(sum / count);
    }
}
