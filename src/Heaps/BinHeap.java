package Heaps;

import java.util.*;


public class BinHeap {

    private LinkedList<Node> rootList;
    private int size;
    private Node root;

    private class Node {
        int data;
        Node child;
        Node right;
        int degree;

        Node(int data, int degree) {
            this.data = data;
            this.degree = degree;
        }
    }

    public BinHeap() {
        rootList = new LinkedList<>();
        size = 0;

    }

    public BinHeap merge(BinHeap h1, BinHeap h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        BinHeap h = new BinHeap();
        Node currH = h.root;


        return h;
    }




}
