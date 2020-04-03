package heylichen.alg.graph.structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UndirectedGraph implements Graph {
    private int vertexCount;
    private int edgeCount;
    private List<List<Integer>> adjacentList;

    public UndirectedGraph(int vertexCount) {
        initAdjacentList(vertexCount);
    }

    public UndirectedGraph(GraphSource<Edge> graphSource) {
        initAdjacentList(graphSource.getVertexCount());
        for (Edge edge : graphSource.getEdges()) {
            innerAddEdge(edge.getLeft(), edge.getRight());
        }
    }

    private void initAdjacentList(int vertexCount) {
        this.vertexCount = vertexCount;
        adjacentList = new ArrayList<>(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            adjacentList.add(new LinkedList<>());
        }
    }

    private void innerAddEdge(int v, int w) {
        this.edgeCount += 1;
        adjacentList.get(v).add(w);
        adjacentList.get(w).add(v);
    }

    @Override
    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public int getEdgeCount() {
        return edgeCount;
    }

    @Override
    public void addEdge(int v, int w) {
        innerAddEdge(v, w);
    }

    @Override
    public Iterable<Integer> getAdjacentVertices(int vertex) {
        return adjacentList.get(vertex);
    }
}
