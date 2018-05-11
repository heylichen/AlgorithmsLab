package algorithms.graph;

import java.util.Collection;

/**
 * Minimum Spanning Tree API
 */
public interface MST {

  void init(EdgeWeightedGraph graph);

  /**
   * all of the MST edges
   */
  Collection<Edge> edges();

  /**
   * weight of MST
   */
  Double weight();
}
