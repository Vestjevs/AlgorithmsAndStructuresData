package USearch;


import java.util.Arrays;

public class WeightedQuickUnionUF {
    private int[] id; // parent link
    private int[] sz; // size of component for root
    private int count;
    private int total;
    private int cost;

    WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
        cost = N;
        total = 2 * N;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if (i == j) return;
        //меньший корень должен указывать на большой
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
            cost += 2;
            total += 3;
        } else {
            id[j] = i;
            sz[i] += sz[j];
            cost += 2;
            total += 3;
        }
        cost += 2;
        total += 2;

        count--;
    }

    private int find(int p) {
        validate(p);
        int root = p;
        while (root != id[root]) {
            root = id[root];
            total += 2;
        }
        while (p != root) {
            int newp = id[p];
            id[p] = root;
            root = newp;
        }
        total++;
        return root;
    }

    private void validate(int p) {
        int size = id.length;
        if (p < 0 || p >= size)
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (size - 1));
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    public int getTotal() {
        return total;
    }

    public int getCost() {
        return cost;
    }

    public int[] getId() {
        return id;
    }

    public int[] getSz() {
        return sz;
    }

    public static void main(String[] args) {
//        int N = Integer.parseInt("625");
//        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
//
//
//        try {
//            Scanner in = new Scanner(new File("file.txt"));
//            BufferedWriter out = new BufferedWriter(new FileWriter("out.txt"));
//
//            while (in.hasNextInt()) {
//                int p = in.nextInt();
//                int q = in.nextInt();
//
//                if (uf.connected(p, q)) continue;
//                uf.union(p, q);
//                out.write(p + " " + q);
//                out.newLine();
//            }
//            out.write(uf.count() + " components");
//            out.flush();
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(8);

        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(0, 2);
        uf.union(4, 6);
        uf.union(0, 4);
        System.out.println(Arrays.toString(uf.id));
        System.out.println(Arrays.toString(uf.sz));
    }
}
