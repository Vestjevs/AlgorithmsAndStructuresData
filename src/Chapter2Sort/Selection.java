package Chapter2Sort;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Selection {


    public static void sort(Comparable[] a) {
        int min;
        for (int i = 0; i < a.length; i++) {
            min = i;
            for (int j = i + 1; j < a.length; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);
        }
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
