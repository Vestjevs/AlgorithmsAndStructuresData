package Chapter4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stopwatch;


public class PrimMST {

    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;


    PrimMST(EdgeWeightedGraph graph) {
        pq = new MinPQ<>();
        marked = new boolean[graph.V()];
        mst = new Queue<>();

        vist(graph, 0);
        while (!pq.isEmpty() || mst.size() < graph.V() - 1) {
            Edge edge = pq.delMin();
            int v = edge.either(), w = edge.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(edge);
            if (!marked[v]) vist(graph, v);
            if (!marked[w]) vist(graph, w);
        }
    }

    private void vist(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e :
                graph.adj(v)) {
            if (!marked[e.other(v)]) pq.insert(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double weight = .0;
        for (Edge e : edges()) weight += e.weight();
        return weight;
    }


    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();
        In in = new In("file.txt");
        EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
        PrimMST mst = new PrimMST(graph);
        for (Edge e : mst.edges()) {
            System.out.println(e.toString());
        }
        System.out.println(mst.weight() + '\n' + timer.elapsedTime());

        System.out.println("String".matches("\\[[a-z]]"));


    }
}
