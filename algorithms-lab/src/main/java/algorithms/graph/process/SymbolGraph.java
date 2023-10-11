package algorithms.graph.process;

import algorithms.graph.Graph;

/**
 * Created by Chen Li on 2018/5/5.
 */
public interface SymbolGraph {

  /**
   * underlying Graph
   */
  Graph getGraph();

  /**
   * is key a vertex?
   */
  boolean contains(String key);

  /**
   * index associated with key
   */
  int index(String key);

  /**
   * key associated with index v
   */
  String name(int v);
}
