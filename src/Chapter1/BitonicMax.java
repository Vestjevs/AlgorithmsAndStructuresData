package Chapter1;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class BitonicMax {

    public static int[] bitonic(int N) {
        int mid = StdRandom.uniform(N);
        int[] a = new int[N];
        for (int i = 1; i < mid; i++) {
            a[i] = a[i - 1] + 1 + StdRandom.uniform(9);
        }

        if (mid > 0) a[mid] = a[mid - 1] + StdRandom.uniform(10) - 5;
        for (int i = mid + 1; i < N; i++) {
            a[i] = a[i - 1] - 1 - StdRandom.uniform(9);
        }
        return a;
    }

    public static int max(int[] a, int lo, int hi) {
        if (hi == lo) return hi;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] < a[mid + 1]) return max(a, mid + 1, hi);
        if (a[mid] > a[mid]) return max(a, lo, mid);
        else return mid;
    }


    public static void main(String[] args) {

        System.out.println(Arrays.toString(bitonic(15)));
    }
}
