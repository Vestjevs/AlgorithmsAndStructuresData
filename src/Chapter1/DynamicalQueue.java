package Chapter1;
/**
 * Algorithm 1.1
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class DynamicalQueue<T> implements Iterable<T> {
    private int head;
    private int tail;
    private int size;
    private T[] elements;


    DynamicalQueue(int CAPACITY) {
        elements = (T[]) new Object[CAPACITY];
        head = tail = 0;
    }

    DynamicalQueue(DynamicalQueue<T> queue){
        elements = queue.elements;
    }


    public void pushLeft(T element) {
        if (this.size == this.elements.length) resize(2 * elements.length);
        elements[tail++] = element;
        size++;
    }


    public T dequeue() {
        if (head == tail) throw new RuntimeException();
        T element = elements[head++];
        elements[head] = null;
        size--;
        if (size > 0 && size == elements.length / 4) resize(elements.length / 2);
        return element;
    }
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return elements[head];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int i = size;
            private int k = 0;

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    i--;
                    return elements[k++];
                }
                return null;
            }
        };
    }

    private void resize(int capacity) {
        T[] elementsTmp = (T[]) new Object[capacity];

        IntStream.range(0, size).forEachOrdered(i -> elementsTmp[i] = elements[i]);
        elements = elementsTmp;
    }

}

