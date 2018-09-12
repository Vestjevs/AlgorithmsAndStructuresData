package Chapter1;

import java.util.Random;

public class RandomSeq {
    public static void main(String[] args) {
        /** Вывод N случайных значений из диапазона (lo,, hi)*/
        int n = Integer.parseInt("5");
        int lo = Integer.parseInt("100");
        int hi = Integer.parseInt("200");
        int x = 0;
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            x = lo + random.nextInt(hi + 1 - lo); // 0.0 + random.nextInt(1.5)
        }
    }
}
