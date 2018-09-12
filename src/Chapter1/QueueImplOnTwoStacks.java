package Chapter1;

import java.util.Iterator;

public class QueueImplOnTwoStacks<T> implements Iterable<T> {

    private LinkedStack<T> stack1;
    private LinkedStack<T> stack2;

    QueueImplOnTwoStacks() {
        stack1 = new LinkedStack<>();
        stack2 = new LinkedStack<>();
    }

    public void enqueue(T element) {
        stack1.push(element);
    }

    public T dequeue() throws EmptyQueueException {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                throw new EmptyQueueException();
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int size() {
        return stack1.size();
    }

    public boolean isEmpty() {
        return stack1.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return stack1.iterator();
    }
}
