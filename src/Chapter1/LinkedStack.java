package Chapter1;
/**
 * Algorithm 1.3
 */

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> implements Iterable<T> {


    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int count;

    private static class Node<T> {
        Node<T> prev;
        T element;
        Node<T> next;

        Node(T element, Node<T> next) {
//            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(T element) {
        Node<T> toAddNode = head;
        head = new Node<>(element, toAddNode);
        size++;
        count++;

    }

    public T pop() {
        T element = head.element;
        head = head.next;
        size--;
        count++;
        return element;
    }

    public T peek() {
        if (size == 0) throw new NoSuchElementException();
        return head.element;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int val = size;
            Node<T> nextNode = head;
            private int count1 = count;
            T nextElem;

            @Override
            public boolean hasNext() {
                if (count != count1) throw new ConcurrentModificationException();
                return val > 0;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                else if (count != count1) throw new ConcurrentModificationException();
                nextElem = nextNode.element;
                nextNode = nextNode.next;
                val--;
                return nextElem;
            }
        };
    }
}
