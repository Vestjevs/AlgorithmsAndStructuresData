package Chapter1;

public class Buffer {
    LinkedStack<Character> stack1;
    LinkedStack<Character> stack2;
    private int cursor;

    Buffer() {
        stack1 = new LinkedStack<>();
        stack2 = new LinkedStack<>();
    }

    public void insert(char c) {
        stack1.push(c);
    }

    public char delete() {
        return ' ';
    }

    public void left(int k) {
    }

    public void right(int k) {
    }

    public int size() {
        return stack1.size();
    }
}
