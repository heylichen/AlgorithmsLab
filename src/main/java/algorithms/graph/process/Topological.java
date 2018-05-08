package algorithms.graph.process;

import algorithms.graph.Digraph;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/5/8.
 * Topological sort a digraph
 */

public class Topological {

  @Getter
  @Setter
  private CycleDetection directedCycleDetection;
  @Getter
  @Setter
  private Digraph digraph;
  //inner status
  private DepthFirstOrder depthFirstOrder;
  @Getter
  private Iterable<Integer> order;

  public void init() {
    directedCycleDetection.init(digraph);
    if (!directedCycleDetection.hasCycle()) {
      depthFirstOrder = new DepthFirstOrder(digraph);
      order = depthFirstOrder.getReversePost();
    }
  }

  public boolean isDAG() {
    return order != null;
  }
}
