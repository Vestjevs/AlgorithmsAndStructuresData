package Chapter1;

import edu.princeton.cs.algs4.StdRandom;

public class TestingPairs {

    public static double timeTrial(int N){
        int MAX = 1_000_000;
        long[] a = new long[N];

        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }

        Stopwatch timer = new Stopwatch();
        int count = EqualsPairs.count(a);
        return timer.elapsedTime();
    }

    public static double timeTrialFast(int N){
        int MAX = 1_000_000;
        long[] a = new long[N];

        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }

        Stopwatch timer = new Stopwatch();
        int count = EqualsPairs.countFast(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        for (int i = 1_000; true; i += i) {
            double time = timeTrial(i);
            double time1 = timeTrialFast(i);
            System.out.println(i + "  " + ", time - " + time + ", time1 - " + time1);
        }
    }
}
