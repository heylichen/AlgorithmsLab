package heylichen.alg.graph.structure.directed;

import heylichen.alg.graph.structure.GraphSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class EdgeWeightedDigraph {
    private int vertexCount;
    private List<List<DirectedEdge>> adjacentList;
    private List<DirectedEdge> edges;

    public EdgeWeightedDigraph(int vertexCount) {
        initAdjacentList(vertexCount);
    }

    public EdgeWeightedDigraph(GraphSource<DirectedEdge> graphSource) {
        initAdjacentList(graphSource.getVertexCount());
        for (DirectedEdge edge : graphSource.getEdges()) {
            innerAddEdge(edge);
        }
    }

    private void initAdjacentList(int vertexCount) {
        this.vertexCount = vertexCount;
        edges = new ArrayList<>();
        adjacentList = new ArrayList<>(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            adjacentList.add(new LinkedList<>());
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public void addEdge(DirectedEdge edge) {
        innerAddEdge(edge);
    }

    private void innerAddEdge(DirectedEdge edge) {
        adjacentList.get(edge.getFrom()).add(edge);
        edges.add(edge);
    }

    public Collection<DirectedEdge> getAdjacent(int v) {
        if (v < 0 || v >= vertexCount) {
            throw new IllegalArgumentException("index out of range");
        }
        return adjacentList.get(v);
    }

    public Collection<DirectedEdge> getEdges() {
        return edges;
    }
}
