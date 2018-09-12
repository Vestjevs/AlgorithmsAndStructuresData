package Chapter1;

import edu.princeton.cs.algs4.StdRandom;

public class DoublingTest {
    private static final int MAX = 1_000_000;

    public static double timeTrial1(int N) {

        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        int count = ThreeSum.count1(a);
        return timer.elapsedTime();
    }

    public static double timeTrial2(int N) {
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        int count = ThreeSum.count2(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        for (int i = 250; i < 20000; i += i) {
            double time1 = timeTrial1(i);
            double time2 = timeTrial2(i);
            System.out.printf("%7d %6.3f %6.3f\n", i, time1, time2);
        }
    }
}
