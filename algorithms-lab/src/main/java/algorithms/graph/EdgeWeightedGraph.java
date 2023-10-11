package algorithms.graph;

import java.util.Collection;

public interface EdgeWeightedGraph {

  void init(int verticesCount);

  void addEdge(Edge edge);

  Collection<Edge> adjacent(int vertex);

  int verticesCount();

  int edgesCount();

  Collection<Integer> getVertices();

  Collection<Edge> getEdges();
}
