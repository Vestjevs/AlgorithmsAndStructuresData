package Chapter3Find;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class SequentialSearchST<Key, Value> implements ST<Key, Value> {

    private Node first;
    private int size;

    private class Node {
        Node next;
        Key key;
        Value value;
        int degree;

        Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    SequentialSearchST() {

    }

    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
            first = new Node(key, value, first);
            size++;
        }
    }

    @Override
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        } else if (first == null) {
            System.out.println("ST is empty");
            System.exit(1);
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;

    }

    @Override
    public void delete(Key key) {
//        if (key == null) throw new NoSuchElementException("Key is null");
        if (isEmpty()) {
            System.out.println("ST is empty");
            System.exit(1);
        }
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        first = delete(first, key);

    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            size--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Key> iterator() {
        return new Iterator<Key>() {
            int k = size;
            Node x = first;
            Key key;

            @Override
            public boolean hasNext() {
                return k > 0;
            }

            @Override
            public Key next() {
                if (!hasNext()) {
                    return null;
                } else {
                    key = x.key;
                    x = x.next;
                    k--;
                    return key;
                }

            }
        };
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key min() {
        return null;
    }

    @Override
    public Key max() {
        return null;
    }

    public Iterable<Key> keys() {
        LinkedList<Key> list = new LinkedList<>();
        for (Node x = first; x != null; x = x.next) {
            list.add(x.key);
        }
        return list;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        Scanner in = new Scanner(System.in);
        int i = 0;
        while (true) {
            String str = in.nextLine();
            st.put(str, i++);
        }
//        for (String s :
//                st.keys()) {
//
//        }
    }

}
