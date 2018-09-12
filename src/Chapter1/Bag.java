package Chapter1;

import java.util.Iterator;

public class Bag<T> implements Iterable<T> {

    private Node<T> head;
    private int size;

    private static class Node<T> {
        T element;
        Node<T> next;

        Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }


    public void add(T element) {
        Node<T> newNode = head;
        head = new Node<>(element, newNode);
        size++;
    }

    public int sze() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> currNode = head;
            T element;

            @Override
            public boolean hasNext() {
                return currNode != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    element = currNode.element;
                    currNode = currNode.next;
                    return element;
                } else
                    return null;
            }
        };
    }
}
