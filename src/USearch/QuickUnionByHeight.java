package USearch;

import java.util.Arrays;
import java.util.stream.IntStream;

public class QuickUnionByHeight {
    private int[] parent;
    private int[] height;
    private int count;

    QuickUnionByHeight(int n) {
        this.count = n;
        parent = new int[n];
        IntStream.range(0, n).forEach(i -> parent[i] = i);
        height = new int[n];
        Arrays.fill(this.height, 0);
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if (i == j) {
            return;
        }
        if (height[i] < height[j]) parent[i] = j;
        else if (height[i] > height[j]) parent[j] = i;
        else {
            parent[j] = i;
            height[i]++;
        }
        count--;
    }

    private void validate(int p) {
        int size = parent.length;
        if (p < 0 || p >= size)
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (size - 1));
    }
}
