package Chapter3Find;

import java.util.LinkedList;

public class LinearProbingHashST<Key, Value> {

    private int n; // k pairs key - value in table
    private int m = 16;
    private Key[] keys;
    private Value[] values;
    private int scorer; // scorer for lazy deleting

    public LinearProbingHashST() {
        n = 0;
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int size) {
        Key[] keys1 = (Key[]) new Object[size];
        Value[] values1 = (Value[]) new Object[size];

        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                int k;
                for (k = hash(keys[i]); keys1[k] != null; k = (k + 1) % size) {
                    if (keys1[k].equals(keys[i])) {
                        values1[k] = values[i];
                        return;
                    }
                }
                keys1[k] = keys[i];
                values1[k] = values[i];
            }
        }
        keys = keys1;
        values = values1;
        m = size;
    }

    public void put(Key key, Value value) {
        if (n >= m / 2) resize(2 * m);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        n++;
        scorer++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) return values[i];
        }
        return null;
    }

    public void delete(Key key) {
        int i = hash(key);
        while (!keys[i].equals(key))
            i = (i + 1) % m;

        keys[i] = null;
        values[i] = null;

        i = (i + 1) % m;
        while (keys[i] != null) {
            Key keyTo = keys[i];
            Value valueTo = values[i];
            keys[i] = null;
            values[i] = null;
//            n--;
            put(keyTo, valueTo);
            i = (i + 1) % m;
        }
        n--;
        if (n > 0 && n <= m / 8) resize(m / 2);
    }

    public Iterable<Key> iterator() {
        LinkedList<Key> list = new LinkedList<>();
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                list.add(keys[i]);
            }
        }
        return list;
    }

    public static void main(String[] args) {

    }

}
