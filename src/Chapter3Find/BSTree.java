package Chapter3Find;


import java.util.Arrays;
import java.util.Random;

public class BSTree<Key extends Comparable<Key>, Value> {

    private Node root;

    class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int size;

        Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.size = n;
        }

        public String toSString(Node x, String prefix) {
            if (x == null) {
                return "";
            } else {
                String indent = prefix + '\t';
                return String.format("%sNode -> %s \n%s%s", indent, x.key, toSString(x.left, indent), toSString(x.right, indent));
            }
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else x.value = value;
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else return x.value;
    }




    public String toString() {
        return root.toSString(root, " ");
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BSTree<Integer, Integer> tree = new BSTree<>();
        for (int i = 0; i < 10; i++) {
            tree.put(arr[i], i);

        }

        System.out.println(Arrays.toString(arr));
        System.out.println(tree.toString());

    }

}
