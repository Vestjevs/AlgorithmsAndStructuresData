package Chapter1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public static class Node<T> {
        Node<T> prev;
        T element;
        Node<T> next;

        Node(Node<T> prev, T element, Node<T> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }


    /**
     * Start
     */
    public void pushLeft(T element) {
        Node<T> currNode = head;
        Node<T> newNode = new Node<>(tail, element, currNode);
        head = newNode;
        if (currNode == null) {
            head = new Node<>(null, element, null);
            size++;
            return;
        } else if (size == 1) {
            currNode.prev = head;
            tail = currNode;
        } else {
            currNode.prev = head;
            head.prev = tail;
        }
        size++;
    }

    /**
     * End
     */
    public void pushRight(T element) {
        Node<T> prevElement = tail;
        Node<T> currElement = new Node<>(prevElement, element, null);
        tail = currElement;

        if (prevElement == null) {
            head = currElement;
        } else {
            prevElement.next = currElement;
        }
        size++;
    }

    /**
     * Start
     */
    public T popLeft() {
        if (size == 0) throw new RuntimeException();

        T element = head.element;
        Node<T> lastNode = head.next;

        head = lastNode;

        size--;
        return element;

    }

    /**
     * End
     */
    public T popRight() {
        return popByIndex(size - 1);
    }

    private T popByIndex(int index) {
        this.checkRange(index);

        Node<T> RemNode = this.getNode(index);
        Node<T> NextFromRemNode = RemNode.next;
        Node<T> PrevFromRemNode = RemNode.prev;

        if (NextFromRemNode != null) {
            NextFromRemNode.prev = PrevFromRemNode;
        }
        if (PrevFromRemNode != null) {
            PrevFromRemNode.next = NextFromRemNode;
        }

        if (index == 0 && NextFromRemNode != null) {
            this.head = NextFromRemNode;
        }
        size--;
        return RemNode.element;
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


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node<T> currNode = head;

            @Override
            public boolean hasNext() {
                return currNode != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                T element = currNode.element;
                currNode = currNode.next;
                return element;
            }
        };
    }
}
