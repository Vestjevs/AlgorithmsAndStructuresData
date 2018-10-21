package Chapter2Sort;

public class Main {

    public static void sort(Comparable[] a) {

    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable e = a[i];
        a[i] = a[j];
        a[j] = e;
    }

    private static Comparable[] show(Comparable[] a) {
        return a;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }


    public static void main(String[] args) {

    }
}