package Chapter3Find;

import java.util.Iterator;

public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> implements ST<Key, Value> {

    private int size;
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node next;

        Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
        size++;

    }

    private Node put(Node aux, Key key, Value value) {
        if (aux == null) {
            return new Node(key, value, null);
        }
//        Node x = new Node(key, value, null);
        if (key.compareTo(aux.key) < 0) {
            Node x = new Node(key, value, null);
            x.next = aux;
            return x;
        } else if (key.compareTo(aux.key) > 0) {
            put(aux.next, key, value);
        } else {
            for (Node x = aux; aux != null; aux = aux.next) {
                if (x.key.compareTo(key) == 0) {
                    x.value = value;
                }
            }
        }
        return aux;
    }

    @Override
    public Value get(Key key) {
        for (Node aux = root; aux.next != null; aux = aux.next) {
            if (aux.key.compareTo(key) == 0) {
                return aux.value;
            }
        }
        return null;
    }

    private Node getNode(Key key) {
        for (Node aux = root; aux != null; aux = aux.next) {
            if (aux.key.compareTo(key) == 0) {
                return aux;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {

    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
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
}
