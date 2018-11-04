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
        int size;  // кол-во узлов в данном поддереве
        boolean color; // цвет ссылки на узел

        Node(Key key, Value value, int size, boolean color) {
            this.value = value;
            this.key = key;
            this.size = size;
            this.color = color;
        }

    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return (x == null) ? 0 : x.size;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(Key key, Value value) {
        //Search key, if searched, will change value;
        // if no - increase tree
        if (key == null) throw new IllegalArgumentException("Null key");
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

        x.size = size(x.right) + size(x.left) + 1;
        return x;
    }

    public void deleteMin() {
        if (size() == 0) throw new NoSuchElementException("The BST is underflow");

        if (!(isRed(root.left)) && !(isRed(root.right)))
            root.color = RED;

        root = deleteMin(root);
        if (size() != 0) root.color = BLACK;
    }

    private Node deleteMin(Node x) {

        if (x.left == null)
            return null;
        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return balance(x);
    }

    public void deleteMax() {
        if (size() == 0) throw new NoSuchElementException("Tree underflow");

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (size() != 0) root.color = BLACK;

    }

    private Node deleteMax(Node x) {

        if (x.right == null) {
            return null;
        }
        if (!isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x.right);
        }
        x.right = deleteMax(x.right);

        return balance(x);
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
                x = rotateLeft(x);
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

    private Node balance(Node x) {
        flipColors(x);
        if (isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);

        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateLeft(x);
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
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
}
