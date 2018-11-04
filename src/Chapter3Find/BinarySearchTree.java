package Chapter3Find;

import java.util.LinkedList;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key; // key
        private Value value;  // connected value
        private Node left, right; // links for left and right subtree
        private int size; // number of nodes  in subtree connected with curr root

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.size = n;
        }
    }

    BinarySearchTree() {
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // Возращаю значение, связанное с ключом в поддереве с корне xж
        // либо null. если ключ в subtree отсутствует
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        // if key is present in subtree with root x,
        // his value change to value
        // else in subtree add new node with Key key and Value value
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);

//        alternative variant
//         while (true) {
//            if (x.left == null) return x;
//            else {
//                x = x.left;
//            }
//         }
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
//         alternative variant
//        while(true) {
//          if (x.right == null) return x;
//          else {
//                x = x.right;
//          }
//        }
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) return t;
            else return x;
        }
        return ceiling(x.right, key);
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException();
        }
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        // Return number of keys, less x.key, in subtree with root in x
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return 1 + x.left.size + rank(x.right, key);
        else return x.left.size;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = x.left.size + x.right.size + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
//            or
//            x = max(t.left);
//            x.left = deleteMax(t.left);
//            x.right = t.right;
        }
        x.size = x.left.size + x.right.size + 1;
        return x;
    }

    public void print() {
        print(root);
        System.out.println();
    }

    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        System.out.println(x.key);
        print(x.right);

    }

    public void printAsTree() {
        printAsTree(root, "");
        System.out.println();
    }

    private void printAsTree(Node x, String s) {
        if (x == null) return;
        s += " - | ";
        printAsTree(x.right, s);
        System.out.println(s + x.key);
        printAsTree(x.left, s);
    }


    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        LinkedList<Key> list = new LinkedList<>();
        keys(root, list, lo, hi);
        return list;
    }

    private void keys(Node x, LinkedList<Key> list, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, list, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) list.addLast(x.key);
        if (cmphi > 0) keys(x.right, list, lo, hi);
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public int avgCompares() {
        return height() / size() + 1;
    }

    public static int optCompares(int N) {
        return 0;
    }

    public static void main(String[] args) {
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
        String test = "75468321";
        for (int i = 0; i < test.split("").length; i++) {
            bst.put(test.split("")[i], i);
        }
        bst.printAsTree();
    }
}


