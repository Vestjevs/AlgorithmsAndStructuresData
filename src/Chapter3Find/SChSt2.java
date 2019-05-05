package Chapter3Find;

public class SChSt2<Key, Value> {

    private int M; // size hash-table;
    private int N; // k pairs key-value;
    private Node[] st;

    private class Node {
        Node next;
        Key key;
        Value value;
        int k;

        Node(Key key, Value value, Node next, int k) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.k = k;
        }
    }

    SChSt2(int M) {
        this.M = M;
        st = (Node[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value value) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            x = new Node(key, value, x, N);
            N++;
        }
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (x.key.equals(key)) {
                return x.value;
            }
        }
        return null;
    }

    public void delete(Key key) {
        int i = hash(key);
        st[i] = delete(st[i], key);
    }



    public void deleteByK(int k) {
        for (int i = 0; i < st.length; i++) {
            for (Node x = st[i]; x != null; x = x.next) {
                if (x.k > k) {
                    delete(x.key);
                }
            }
        }
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key.equals(key)) {
            N--;
            return node.next;
        }
        node = delete(node.next, key);

        return node;
    }


    public static void main(String[] args) {

    }
}
