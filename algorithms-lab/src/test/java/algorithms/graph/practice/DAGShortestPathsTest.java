package algorithms.graph.practice;

import algorithms.graph.ShortestPaths;
import algorithms.graph.practice.weighted.digraph.DAGShortestPaths;

/**
 * Created by Chen Li on 2018/6/1.
 */
public class DAGShortestPathsTest  extends AbstractShortestPathsTest {

  @Override
  protected ShortestPaths getInstance() {
    return new DAGShortestPaths();
  }
}
