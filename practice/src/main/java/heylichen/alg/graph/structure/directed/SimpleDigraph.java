package heylichen.alg.graph.structure.directed;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleDigraph implements Digraph {
    private int vertexCount;
    private int edgeCount;
    private List<List<Integer>> adjacentList;

    public SimpleDigraph(int vertexCount) {
        initAdjacentList(vertexCount);
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
    }

    @Override
    public Digraph reverse() {
        SimpleDigraph reverseDigraph = new SimpleDigraph(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            List<Integer> adList = adjacentList.get(i);
            for (Integer integer : adList) {
                reverseDigraph.addEdge(integer, i);
            }
        }
        return reverseDigraph;
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
