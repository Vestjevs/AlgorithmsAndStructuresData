package Heaps;

import java.util.ArrayList;

public class FibHeap {

    private Node root;
    private int size;

    private static class Node {
        Node left, right, child, parent;
        int key;
        int degree;

        Node(int key) {
            this.key = key;
            left = right = parent = child = null;
            degree = 1;
        }

        private static String toSString(Node x, String prefix) {
            if (x == null) {
                return "";
            }
            return String.format("%sNode: %s%s", prefix, x.key, toSString(x.child, prefix + " "));
        }
    }

    public FibHeap() {
        this.root = null;
        this.size = 0;
    }

    public void add(int key) {
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
        if (newNode.key < root.key) {
            root = newNode;
        }
        size++;
    }

    public int getMin() {
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

    public void merge(FibHeap that) {
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

    public int deleteMin() {
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
                if (conflict.key < curr.key) { // conflict.key < curr.key
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
            if (root.key  > curr.key) {
                root = curr;
            }
        }

    }

    public String ToSString() {
        return Node.toSString(root, " ");
    }

    public static void main(String[] args) {
        FibHeap heap = new FibHeap();

        heap.add(10);
        heap.add(12);
        heap.add(15);
        heap.add(18);
        heap.add(20);
        heap.deleteMin();
        System.out.println(heap.ToSString());

    }
}
