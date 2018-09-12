package Chapter1;

import edu.princeton.cs.algs4.StdDraw;
public class VisualCounter {
    private int count;
    private int N;
    private int max;
    private int i; // for N


    VisualCounter(int N, int max) {
        this.N = N;
        this.max = max;
    }

    public void increment() {
        if (count < max && i < N) {
            count++;
            i++;
            StdDraw.clear();
            paint();
        }
    }

    public void decrement() {
        if (i < N) {
            count--;
            i++;
            StdDraw.clear();
            paint();
        }
    }

    public int taily() {
        return count;
    }

    private void paint(){
        StdDraw.text(0.5,0.5, Integer.toString(count));

    }

    public String toString() {
        return count + " ";
    }

    public static void main(String[] args) {


    }

}
