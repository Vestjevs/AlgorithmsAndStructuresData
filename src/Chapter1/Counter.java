package Chapter1;

public class Counter {
    private final String name;
    private int count;

    public Counter(String id) {
        this.name = id;
    }

    public void increment() {
        count++;
    }

    public int tally() {
        return count;
    }

    public String toString() {
        return count + " " + name;
    }

}
