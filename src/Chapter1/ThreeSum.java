package Chapter1;

import edu.princeton.cs.algs4.StdRandom;

public class ThreeSum {

    public static int count1(int[] a) {
        int size = a.length;
        int count = 0;

        for (int i = 0; i < size; i++)
            for (int j = i + 1; j < size; j++)
                for (int k = j + 1; k < size; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        count++;
        return count;
    }

    public static int count2(int[] a) {
        int size = a.length;
        int count = 0;

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                for (int k = 0; k < size; k++)
                    if (i < j && j < k)
                        if (a[i] + a[j] + a[k] == 0)
                            count++;
        return count;
    }

    public static void main(String[] args) {
        int[] a = new int[2000];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(-1_000_000, 1_000_000);
        }
        Stopwatch timer = new Stopwatch();
        int count = ThreeSum.count1(a);
        double time = timer.elapsedTime();
        System.out.println(count + " triples, " + time + " seconds");

    }
}
