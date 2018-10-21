package Chapter2Sort;

import java.util.Random;
import java.util.Scanner;

public class SortCompare {

    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();

        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
//        if (alg.equals("Insertion")) Insertion.sort(a);
//        if (alg.equals("Insertion")) Insertion.sort(a);
//        if (alg.equals("Insertion")) Insertion.sort(a);
//        if (alg.equals("Insertion")) Insertion.sort(a);
        return timer.elapsedTime();

    }

    public static double timeRandomInput(String alg, int n, int t) {
        Random random = new Random();
        double total = .0;
        Double[] arr = new Double[n];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++)
                arr[j] = random.nextDouble();
            total += time(alg, arr);
        }
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String alg1 = sc.next();
        String alg2 = sc.next();
        int N = sc.nextInt();
        int T = sc.nextInt();
        double t1 = timeRandomInput(alg1, N, T); // время для alg1
        double t2 = timeRandomInput(alg2, N, T); // время для alg2

        System.out.format("Для %d случайных Doubles\n %s в", N, alg1);
        System.out.format(" %.2f раз быстрее, чем %s\n ", t2 / t1, alg2);

    }
}
