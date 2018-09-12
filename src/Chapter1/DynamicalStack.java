package Chapter1;

/**
 * Algorithm 1.1
 */

import java.util.EmptyStackException;
import java.util.Iterator;

public class DynamicalStack<T> implements Iterable<T> {
    private T[] elements;
    private int size;

    public DynamicalStack(int CAPACITY) {
        elements = (T[]) new Object[CAPACITY];
        size = 0;
    }

    public DynamicalStack(DynamicalStack<T> stack){
        elements = stack.elements;
    }

    public void push(T element) {
        if (size == elements.length) resize(2 * elements.length);
        elements[size++] = element;
    }

    public T pop() {
        if (size == 0) throw new EmptyStackException();
        T element = elements[--size];
        elements[size] = null;
        if (size > 0 && size == elements.length / 4) resize(elements.length / 2);
        return element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int ex = size;

            @Override
            public boolean hasNext() {
                return ex > 0;
            }

            @Override
            public T next() {
                if (hasNext())
                    return elements[--ex];
                return null;
            }
        };
    }

    public DynamicalStack<T> copy(DynamicalStack<T> stack) {
        DynamicalStack<T> newStack = new DynamicalStack<>(stack.size());

        for (int i = 0; i < stack.size(); i++) {
            newStack.push(stack.elements[i]);
        }
        return newStack;
    }


    private void resize(int capacity) {
        T[] elementsTemp = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            elementsTemp[i] = elements[i];
        }
        elements = elementsTemp;

    }
}
