package algorithms.graph.process;

import algorithms.graph.Digraph;

public interface TopologicalSort {

  void init(Digraph digraph);

  boolean isDAG();

  Iterable<Integer> sort();

}
