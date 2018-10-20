package USearch;

import java.util.Random;
import java.util.Scanner;

public class ErdosRenyi {


    public static int count(int n) {
        Random random = new Random();
        QuickUnionByHeight uf = new QuickUnionByHeight(n);
        int p;
        int q;
        int c = 0;
        for (int i = 0; i < n; i++) {
            p = random.nextInt(n);
            q = random.nextInt(n);
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                c++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
        System.out.println(count(sc.nextInt()));
    }
}
