package Chapter1;

/**
 * (N^2) logN
 */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class ThreeSumFast {

    public static int count(int[] a) {
        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (BinarySearch.rank(a, -a[i] - a[j]) > j) {
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        int[] a = new int[1_000_000];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(-1_000_000, 1_000_000);
        }
        Stopwatch timer = new Stopwatch();
        System.out.println(count(a) + "- > triples " + " time" + timer.elapsedTime());
    }
}
