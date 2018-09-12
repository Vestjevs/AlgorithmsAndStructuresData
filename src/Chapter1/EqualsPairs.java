package Chapter1;

import java.util.Arrays;

public class EqualsPairs {

    public static int count(long[] a) {
        int count = 0;

        for (long anA : a) {
            for (long anA1 : a) {
                if (anA == anA1) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int countFast(long[] a) {
        int count = 0;
        Arrays.sort(a);

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] == a[j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
