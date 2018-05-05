package algorithms.graph.process;

import algorithms.graph.Graph;

/**
 * Created by Chen Li on 2018/5/1.
 */
public interface Paths {

  void init(Graph graph, int sourceVertex);

  /**
   * if existed a path from source to the given vertex
   */
  boolean hasPathTo(int v);

  /**
   * find the path from source to the given vertex
   */
  Iterable<Integer> pathTo(int v);

  Graph getGraph();

  int getSourceVertex();
}
