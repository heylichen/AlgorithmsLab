package algorithms.graph;

import java.util.Collection;

public interface EdgeWeightedGraph {

  void addEdge(Edge edge);

  Collection<Edge> adjacent(int vertex);

  int verticesCount();

  int edgesCount();

  Collection<Integer> getVertices();

  Collection<Edge> getEdges();
}
