package Chapter4;

import edu.princeton.cs.algs4.*;

public class KruskalMST {

    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph graph) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        UF uf = new UF(graph.V());

        for (Edge e : graph.edges()) {
            pq.insert(e);
        }

        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double weight = .0;
        for (Edge e :
                edges())
            weight += e.weight();
        return weight;
    }

    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();
        In in = new In("file.txt");
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(graph);
        for (Edge e : mst.edges()) {
            System.out.println(e.toString());
        }
        System.out.println(mst.weight() + '\n' + timer.elapsedTime());
    }
}
