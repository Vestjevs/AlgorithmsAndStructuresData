package Chapter1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class LinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    private static class Node<T> {
        T element;
        Node<T> prev;
        Node<T> next;

        Node(Node<T> prev, T element, Node<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
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
        Node<T> currElement = new Node<>(prevElement, element, null);
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

    public boolean removeAfter(Node<T> currNode) throws NoSuchFieldException {
        if (currNode.next == null) return false;

        currNode.next = currNode.next.next;
        currNode.next.prev = currNode;
        return true;
    }

    public void insert(int index, T element) {
        this.checkRange(index);

        if (index == this.size)
            this.addEnd(element);

        Node<T> lastNode = getNode(index);
        Node<T> newNode;
        if (index == 0) {
            addStart(element);
        }

        newNode = new Node<>(lastNode.prev, element, lastNode);
        if (newNode.prev != null) {
            newNode.prev.next = newNode;
        }
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        }
        size++;
    }

    public void insertBefore(int index, T element) throws NoSuchFieldException {
        this.checkRange(index);

        if (index == this.size)
            this.addEnd(element);
        if (index == 0) throw new NoSuchFieldException();

        Node<T> lastNode = getNode(index - 1);
        Node<T> newNode;

        newNode = new Node<>(lastNode.prev, element, lastNode);
        if (newNode.prev != null) {
            newNode.prev.next = newNode;
        }
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        }
        size++;
    }

    public void insertAfter(int index, T element) {
        this.checkRange(index);
        if (index == this.size)
            this.addEnd(element);

        Node<T> lastNode = getNode(index + 1);
        Node<T> newNode;

        newNode = new Node<>(lastNode.prev, element, lastNode);
        if (newNode.prev != null) {
            newNode.prev.next = newNode;
        }
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        }
        size++;
    }

    public T get(int index) {
        this.checkRange(index);
        return getNode(index).element;
    }

    public T removeEnd() {
//        if (size == 0) throw new RuntimeException();
//
//        T element = tail.element;
//        Node<T> lastNode = tail.prev;
//        tail = lastNode;
//
        return remove(size - 1);
//        size--;
//        return element;
    }

    public T removeStart() {
        if (size == 0) throw new RuntimeException();

        T element = head.element;
        Node<T> nextNode = head.next;

        head = nextNode;

        size--;
        return element;
    }

    public T remove(int index) {
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

    public int size() {
        return size;
    }

    public int find(T element) {
        int index = 0;
        Node<T> currNode = head;

        while (currNode != null) {
            if (currNode.element.equals(element))
                return index;
            currNode = currNode.next;
            index++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int findToRemove(T element) {
        int i = 0;
        for (Node<T> x = head; ; x = x.next) {
            if (x.element.equals(element)) return i;
            i++;
        }
    }

    public boolean remove(LinkedList<T> list, T element) {
        for (T value :
                list) {
            if (element.equals(value)) {
                list.remove(findToRemove(element));
                return true;
            }
        }
        return false;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> currNode = head;

            @Override
            public boolean hasNext() {
                if (currNode != null)
                    return true;
                return false;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T element = currNode.element;
                currNode = currNode.next;
                return element;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    private void checkRange(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
    }


}
