package Chapter3Find;

public class SplayTree<Key extends Comparable<Key>, Value> {
    private Node root;


    private class Node {
        private Key key;
        private Value value;
        Node left;
        Node right;
        Node ancestor;
        int degree;


        Node(Key key, Value value, int degree) {
            this.key = key;
            this.value = value;
            this.degree = degree;
        }

        public String toSString(Node x, String prefix) {
            if (x == null) {
                return "";
            } else {
                String indent = prefix + "\t";
                return String.format("%sNode -> %s\n%s%s", indent, x.key, toSString(x.left, indent), toSString(x.right, indent));
            }
        }
    }


    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    private Node zig(Node x) {
        return null;
    }

    private Node zigZig(Node x) {
        return null;
    }

    private Node zigZag(Node x) {
        return null;
    }

    private Node splay(Node x) {
        Node p = x.ancestor;
        if (p == this.root) {
            x = this.zig(x);
        }
        Node g = p.ancestor;
        if (p.left == x && g.left == p) {
            x = this.zigZig(x);
        }
        if (p.right == x && p == g.left) {
            x = this.zigZag(x);
        }
        return x;
    }
}
