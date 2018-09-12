package Chapter1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RingBuffer<T> implements Iterable<T> {
    private T[] elements;
    private int size;
    private int first;
    private int last;

    RingBuffer(int CAPACITY) {
        elements = (T[]) new Object[CAPACITY];
    }

    RingBuffer(RingBuffer<T> buffer) {
        elements = buffer.elements;
    }


    public void enqueue(T element) {
        if (size == elements.length)
            last = 0;
        elements[last++] = element;
        size++;
    }

    public T dequeue() {
        if (size == 0) throw new RuntimeException();
        T element = elements[first];
        first++;
        size--;
        if (first == elements.length) first = 0;
        return element;
    }


    public T peek() {
        if (size == 0) throw new NoSuchElementException();
        return elements[first];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = elements.length;
            int k = 0;

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                i--;
                return elements[k++];
            }
        };
    }
}
