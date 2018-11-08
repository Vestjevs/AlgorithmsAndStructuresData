package Heaps;

import java.util.ArrayList;

public class FibHeap<Key extends Comparable<Key>> {

    private Node root;
    private int size;

    private class Node {
        Node left, right, child, parent;
        Key key;
        int degree;

        Node(Key key) {
            this.key = key;
            left = right = parent = child = null;
            degree = 1;
        }

        private String toString(Node x, String prefix) {
            if (x == null) {
                return "";
            }
            return String.format("%sKey : %s%s", prefix, x.key.toString(), x.child.key.toString());
        }
    }

    public FibHeap() {
        this.root = null;
        this.size = 0;
    }

    public void add(Key key) {
        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
            root.left = root.right = newNode;
        } else {
            Node auxNode = root.left;
            auxNode.right = newNode;
            newNode.left = auxNode;
            root.left = newNode;
            newNode.right = root;
        }
        if (newNode.key.compareTo(root.key) < 0) {
            root = newNode;
        }
        size++;
    }

    public Key getMin() {
        return root.key;
    }

    private void union(Node first, Node second) {
        Node l = first.left;
        Node r = second.right;
        second.right = first;
        first.left = second;
        l.right = r;
        r.left = l;
    }

    public void merge(FibHeap<Key> that) {
        if (that.size == 0) {
            return;
        }
        if (size == 0) {
            root = that.root;
            size = that.size;
        } else {
            union(root, that.root);
            size += that.size;
        }
    }

    public Key deleteMin() {
        Node aux = root;
        if (root.child != null) {
            union(root, root.child);
        }
        Node l = root.left;
        // удаляем root из списка
        Node r = root.right;
        l.right = r;
        r.left = l;
        if (aux.right == aux) {
            root = root.right;
        }
        consolidate();
        size--;
        return aux.key;
    }

    private void consolidate() {
        ArrayList<Node> arr = new ArrayList<>();
        arr.add(root.degree - 1, root);
        Node curr = root.right;
        while (true) {
            if (arr.get(curr.degree - 1) == null) {
                arr.add(curr.degree - 1, curr);
            } else {
                Node conflict = arr.get(curr.degree - 1);
                Node addTo, adding;
                if (conflict.key.compareTo(curr.key) < 0) {
                    addTo = conflict;
                    adding = curr;
                } else {
                    adding = conflict;
                    addTo = curr;
                }
                if (addTo.child != null) {
                    union(addTo.child, adding);
                    adding.parent = addTo;
                    addTo.degree++;
                }
            }
            if (root.key.compareTo(curr.key) > 0) {
                root = curr;
            }
        }

    }

    public String ToString() {
        return root.toString(root, "");
    }

    public static void main(String[] args) {
        FibHeap<Integer> heap = new FibHeap<>();

        heap.add(10);
        heap.add(12);
        heap.add(15);
        heap.add(18);
        heap.deleteMin();
//        System.out.println(heap.ToString());

    }
}
