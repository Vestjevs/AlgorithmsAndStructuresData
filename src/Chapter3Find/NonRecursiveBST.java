package Chapter3Find;

import java.util.LinkedList;
import java.util.Stack;

public class NonRecursiveBST<Key extends Comparable<Key>, Value> {

    private Node root;
    private int size;

    private class Node {
        private Node left, right;
        private Key key;
        private Value value;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }


    public void put(Key key, Value value) {
        Node z = new Node(key, value);
        if (root == null) {
            root = z;
        }
        Node parent = null, x = root;
        while (x != null) {
            parent = x;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                x.value = value;
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp < 0) parent.left = z;
        else parent.right = z;
    }


    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        while (true) {
            if (x == null) throw new IllegalArgumentException("any keys in Tree");
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
    }

    public Iterable<Key> keys() {
        Stack<Node> stack = new Stack<>();
        LinkedList<Key> list = new LinkedList<>();
        Node x = root;
        while ((x != null || !stack.empty())) {
            if (x != null) {
                stack.push(x);
                x = x.left;
            } else {
                x = stack.pop();
                list.addLast(x.key);
                x = x.right;
            }
        }
        return list;
    }
}
