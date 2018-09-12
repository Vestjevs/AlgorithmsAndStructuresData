package Chapter1;
/**
 * Algorithm 1.3
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T element;
        Node<T> next;

        Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (this != obj) return false;
            if (this.getClass() != obj.getClass()) return false;

            Node<T> that = (Node<T>) obj;

            if (this.element != that.element) return false;
            return this.next == that.next;
        }

        public boolean removeAfter(Node<T> currNode) {
            if (currNode.next == null) return false;

            currNode.next = currNode.next.next;
            return true;
        }

        @Override
        public String toString() {
            return this.element + " ";
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void enqueue(T element) {
        Node<T> currNode = tail;
        Node<T> newNode = new Node<>(element, head);
        tail = newNode;


        if (size == 0) {
            newNode = new Node<>(element, null);
            head = newNode;
        } else {
            currNode.next = newNode;

        }
        size++;
    }

    public T dequeue() {
        T element = head.element;

        head = head.next;
        if (size == 0) tail = null;

        size--;

        return element;
    }

    private int count;


    private int i = 0;

    public void delete(int index) {
        this.checkRange(index);
        Node<T> toRemNode = getNode(index - 1);

        toRemNode.next = toRemNode.next.next;
        size--;

    }


    public boolean find(T key) {
        int index = 0;
        Node<T> currNode = head;

        while (currNode != null) {
            if (currNode.element.equals(key))
                return true;
            currNode = currNode.next;
            index++;
        }

        return false;
    }

    private Node<T> getNode(int index) {
        this.checkRange(index);

        int i = 0;
        for (Node<T> x = head; ; x = x.next) {
            if (i == index) return x;
            i++;
        }
    }

    private void checkRange(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int val = size;
            private Node<T> currNode = head;
            private T element;

            @Override
            public boolean hasNext() {
                return val > 0;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                element = currNode.element;
                currNode = currNode.next;
                val--;
                return element;
            }
        };
    }


}
