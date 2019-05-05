package Chapter3Find;

import java.util.LinkedList;

public class SeparateChainingHashST<Key, Value> {

    private int N;   //k pairs key-value
    private int M;  // size of hash-table
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this.M = 997;
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        if (N >= M / 2) resize(2 * M);
        st[hash(key)].put(key, value);
        N++;
    }

    private void resize(int size) {

    }

    public void delete(Key key) {
        st[hash(key)].delete(key);
        N--;
        if (N > 0 && N <= M / 8) resize(M / 2);
    }

    public Iterable<Key> keys() {
        LinkedList<Key> list = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            if (!st[i].isEmpty()) {
                for (int j = 0; j < st[i].size(); j++) {
                    list.add(st[i].iterator().next());
                }
            }
        }

        return list;
    }


}
