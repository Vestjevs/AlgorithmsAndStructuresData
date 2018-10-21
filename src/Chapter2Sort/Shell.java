package Chapter2Sort;

import java.util.Random;

public class Shell {

    public static double sort(Comparable[] a) {
        int h = 1;
        int k = 0;
        StringBuilder str = new StringBuilder();
        while (h < a.length / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                // Вставка a[i] между a[i-h], a[i - 2*h]...
                k++;
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {

                    exch(a, j, j - h);
                  //  str.append(j).append("-").append(j - h).append(" Количество сравнений ").append(k/a.length).append(" ;\n");
                }
            }
            h /= 3;
        }
        return k / a.length;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable e = a[i];
        a[i] = a[j];
        a[j] = e;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static double time(Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        sort(a);
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        Double[] arr;
        for (int i = 100; true; i *= 10) {
            arr = new Double[i];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = random.nextDouble();
            }
            System.out.printf("Time of sorting: %.3f , Quantity of comparisons = %f\n", time(arr), sort(arr));
        }




    }
}
