package algorithms.graph.practice;

import algorithms.graph.process.TopologicalSort;


public class ReversePostTopoSortTest extends AbstractTopoSortTest{

  @Override
  protected TopologicalSort getInstance() {
    return new ReversePostTopoSort();
  }
}
