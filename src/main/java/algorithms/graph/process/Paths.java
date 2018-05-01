package algorithms.graph.process;

import algorithms.graph.Graph;

/**
 * Created by Chen Li on 2018/5/1.
 */
public interface Paths {

  void init(Graph graph, int sourceVertex);

  boolean hasPathTo(int v);

  Iterable<Integer> pathTo(int v);

  Graph getGraph();

  int getSourceVertex();
}
