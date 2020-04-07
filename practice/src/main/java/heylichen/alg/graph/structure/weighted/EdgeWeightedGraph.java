package heylichen.alg.graph.structure.weighted;

import heylichen.alg.graph.structure.Edge;
import heylichen.alg.graph.structure.GraphSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class EdgeWeightedGraph {
    private int vertexCount;
    private List<List<WeightedEdge>> adjacentList;
    private List<WeightedEdge> edges;

    public EdgeWeightedGraph(int vertexCount) {
        initAdjacentList(vertexCount);
    }

    public EdgeWeightedGraph(GraphSource<WeightedEdge> graphSource) {
        initAdjacentList(graphSource.getVertexCount());
        for (WeightedEdge edge : graphSource.getEdges()) {
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

    public void addEdge(WeightedEdge edge) {
        innerAddEdge(edge);
    }

    private void innerAddEdge(WeightedEdge edge) {
        int v = edge.either();
        int w = edge.theOther(v);

        adjacentList.get(v).add(edge);
        adjacentList.get(w).add(edge);
        edges.add(edge);
    }

    public Collection<WeightedEdge> getAdjacent(int v) {
        if (v < 0 || v >= vertexCount) {
            throw new IllegalArgumentException("index out of range");
        }
        return adjacentList.get(v);
    }

    public Collection<WeightedEdge> getEdges() {
        return edges;
    }
}
