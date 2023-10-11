package algorithms.graph;

import java.util.Collection;

public interface EdgeWeightedDigraph {

  void init(int verticesCount);

  void addEdge(DirectedEdge edge);

  Collection<DirectedEdge> adjacent(int vertex);

  int verticesCount();

  int edgesCount();

  Collection<Integer> getVertices();

  Collection<DirectedEdge> getEdges();
}
