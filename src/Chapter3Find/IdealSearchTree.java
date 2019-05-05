package Chapter3Find;

import java.util.LinkedList;
import java.util.Random;

public class IdealSearchTree {

    private LinkedList<Node> list;
    private Node root;

    static class Node implements Comparable {
        private int key;
        private Node left;
        private Node right;

        Node(int key) {
            this.key = key;
        }


        @Override
        public int compareTo(Object o) {
            Node that = (Node) o;
            if (this.key < that.key) {
                return -1;
            } else if (this.key > that.key) {
                return 1;
            }
            return 0;
        }


    }


    public IdealSearchTree() {
        list = new LinkedList<>();
    }


    private void make() {
        list.sort(Node::compareTo);
        root = makeIdeal(root, 0, list.size() - 1);
    }

    private Node makeIdeal(Node node, int lo, int hi) {
        int i = (lo + hi) / 2;
        node = list.get(i);
        if (lo > hi) {
            return null;
        }
        node.left = makeIdeal(node.left, lo, i - 1);
        node.right = makeIdeal(node.right, i + 1, hi);
        return node;
    }


    public void insert(int key) {
        Node node = new Node(key);
        list.add(node);
        make();
    }

    public void remove(int key) {
        int i = 0;
        while (list.get(i).key != key) {
            i++;
        }
        list.remove(i);
        make();
    }

    public int sizeOf() {
        return list.size();
    }


    public String toSString() {
        make();
        return toSString(root, " ");
    }

    private String toSString(Node node, String s) {
//        int i = (lo + hi) / 2;
        if (node == null) {
            return "";
        } else {
            String indent = s + "\t";
            return String.format("%sNode -> %s \n%s%s", s, node.key, toSString(node.left, indent), toSString(node.right, indent));
        }
    }


    public static void main(String[] args) {
        Random pr = new Random();
        int aux;
        IdealSearchTree tree = new IdealSearchTree();
//        for (int i = 0; i < 15; i++) {
//            aux = Math.abs(pr.nextInt() % 15);
//            tree.insert(aux);
////            System.out.println(aux);
//        }

        tree.insert(7);
        tree.insert(14);
        tree.insert(2);
        tree.insert(1);
        tree.insert(11);
        tree.insert(8);
        tree.insert(15);
        tree.insert(6);

        System.out.println(tree.toSString());
        tree.remove(1);
        System.out.println(tree.toSString());
    }

}
