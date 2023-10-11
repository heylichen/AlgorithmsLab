package algorithms.graph.practice;

import algorithms.graph.process.TopologicalSort;


public class QueueTopoSortTest  extends AbstractTopoSortTest{

  @Override
  protected TopologicalSort getInstance() {
    return new QueueTopoSort();
  }
}
