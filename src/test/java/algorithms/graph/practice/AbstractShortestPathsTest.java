package algorithms.graph.practice;

import algorithms.graph.EdgeWeightedDigraph;
import algorithms.graph.EdgeWeightedDigraphFactory;
import algorithms.graph.EdgeWeightedDigraphImpl;
import algorithms.graph.PathLogUtil;
import algorithms.graph.ShortestPaths;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/31.
 */
@Slf4j
public abstract class AbstractShortestPathsTest {

  protected abstract ShortestPaths getInstance();

  @Test
  public void iterateAll() {
    EdgeWeightedDigraphFactory factory = new EdgeWeightedDigraphFactory();
    EdgeWeightedDigraph graph = new EdgeWeightedDigraphImpl();
    factory.loadGraph(graph, "algorithms/graph/directed/shortestPathEDG.txt");

    ShortestPaths shortestPaths = getInstance();
    shortestPaths.init(graph, 0);

    int vCount = graph.verticesCount();
    for (int i = 1; i < vCount; i++) {
      log.info("from source to {} : {}", i, PathLogUtil.edgesPathToString(shortestPaths.pathTo(i)));
    }

  }


}
