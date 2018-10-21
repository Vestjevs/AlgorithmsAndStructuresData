package Chapter2Sort;

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
}
