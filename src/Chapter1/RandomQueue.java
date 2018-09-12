package Chapter1;

import java.util.*;

public class RandomQueue<T> implements Iterable<T> {
    private T[] elements;
//    private static final int CAPACITY = 10;
    private int size;
    private int head;
    private int tail;

    RandomQueue(int CAPACITY) {
        elements = (T[]) new Object[CAPACITY];

    }

    public void enqueue(T element) {
        if (this.size == elements.length) resize(2 * elements.length);
        elements[size++] = element;

    }

    public T dequeue() {
        if (size == 0) throw new NoSuchElementException();

        Random random = new Random();
        T element;

        int R = random.nextInt(size - 1);
        element = elements[R];
        elements[R] = elements[size - 1];
        elements[size - 1] = element;

        T element1 = elements[--size];
        if (size > 0 && size == elements.length / 4) resize(elements.length / 2);

        return element1;
    }

    public T sample() {
        if (size == 0) throw new NoSuchElementException();

        Random random = new Random();
        int R = random.nextInt(tail + 1);
        return elements[R];
    }


    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int capacity) {
        T[] tmpElements = (T[]) new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            tmpElements[i] = this.elements[i];
        }
        elements = tmpElements;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        int count = size;
        int i = 0;
        T[] tmpElement = elements;

        ListIterator() {
            Collections.shuffle(Arrays.asList(tmpElement));
        }

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            count--;
            return tmpElement[i++];
        }

    }
}
