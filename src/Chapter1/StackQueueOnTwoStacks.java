package Chapter1;

import java.util.Iterator;

public class StackQueueOnTwoStacks<T> implements Iterable<T> {

    private LinkedStack<T> frontStack;
    private LinkedStack<T> backStack;

    StackQueueOnTwoStacks() {
        frontStack = new LinkedStack<>();
        backStack = new LinkedStack<>();
    }

    /**
     * TO HEAD
     */
    public void pushFront(T element) {
        frontStack.push(element);
    }

    /**
     * TO TAIL
     */
    public void pushBack(T element) {
        backStack.push(element);
    }

    /**
     * FROM HEAD
     */
    public T pop() {
        if (!frontStack.isEmpty()) {
            return frontStack.pop();
        } else {
            int size = backStack.size();
            LinkedStack<T> localStack = new LinkedStack<>();
            for (int i = 0; i < size / 2; i++) {
                localStack.push(backStack.pop());
            }
            while (!backStack.isEmpty()) {
                frontStack.push(backStack.pop());
            }
            while (!localStack.isEmpty()) {
                backStack.push(localStack.pop());
            }
        }
        return frontStack.pop();
    }

    @Override
    public Iterator<T> iterator() {
        return frontStack.iterator();
    }
}
