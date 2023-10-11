package algorithms.graph.process;

import algorithms.graph.Graph;

/**
 * Created by Chen Li on 2018/5/5.
 * Support this query: Is a given graph acylic ?
 */
public interface CycleDetection {

  void init(Graph graph);

  boolean hasCycle();

  Iterable<Integer> cycle();
}
