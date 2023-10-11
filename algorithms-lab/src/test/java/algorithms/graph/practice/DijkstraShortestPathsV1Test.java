package algorithms.graph.practice;

import algorithms.graph.ShortestPaths;

public class DijkstraShortestPathsV1Test extends AbstractShortestPathsTest {

  @Override
  protected ShortestPaths getInstance() {
    return new DijkstraShortestPathsV1();
  }
}
