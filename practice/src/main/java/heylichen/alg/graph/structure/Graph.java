package heylichen.alg.graph.structure;

public interface Graph {

    int getVertexCount();

    int getEdgeCount();

    void addEdge(int v, int w);

    Iterable<Integer> getAdjacentVertices(int vertex);
}
