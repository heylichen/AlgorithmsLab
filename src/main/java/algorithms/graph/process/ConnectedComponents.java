package algorithms.graph.process;

import algorithms.graph.Graph;

/**
 * Created by Chen Li on 2018/5/5.
 */
public interface ConnectedComponents<T extends Graph> {

  void init(T graph);

  /**
   * are v and w connected?
   */
  boolean connected(int v, int w);

  /**
   * number of connected components
   */
  int count();

  /**
   * component identifer for v
   * ( between 0 and count()-1 )
   */
  int getComponentId(int vertex);
}
