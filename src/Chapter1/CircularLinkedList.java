package Chapter1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> implements Iterable<T> {
    private Node<T> tail;
    private Node<T> head;
    private int size;

    private static class Node<T> {
        Node<T> prev;
        T element;
        Node<T> next;

        Node(Node<T> prev, T element, Node<T> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (this != obj) return false;
            if (this.getClass() != obj.getClass()) return false;

            Node<T> that = (Node<T>) obj;
            if (this.prev != that.prev) return false;
            if (this.element != that.element) return false;
            return this.next == that.next;
        }
    }

    public void addEnd(T element) {
        Node<T> prevElement = tail;
        Node<T> currElement = new Node<>(prevElement, element, head);
        tail = currElement;

        if (prevElement == null) {
            head = currElement;
        } else {
            prevElement.next = currElement;
        }
        size++;
    }

    public void addStart(T element) {

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

    public T removeStart() {

        return remove(0);
    }

    public T removeEnd() {
        if (size == 0) throw new RuntimeException();

        T element = tail.element;
        Node<T> lastNode = tail.prev;
        tail = lastNode;


        size--;
        return element;
    }


    private T remove(int index) {
        this.checkRange(index);

        Node<T> RemNode = this.getNode(index);
        Node<T> Next = RemNode.next;
        Node<T> Prev = RemNode.prev;

        if (Next != null) {
            Next.prev = Prev;
        }
        if (Prev != null) {
            Prev.next = Next;
        }

        if (index == 0 && Next != null) {
            this.head = Next;
        }
        if (index == size - 1 && Prev != null) {
            this.tail = Prev;
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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void catena(CircularLinkedList<T> list) {

        Node<T> thatNodeHEAD = list.head; //THAT
        Node<T> thatNodeTAIL = list.tail;
        Node<T> thisNodeTAIL = tail; //THIS

        thatNodeHEAD.prev = thisNodeTAIL;
        thisNodeTAIL.next = thatNodeHEAD;
        this.tail = thatNodeTAIL;
        this.tail.next = head;
        this.head.prev = this.tail;
        size += list.size;

        int k = list.size;
        for (int i = 0; i < k; i++) {
            list.removeStart();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> currNode = head;
            private int k = size;

            @Override
            public boolean hasNext() {
                if (k > 0)
                    return true;
                return false;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T element = currNode.element;
                currNode = currNode.next;
                k--;
                return element;
            }
        };
    }

    private void checkRange(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
    }


}
