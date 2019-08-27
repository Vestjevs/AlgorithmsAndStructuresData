package Chapter3Find;

import java.util.Random;
import java.util.Scanner;

public class Treap<Key extends Comparable<Key>, Value> {

    private Node root;
    private Random r;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int prior;
        int degree;

        Node(Key key, Value value, int prior, int degree) {
            this.key = key;
            this.value = value;
            this.prior = prior;
            this.degree = degree;
        }

        public String toSString(Node x, String prefix) {
            if (x == null) {
                return "";
            } else {
                String indent = prefix + '\t';
                return String.format("%sNode -> %s; Priority -> %s \n%s%s", prefix, x.key, x.prior, toSString(x.left, indent), toSString(x.right, indent));
            }
        }


    }

    private class Pair {
        private Node a;
        private Node b;

        Pair(Node a, Node b) {
            this.a = a;
            this.b = b;
        }

    }

    public Treap() {
        r = new Random();
    }


    public void insert(Key key, Value value) {
        root = this.insert(root, key, value, r.nextInt(1000));
    }

    private Node insert(Node x, Key key, Value value, int prior) {
        if (x == null) {
            return new Node(key, value, prior, 1);
        }
        Pair pair = split(x, key);
        Node h = new Node(key, value, prior, 1);
        x = merge(pair.a, h);
        x = merge(x, pair.b);

        return x;
    }

    private Pair split(Node x, Key key) {
        Pair pair;
        if (x == null) {
            return new Pair(null, null);
        }
        if (key.compareTo(x.key) > 0) {
            pair = this.split(x.right, key);
            x.right = pair.a;
            return new Pair(x, pair.b);
        } else {
            pair = this.split(x.left, key);
            x.left = pair.b;
            return new Pair(pair.a, x);
        }

    }

    private Node merge(Node x, Node y) {
        if (y == null) {
            return x;
        }
        if (x == null) {
            return y;
        } else if (x.prior < y.prior) {
            x.right = merge(x.right, y);
            return x;
        } else {
            y.left = merge(x, y.left);
            return y;
        }
    }


    public void delete(Key key) {
        this.root = this.delete(this.root, key);
    }

    private Node delete(Node x, Key key) {
        Pair pair = this.split(x, key);
        pair.b = deleteNode(pair.b, key);
        x = this.merge(pair.a, pair.b);
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    private Node deleteNode(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = deleteNode(x.left, key);
        else if (cmp > 0) x.right = deleteNode(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;

        }
        return x;
    }


    private int size(Node x) {
        return (x == null) ? 0 : x.degree;
    }

    public int size() {
        return size(this.root);
    }

    public String toSString() {
        return String.format("%s", root.toSString(root, " "));
    }

    public static void main(String[] args) {
        Treap<Integer, Integer> treap = new Treap<>();
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while (i != 6) {
            System.out.print("Input value: ");
            treap.insert(sc.nextInt(), i++);
            System.out.println(treap.toSString());

        }

        treap.delete(43);
        System.out.println(treap.toSString());

    }

}
