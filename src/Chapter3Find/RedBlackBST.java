package Chapter3Find;

import java.util.NoSuchElementException;

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

        private String toSString(Node x, String prefix){

            if (x == null) {
                return "";
            }
            return  String.format("%sKey : %s; %o%n%s%s ", prefix, x.key.toString(), x.degree, toSString(x.left, prefix + " "), toSString(x.right, prefix));
        }

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

    public void put(Key key, Value value) {
        //Search key, if searched, will change value;
        // if no - increase tree
        if (key == null) throw new IllegalArgumentException("Null key");
        if(value == null) { delete(key); return; }
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null)
            return new Node(key, value, 1, RED);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;

        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);

        x.degree = size(x.right) + size(x.left) + 1;
        return x;
    }

    public void deleteMin() {
        if (root == null) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (root != null) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
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

    private Node delete(Node x, Key key) {
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            if(!isRed(x.left) && !isRed(x.left.left))
                x = moveRedLeft(x);
            x.left = delete(x.left, key);
        }
        else {
            if(isRed(x.left))
                x = rotateRight(x);
            if(cmp == 0 && (x.right == null))
                return null;
            if(!isRed(x.right) && !isRed(x.right.left))
                x = moveRedRight(x);
            if(cmp == 0){
                Node h = min(x.right);
                x.key = h.key;
                x.value = h.value;
                x.right = deleteMin(x.right);
            }
            else x.right = delete(x.right, key);
        }
        return balance(x);
    }

    private Node balance(Node h) {
       // flipColors(h);
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.degree = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node min(Node x){
        if(x.left == null)
            return x;
        else return min(x.left);
    }

    private Node max(Node x){
        if(x.right == null)
            return x;
        else return max(x.right);
    }

    @Override
    public String toString() {
        return root.toSString(root, " ");
    }

    public static void main(String[] args) {
        RedBlackBST<String, Integer> tree = new RedBlackBST<>();

        tree.put("a", 0);
        tree.put("e", 1);
        tree.put("h", 2);

        System.out.println(tree.toString());


    }
}
