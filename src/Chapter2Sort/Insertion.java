package Chapter2Sort;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Insertion {

    public static void sort(Comparable[] a) {
        //по возрастанию
        for (int i = 1; i < a.length; i++) {
            // вставляем a[i] среди a[i-1], a[i-2], ...
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void sortOpt(Comparable[] a) {
        int exc = 0;
        for (int i = a.length - 1; i > 0; i--) {
            if (less(a[i], a[i - 1])) {
                exch(a, i, i - 1);
                exc++;
            }
        }
        if (exc == 0)
            return;
        for (int i = 2; i < a.length; i++) {
            Comparable v = a[i];
            int j = i;
            while (less(v, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = v;
        }
        assert isSorted(a);
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable e = a[i];
        a[i] = a[j];
        a[j] = e;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static double time(Comparable[] a){
        Stopwatch timer = new Stopwatch();
        sort(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Double[] arr;
        Random random = new Random();
        arr = IntStream.range(0, sc.nextInt()).mapToObj(i -> random.nextDouble()).toArray(Double[]::new);
        System.out.printf("Time of sorting:  %.5f", time(arr));

    }

}
