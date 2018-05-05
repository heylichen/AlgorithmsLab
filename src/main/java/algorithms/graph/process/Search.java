package algorithms.graph.process;

import algorithms.graph.Graph;

/**
 * Created by Chen Li on 2018/4/30.
 */
public interface Search {

  void init(Graph graph, int sourceVertex);

  /**
   * is given vertex connected to source?
   */
  boolean marked(int vertex);

  /**
   * count of vertices connected to source
   */
  int connectedVerticesCount();

  Graph getGraph();
}
