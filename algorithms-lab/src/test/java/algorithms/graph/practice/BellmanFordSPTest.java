package algorithms.graph.practice;

import algorithms.graph.EdgeWeightedDigraph;
import algorithms.graph.EdgeWeightedDigraphFactory;
import algorithms.graph.EdgeWeightedDigraphImpl;
import algorithms.graph.PathLogUtil;
import algorithms.graph.practice.weighted.digraph.BellmanFordSP;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/6/4.
 */
@Slf4j
public class BellmanFordSPTest {

  @Test
  public void noNegativeCycle() {
    iterate("algorithms/graph/directed/tinyEWDNegativeWeights.txt");
  }

  @Test
  public void negativeCycle() {
    iterate("algorithms/graph/directed/tinyEWDNegativeCycle.txt");
  }

  private void iterate(String path) {
    EdgeWeightedDigraphFactory factory = new EdgeWeightedDigraphFactory();
    EdgeWeightedDigraph graph = new EdgeWeightedDigraphImpl();
    factory.loadGraph(graph, path);

    BellmanFordSP shortestPaths = new BellmanFordSP();
    shortestPaths.init(graph, 0);

    if (shortestPaths.hasNegativeCycle()) {
      log.info("has negative cycle, no shortest path. negative cycle is: {}", PathLogUtil.pathToString(shortestPaths.cycle()));
    } else {
      int vCount = graph.verticesCount();
      for (int i = 1; i < vCount; i++) {
        log.info("from source to {} : {}", i, PathLogUtil.edgesPathToString(shortestPaths.pathTo(i)));
      }
    }
  }

}
