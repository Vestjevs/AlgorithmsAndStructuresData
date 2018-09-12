package Chapter1;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

public class Flips {
    Random random = new Random();

    public void flips(String k) {
        int T = Integer.parseInt(k);
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");

        for (int i = 0; i < T; i++) {
            if (StdRandom.bernoulli(0.5))
                heads.increment();
            else tails.increment();
        }
        if (heads.tally() == tails.tally())
            System.out.println("Draw");
        else System.out.println(max(heads, tails) + " - Winner");
    }

    private static Counter max(Counter x, Counter y) {
        if (x.tally() > y.tally()) return x;
        else return y;
    }

    public static void main(String[] args) {
        Flips flips = new Flips();
        flips.flips("10000000");

    }
}
