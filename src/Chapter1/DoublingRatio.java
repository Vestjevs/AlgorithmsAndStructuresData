package Chapter1;

import edu.princeton.cs.algs4.StdRandom;

public class DoublingRatio {
    private static final int MAX = 1_000_000;

    public static double timeTrial(int n) {
        int[] arrays = new int[n];
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = StdRandom.uniform(-MAX, MAX);
        }
        Stopwatch timer = new Stopwatch();
        int count = TwoSumFast.count(arrays);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        double prev = timeTrial(125);

        for (int i = 250; true; i += i) {
            double time = timeTrial(i);
            System.out.printf("%6d %7.1f ", i, time);
            System.out.printf("%5.1f\n", time / prev);
            prev = time;
        }
    }
}
