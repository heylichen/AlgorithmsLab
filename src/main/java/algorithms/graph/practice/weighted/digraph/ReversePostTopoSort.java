package algorithms.graph.practice.weighted.digraph;

import java.util.Collections;

import algorithms.graph.EdgeWeightedDigraph;

public class ReversePostTopoSort {

  private boolean hasCycle;
  private Iterable<Integer> topoOrder;

  public void init(EdgeWeightedDigraph digraph) {
    DepthFirstWeightedDigraphCycleDetection cycleDetection = new DepthFirstWeightedDigraphCycleDetection();
    cycleDetection.init(digraph);
    hasCycle = cycleDetection.hasCycle();
    if (hasCycle) {
      topoOrder = Collections.emptyList();
      return;
    }

    DepthFirstOrder order = new DepthFirstOrder();
    order.init(digraph);
    topoOrder = order.reversePost();
  }

  public boolean isDAG() {
    return !hasCycle;
  }

  public Iterable<Integer> sort() {
    return topoOrder;
  }
}
