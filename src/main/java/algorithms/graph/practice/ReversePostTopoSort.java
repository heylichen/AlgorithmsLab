package algorithms.graph.practice;

import java.util.Collections;

import algorithms.graph.Digraph;
import algorithms.graph.process.CycleDetection;
import algorithms.graph.process.TopologicalSort;

public class ReversePostTopoSort implements TopologicalSort {

  private boolean hasCycle;
  private Iterable<Integer> topoOrder;

  public void init(Digraph digraph) {
    CycleDetection cycleDetection = new DepthFirstDirectedCycleDetection();
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
