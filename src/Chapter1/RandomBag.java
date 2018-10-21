package Chapter1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomBag<T> implements Iterable<T> {
    private T[] elements;
    private int size;

    RandomBag(int CAPACITY) {
        elements = (T[]) new Object[CAPACITY];

    }

    public void add(T element) {
        if (this.size == elements.length) resize(2 * elements.length);
        elements[size++] = element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        T[] elementsTemp = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            elementsTemp[i] = elements[i];
        }
        elements = elementsTemp;
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
