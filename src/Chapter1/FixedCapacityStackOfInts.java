package Chapter1;

import java.util.EmptyStackException;

public class FixedCapacityStackOfInts {
    private int[] elements;
    private int size;

    FixedCapacityStackOfInts(int CAPACITY) {
        elements = new int[CAPACITY];
        size = 0;
    }

    public void push(int element) {
        if (size == elements.length) resize(2 * elements.length);
        elements[size++] = element;
    }

    public int pop(){
        if (size == 0) throw new EmptyStackException();
        int element = elements[--size];
        if (size > 0 && size == elements.length / 4) resize(elements.length / 2);
        return element;
    }


    private void resize(int capacity) {
        int[] elementsTemp = new int[capacity];

        for (int i = 0; i < size; i++) {
            elementsTemp[i] = elements[i];
        }
        elements = elementsTemp;
    }
}
