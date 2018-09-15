package USearch;


import java.io.*;
import java.util.Scanner;

public class UF {
    private int[] id; // parent link
    private int[] sz; // size of component for root
    private int count;

    UF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if (i == j) return;
        //меньший корень должен указывать на большой
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }

        count--;
    }

    private int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt("1000000");
        UF uf = new UF(N);

        try {
            Scanner in = new Scanner(new File("file.txt"));
            BufferedWriter out = new BufferedWriter(new FileWriter("out.txt"));

            while (in.hasNextInt()) {
                int p = in.nextInt();
                int q = in.nextInt();

                if (uf.connected(p, q)) continue;
                uf.union(p, q);
                out.write(p + " " + q);
                out.newLine();
            }
            out.write(uf.count() + " components");
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
