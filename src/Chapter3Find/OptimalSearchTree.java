package Chapter3Find;

public class OptimalSearchTree {

    private Node root;
    private int[] keys;
    private int[] access_count;
    private int[] miss_count;
    private int[][] subtree_weights;
    private int[][] subtree_roots;
    private int[][] subtree_measure;


    class Node {
        private Node left;
        private Node right;
        private int key_index;

        public Node(int key_index) {
            this.key_index = key_index;
        }
    }


    public OptimalSearchTree(int[] keys, int[] access_count, int[] miss_count) {
        this.keys = keys;
        this.access_count = access_count;
        this.miss_count = miss_count;
        prepare();
        root = buildTree(0, keys.length);
    }

    private Node buildTree(int i, int j) {
        if (i >= j) return null;
        int k = subtree_roots[i][j];
        Node n = new Node(k - 1);
        n.left = buildTree(i, k - 1);
        n.right = buildTree(k, j);
        return n;
    }

    private void prepare() {
        int len = keys.length + 1;
        subtree_weights = new int[len][len];
        for (int i = 0; i < len; i++) {
            subtree_weights[i][i] = miss_count[i];
            for (int j = i + 1; j < len; j++) {
                subtree_weights[i][j] = subtree_weights[i][j - 1] + access_count[j] + miss_count[j];
            }
        }

        subtree_roots = new int[len][ len];
        subtree_measure = new int[len][ len];
        for (int i = 0; i < len; i++) {
            subtree_measure[i][i] = subtree_weights[i][ i];
            subtree_roots[i][i] = i;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i + j < len; i++) {
                {
                    int min_index = i + 1;
                    for (int k = i + 1; k <= i + j; k++) {
                        if (subtree_measure[i][k - 1] + subtree_measure[k][i + j] < subtree_measure[i][min_index - 1]
                                + subtree_measure[min_index][i + j]) {
                            min_index = k;
                        }
                    }
                    subtree_roots[i][i + j] = min_index;
                    subtree_measure[i][i + j] = subtree_weights[i][i + j] + subtree_measure[i][min_index - 1] + subtree_measure[min_index][i + j];
                }
            }
        }
    }
}
