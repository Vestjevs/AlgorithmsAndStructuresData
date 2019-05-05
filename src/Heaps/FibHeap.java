package Heaps;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FibHeap<T extends Comparable<T>> {

    private Node min;
    private int size;

    private class Node {
        Node left, right, child, parent;
        T key;
        int degree;
        boolean marked = false;

        Node(T key) {
            this.key = key;
            right = this;
            left = this;
        }

        public void addChild(Node node) {
            node.parent = this;
            node.removeSib();
            if (child == null) {
                child = node;
                degree++;
            } else {
                child = child.addSib(node);
                degree++;
            }
        }

        public void removeSib() {
            if (right == null) {
                return;
            }
            Node R = this.right;
            Node L = this.left;
            right.left = left;
            left.right = right;
            right = this;
            left = this;
        }

        public Node addSib(Node node) {
            if (key.compareTo(node.key) <= 0) {
                addRightSib(node);
                return this;
            } else {
                node.addListAsSib(this);
                return node;
            }
        }

        private void addRightSib(Node node) {
            right.left = node;
            node.left = this;
            node.right = right;
            right = node;
        }

        private void addListAsSib(Node node) {
            Node ne = node.left;
            Node cb = this;
            Node ce = this.right;
            ne.right = ce;
            ce.left = ne;
            cb.right = node;
            node.left = cb;
        }

        public int addChildAsSib(Node node) {
            if (node.child == null) {
                return 0;
            }
            addListAsSib(node.child);
            return node.degree;
        }

        public Node addTo(Node p) {
            if (p == null) {
                parent = null;
            } else {
                p.addChild(this);
            }
            return this;
        }

        public String toSString(Node node, String prefix) {
            return toSString(null, node, prefix);
        }

        private String toSString(Node start, Node node, String prefix) {
            if (node == null || node == start) {
                return "";
            }
            if (start == null) {
                start = node;
            }

            return String.format("%sNode %s; %o\n%s%s", prefix, node.key, node.degree, toSString(null, node.child, prefix + " "), toSString(start, node.right, prefix));
        }

        @Override
        public String toString() {
            return String.format("Node %s; %o", key, degree);
        }
    }

    public FibHeap() {
        this.min = null;
        this.size = 0;
    }

    public void insert(T key) {
        if (min == null) {
            min = new Node(key);
            size = 1;
            return;
        }
        min = min.addSib(new Node(key));
        size++;
    }

    public T getMin() {
        return min.key;
    }


    public void deleteMin() {
        if (min == null) {
            return;
        }
        Node m = min;
        size--;
        if (min.right != min) {
            min = min.right;
            m.removeSib();
            min.addChildAsSib(m);
            consolidate();
        } else {
            min = min.child;
        }
    }


    private void consolidate() {
        ArrayList<Node> list = IntStream.range(0, size).<Node>mapToObj(i -> null).collect(Collectors.toCollection(() -> new ArrayList<>(size)));
        Node current = min;
        while (list.get(current.degree) != current) {
            if (list.get(current.degree) == null) {
                list.set(current.degree, current);
                current = current.right;
            } else {
                Node conflict = list.get(current.degree);
                list.set(conflict.degree, null);
                if (conflict.key.compareTo(current.key) <= 0) {
                    conflict.addChild(current);
                    current = conflict;
                } else {
                    current.addChild(conflict);
                }
            }
            if(current.key.compareTo(min.key) <= 0){
                min = current;
            }
        }
    }

    public String toSString() {
        return String.format("Tree %o\n%s", size, min.toSString(min, " "));
    }

    public static void main(String[] args) {
        FibHeap<Integer> heap = new FibHeap<>();
        Random pr = new Random();

        IntStream.range(0, 15).mapToObj(i -> Math.abs(pr.nextInt() % 15)).forEach(heap::insert);
        heap.deleteMin();
        System.out.println(heap.toSString());


    }
}
