package Chapter3Find;

import java.util.NoSuchElementException;
import java.util.Random;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int degree;  // кол-во узлов в данном поддереве
        boolean color; // цвет ссылки на узел

        Node(Key key, Value value, int degree, boolean color) {
            this.value = value;
            this.key = key;
            this.degree = degree;
            this.color = color;
        }


//        private String toSString(Node x, String prefix){
//
//            if (x == null) {
//                return "";
//            }
//            return  String.format("%sKey : %s; %o%n%s%s ", prefix, x.key.toString(), x.degree, toSString(x.left, prefix + " "), toSString(x.right, prefix));
//        }

    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.degree = h.degree;
        h.degree = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.degree = h.degree;
        h.degree = 1 + size(h.left) + size(h.right);
        return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return (x == null) ? 0 : x.degree;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public void insert(Key key, Value value) {
        //Search key, if searched, will change value;
        // if no - increase tree
        if (key == null) throw new IllegalArgumentException("Null key");
        if (value == null) {
            delete(key);
            return;
        }
        root = insert(root, key, value);
        root.color = BLACK;
    }

    private Node insert(Node node, Key key, Value value) {
        if (node == null)
            return new Node(key, value, 1, RED);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = insert(node.left, key, value);
        else if (cmp > 0) node.right = insert(node.right, key, value);
        else node.value = value;

        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.degree = size(node.right) + size(node.left) + 1;
        return node;
    }

    public void deleteMin() {
        if (root == null) throw new NoSuchElementException("BST underflow");

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (root != null) root.color = BLACK;

    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node node) {
        if (node.left == null)
            return null;

        if (!isRed(node.left) && !isRed(node.left.left))
            node = moveRedLeft(node);

        node.left = deleteMin(node.left);
        return balance(node);
    }

    public void deleteMax() {
        if (root == null) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (root != null) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the maximum key rooted at h
    private Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Argument to delete is null");
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);
        if (size() != 0) root.color = BLACK;
    }

    private Node delete(Node node, Key key) {
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            if (!isRed(node.left) && !isRed(node.left.left))
                node = moveRedLeft(node);
            node.left = delete(node.left, key);
        } else {
            if (isRed(node.left))
                node = rotateRight(node);
            if (cmp == 0 && (node.right == null))
                return null;
            if (!isRed(node.right) && !isRed(node.right.left))
                node = moveRedRight(node);
            if (cmp == 0) {
                Node h = min(node.right);
                node.key = h.key;
                node.value = h.value;
                node.right = deleteMin(node.right);
            } else node.right = delete(node.right, key);
        }
        return balance(node);
    }

    private Node balance(Node node) {
        if (isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.degree = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        else return min(x.left);
    }

    private Node max(Node x) {
        if (x.right == null)
            return x;
        else return max(x.right);
    }

    public String toSString() {
        return toSString(root, " ");
    }

    private String toSString(Node node, String s) {
        if (node == null) {
            return "";
        } else {
            String indent = s + "\t";
            return String.format("%sNode -> %s :%o\n%s%s", s, node.key.toString(), node.degree, toSString(node.left, indent), toSString(node.right, indent));

        }
    }


    public static void main(String[] args) {
        RedBlackBST<Integer, Integer> tree = new RedBlackBST<>();
        Random pr = new Random();
        for (int i = 0; i < 15; i++) {
            tree.insert(Math.abs(pr.nextInt() % 100), i);
        }

        System.out.println(tree.toSString());



    }
}
