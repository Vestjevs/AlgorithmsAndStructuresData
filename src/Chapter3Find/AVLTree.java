package Chapter3Find;

import java.io.IOException;
import java.util.Scanner;

public class AVLTree<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        Value value;
        Key key;
        Node left, right;
        int degree;

        public Node(Key key, Value value, int degree) {
            this.key = key;
            this.value = value;
            this.degree = degree;
        }

        public String toSString(Node node, String prefix) {
            if (node == null) {
                return "";
            } else {
                String indent = prefix + '\t';
                return String.format("%sNode -> %s; \n%s%s", prefix, node.key, toSString(node.left, indent), toSString(node.right, indent));
            }
        }
    }

    public AVLTree() {
    }

    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    private Node insert(Node h, Key key, Value value) {
        if (h == null) {
            return new Node(key, value, 1);
        } else {
            int cmp = key.compareTo(h.key);
            if (cmp < 0) {
                h.left = insert(h.left, key, value);
            } else {
                h.right = insert(h.right, key, value);
            }

        }
        h = this.balance(h);

        h.degree = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node balance(Node h) {
        int b = nodeHeight(h.left) - nodeHeight(h.right);
        if (b >= 2) {
            if (this.nodeHeight(h.left.left) > this.nodeHeight(h.left.right) && this.nodeHeight(h.left.right) > 0) {
                h = this.bigRotateRight(h);
            } else {
                h = this.rotateRight(h);
            }
        } else if (b <= -2) {
            if (this.nodeHeight(h.right.right) > this.nodeHeight(h.right.left) && this.nodeHeight(h.right.left) > 0) {
                h = this.bigRotateLeft(h);
            } else {
                h = this.rotateLeft(h);
            }
        }
        return h;

    }

    private Node bigRotateRight(Node h) {
        h.right = this.rotateLeft(h.right);
        h = this.rotateRight(h);
        return h;
    }

    private Node bigRotateLeft(Node h) {
        h.left = this.rotateRight(h.right);
        h = this.rotateLeft(h);
        return h;
    }

    private int size(Node h) {
        return (h == null) ? 0 : h.degree;
    }

    public int size() {
        return size(this.root);
    }

    public void delete(Key key) {
        if (key == null) {
            System.out.println("Key is null");
        } else
            root = delete(root, key);

    }

    private Node delete(Node h, Key key) {
        if (h == null) {
            return null;
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = delete(h.left, key);
        } else if (cmp > 0) {
            h.right = delete(h.right, key);
        } else {
            if (h.right == null) return h.left;
            if (h.left == null) return h.right;
            Node t = h;
            h = max(t.left);
            h.left = deleteMax(t.left);
            h.right = t.right;
            h = this.balance(h);


        }
        h.degree = size(h.left) + size(h.right) + 1;
        return h;

    }

    private int nodeHeight(Node h) {
        if (h == null) {
            return -1;
        }
        int lefth = nodeHeight(h.left);
        int righth = nodeHeight(h.right);

        if (lefth > righth) {
            return lefth + 1;
        } else {
            return righth + 1;
        }


    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.degree = h.degree;
        h.degree = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.degree = h.degree;
        h.degree = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node deleteMax(Node h) {
        if (h.right == null) return h.left;
        h.right = deleteMax(h.right);
        h.degree = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node max(Node h) {
        if (h.right == null) return h;
        else return max(h.right);
    }


    public String toSString() {
        return String.format("%s", root.toSString(root, " "));
    }


    public static void main(String[] args) {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while (i != 7) {
            tree.insert(Integer.parseInt(sc.next()), i);
            i++;
            System.out.println(tree.toSString());

        }

        while (i > 0) {
            tree.delete(sc.nextInt());
            System.out.println(tree.toSString());
            i--;
        }

    }


}
