package algorithms.graph.practice;

import algorithms.graph.ShortestPaths;

/**
 * Created by Chen Li on 2018/5/31.
 */
public class DijkstraShortestPathsTest extends AbstractShortestPathsTest {

  @Override
  protected ShortestPaths getInstance() {
    return new DijkstraShortestPaths();
  }
}
