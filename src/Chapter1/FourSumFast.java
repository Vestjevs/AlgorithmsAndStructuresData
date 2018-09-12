package Chapter1;

import java.util.Arrays;

public class FourSumFast {


    public static int count(int[] a) {
        Arrays.sort(a);
        int count = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                for (int k = j + 1; k < a.length; k++) {
                        if (Arrays.binarySearch(a, -a[i] - a[j] - a[k] ) > k)
                            count++;
                }
            }
        }

        return count;
    }

}
