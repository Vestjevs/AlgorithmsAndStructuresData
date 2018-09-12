package Chapter1;

import java.util.Iterator;

public class DeqImplOnTwoStacks<T> implements Iterable<T> {

    LinkedStack<T> stack1;
    LinkedStack<T> stack2;

    DeqImplOnTwoStacks() {
        stack1 = new LinkedStack<>();
        stack2 = new LinkedStack<>();
    }

    /**
     * ADD TO HEAD
     */
    public void pushFront(T element) {
        stack1.push(element);
    }

    /**
     * ADD TO TAIL
     */
    public void pushBack(T element) {
        stack2.push(element);
    }

    /**
     * REMOVE FROM HEAD
     */
    public T popFront() {
        if(!stack1.isEmpty()) {
            return stack1.pop();
        } else {
            int size = stack2.size();
            LinkedStack<T> localStack = new LinkedStack<>();
            for (int i = 0; i < size / 2; i++) {
                localStack.push(stack2.pop());
            }
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            while (!localStack.isEmpty()) {
                stack2.push(localStack.pop());
            }
        }
        return stack1.pop();
    }

    /**
     * REMOVE FROM TAIL
     */
    public T popBack() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else {
            int size = stack1.size();
            LinkedStack<T> localStack = new LinkedStack<>();
            for (int i = 0; i < size / 2; i++) {
                localStack.push(stack1.pop());
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            while (!localStack.isEmpty()) {
                stack1.push(localStack.pop());
            }
        }
        return stack2.pop();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
