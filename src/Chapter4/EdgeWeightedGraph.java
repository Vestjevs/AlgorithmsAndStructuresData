package Chapter4;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.stream.IntStream;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private LinkedList<Edge>[] adj;          // list of adjacency

    EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Edge>[]) new LinkedList[V];

        IntStream.range(0, V).forEach(i -> adj[i] = new LinkedList<>());
    }

    EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("Number of edges must be nonnegative");
        } else {
            for (int i = 0; i < E; ++i) {
                int v = in.readInt();
                int w = in.readInt();
                this.validateVertex(v);
                this.validateVertex(w);
                double weight = in.readDouble();
                Edge e = new Edge(v, w, weight);
                this.addEdge(e);
            }

        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= this.V){
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (this.V - 1));
        }
    }

    public int E() {
        return E;
    }

    public int V() {
        return V;
    }

    public void addEdge(Edge edge) {
        int v = edge.either(), w = edge.other(v);
        this.validateVertex(v);
        this.validateVertex(w);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        LinkedList<Edge> list = new LinkedList<>();
        IntStream.range(0, V).forEachOrdered(v -> {
            for (Edge e :
                    adj[v]) {
                if (e.other(v) > v) list.add(e);
            }
        });
        return list;
    }
}
