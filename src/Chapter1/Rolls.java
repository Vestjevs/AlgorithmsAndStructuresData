package Chapter1;

import edu.princeton.cs.algs4.Count;
import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdRandom;

public class Rolls<T extends Number> {

    static <T extends Comparable<T>, V extends T> boolean
    aB(T[] x, T[] y) {


        return false;
    }


    public static void main(String[] args) {
        int T = 2;
        int SIDES = 6;

        Counter[] rolls = new Counter[SIDES + 1];
        for (int i = 1; i <= SIDES; i++) {
            rolls[i] = new Counter("Выпадений: " + i);
        }
        for (int i = 0; i < T; i++) {
            int result = StdRandom.uniform(1, SIDES + 1);
            rolls[result].increment();
        }
        for (int i = 1; i <= SIDES; i++) {
            System.out.println(rolls[i]);
        }
    }
}
