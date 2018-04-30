package algorithms.graph.process;

import algorithms.graph.Graph;

/**
 * Created by Chen Li on 2018/4/30.
 */
public interface Search {

  void init(Graph graph, int vertex);

  boolean marked(int vertex);

  int connectedVerticesCount();
}
