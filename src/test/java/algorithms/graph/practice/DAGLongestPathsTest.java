package algorithms.graph.practice;

import algorithms.graph.EdgeWeightedDigraph;
import algorithms.graph.EdgeWeightedDigraphFactory;
import algorithms.graph.EdgeWeightedDigraphImpl;
import algorithms.graph.PathLogUtil;
import algorithms.graph.practice.weighted.digraph.DAGLongestPaths;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/6/2.
 */
@Slf4j
public class DAGLongestPathsTest {

  @Test
  public void testWeightedDAG() {
    iterate("algorithms/graph/directed/shortestPathEDAG.txt");
  }

  private void iterate(String path) {
    EdgeWeightedDigraphFactory factory = new EdgeWeightedDigraphFactory();
    EdgeWeightedDigraph graph = new EdgeWeightedDigraphImpl();
    factory.loadGraph(graph, path);

    DAGLongestPaths shortestPaths = new DAGLongestPaths();
    shortestPaths.init(graph, 5);

    int vCount = graph.verticesCount();
    for (int i = 1; i < vCount; i++) {
      log.info("from source to {} : {}", i, PathLogUtil.edgesPathToString(shortestPaths.pathTo(i)));
    }
  }
}
