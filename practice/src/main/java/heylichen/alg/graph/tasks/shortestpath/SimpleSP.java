package heylichen.alg.graph.tasks.shortestpath;

import heylichen.alg.graph.structure.directed.DirectedEdge;
import heylichen.alg.graph.structure.directed.EdgeWeightedDigraph;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class SimpleSP implements ShortestPath {
    private double distTo[];
    private DirectedEdge edgeTo[];
    private final EdgeWeightedDigraph graph;
    private final int sourceVertex;

    public SimpleSP(EdgeWeightedDigraph graph, int sourceVertex) {
        this.graph = graph;
        this.sourceVertex = sourceVertex;
    }

    public void init() {
        initDistTo();
        initEdgeTo();
        relaxAll();
    }

    private void relaxAll() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(sourceVertex);

        while (!queue.isEmpty()) {
            Integer vertex = queue.remove();
            for (DirectedEdge directedEdge : graph.getAdjacent(vertex)) {
                boolean relaxed = relax(directedEdge);
                if (relaxed) {
                    queue.add(directedEdge.getTo());
                }
            }
        }
    }


    protected boolean relax(DirectedEdge edge) {
        int from = edge.getFrom();
        int to = edge.getTo();
        if (distTo[to] > distTo[from] + edge.getWeight()) {
            distTo[to] = distTo[from] + edge.getWeight();
            edgeTo[to] = edge;
            return true;
        } else {
            return false;
        }
    }

    protected void relax(int vertex) {
        for (DirectedEdge directedEdge : graph.getAdjacent(vertex)) {
            relax(directedEdge);
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