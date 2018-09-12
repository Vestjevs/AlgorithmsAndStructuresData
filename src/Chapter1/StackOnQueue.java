package Chapter1;

import java.util.Iterator;

public class StackOnQueue<T> implements Iterable<T> {

    private LinkedQueue<T> queue;

    StackOnQueue() {
        queue = new LinkedQueue<>();
    }

    public void push(T element) {
        queue.enqueue(element);
    }

    public T pop() {
        while (queue.size() != 1) {
            queue.enqueue(queue.dequeue());
        }
        return queue.dequeue();
    }

    @Override
    public Iterator<T> iterator() {
        return queue.iterator();
    }
}
