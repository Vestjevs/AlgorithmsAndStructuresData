package Chapter1;

import edu.princeton.cs.algs4.StdRandom;

import java.io.NotActiveException;
import java.util.Arrays;

public class PresentsArgument {
    private static LinkedList<Integer> list = new LinkedList<>();

    public static LinkedList<Integer> findPresents(int[] a, int[] b) throws NotActiveException {
        if (a.length != b.length) throw new NotActiveException();

        for (int i = 0; i < a.length; i++) {
            if (BinarySearch.rank(b, a[i]) != -1) {
                list.addEnd(a[i]);
            }
        }

        return list;
    }


    public static void main(String[] args) throws NotActiveException {
        int[] a = new int[5_000_000];
        int[] b = new int[5_000_000];

        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(-1_000_000, 1_000_000);
            b[i] = StdRandom.uniform(-1_000_000, 1_000_000);
        }
        Arrays.sort(a);
        Arrays.sort(b);
        Stopwatch timer = new Stopwatch();

        System.out.println(findPresents(a, b).size() + " - " + timer.elapsedTime());
    }

}
