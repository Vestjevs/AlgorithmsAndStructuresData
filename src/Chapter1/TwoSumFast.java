package Chapter1;

import java.util.Arrays;

public class TwoSumFast {

    public static int count(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N/2; i++) {
            if (Arrays.binarySearch(a, -a[i]) > i) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arrays = new int[10_000_000];
//        for (int i = 0; i < 10000; i++) {
//            arrays[i] = StdRandom.uniform(-10_000_000, 10_000_000);
//        }

        Stopwatch timer = new Stopwatch();

        System.out.println(count(arrays) + " pairs " + " time ->" + timer.elapsedTime());

    }
}
