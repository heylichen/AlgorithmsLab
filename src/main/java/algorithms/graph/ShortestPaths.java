package algorithms.graph;

import java.util.Collection;

public interface ShortestPaths {

  void init(EdgeWeightedDigraph graph, Integer source);

  Double distanceTo(Integer vertex);

  boolean hasPathTo(Integer vertex);

  Collection<DirectedEdge> pathTo(Integer vertex);
}
