package heylichen.alg.graph.tasks.shortestpath;

import heylichen.alg.graph.structure.directed.DirectedEdge;
import heylichen.alg.graph.structure.directed.EdgeWeightedDigraph;
import heylichen.sort.pq.IndexedPriorityQueue;
import heylichen.sort.pq.MinDoublePriorityComparator;
import heylichen.utils.DoubleUtils;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class IndexedPqSP implements ShortestPath {
    private double distTo[];
    private DirectedEdge edgeTo[];
    private final EdgeWeightedDigraph graph;
    private final int sourceVertex;

    public IndexedPqSP(EdgeWeightedDigraph graph, int sourceVertex) {
        this.graph = graph;
        this.sourceVertex = sourceVertex;
    }

    public void init() {
        initDistTo();
        initEdgeTo();
        relaxAll();
    }

    private void relaxAll() {
        IndexedPriorityQueue<Double> indexedPq = new IndexedPriorityQueue<>(new MinDoublePriorityComparator(), graph.getVertexCount());
        indexedPq.insert(sourceVertex, 0d);

        int count=0;
        while (!indexedPq.isEmpty()) {
            count++;
            Integer vertex = indexedPq.delMin();
            for (DirectedEdge directedEdge : graph.getAdjacent(vertex)) {
                boolean relaxed = relax(directedEdge);
                if (!relaxed) {
                    continue;
                }
                int w = directedEdge.getTo();
                double dist = distTo[w];
                if (indexedPq.contains(w)) {
                    indexedPq.change(w, dist);
                } else {
                    indexedPq.insert(w, dist);
                }
            }
        }
        System.out.println("total times " + count);
    }

    protected boolean relax(DirectedEdge edge) {
        int from = edge.getFrom();
        int to = edge.getTo();
        if (distTo[to] > distTo[from] + edge.getWeight()) {
            distTo[to] =  DoubleUtils.add(distTo[from], edge.getWeight());
            edgeTo[to] = edge;
            return true;
        } else {
            return false;
        }
    }

    protected void initDistTo() {
        distTo = new double[graph.getVertexCount()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[sourceVertex] = 0d;
    }

    protected void initEdgeTo() {
        edgeTo = new DirectedEdge[graph.getVertexCount()];
    }

    @Override
    public double distTo(int v) {
        return distTo[v];
    }

    @Override
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    @Override
    public Iterable<DirectedEdge> pathTo(int v) {
        Deque<DirectedEdge> stack = new LinkedList<>();
        if (!hasPathTo(v)) {
            return Collections.emptyList();
        }
        DirectedEdge edge = edgeTo[v];
        while (edge != null) {
            stack.push(edge);
            edge = edgeTo[edge.getFrom()];
        }
        return stack;
    }
}
