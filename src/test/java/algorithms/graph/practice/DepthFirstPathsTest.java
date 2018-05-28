package algorithms.graph.practice;

import algorithms.graph.Graph;
import algorithms.graph.GraphFactory;
import algorithms.graph.PathLogUtil;
import algorithms.graph.process.Paths;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/27.
 */
@Slf4j
public class DepthFirstPathsTest {

  @Test
  public void pathTest() {
    Graph graph = new GraphImpl();
    GraphFactory gf = new GraphFactory();
    gf.loadGraph(graph, "algorithms/graph/practice/directed/directedNoCycle14.txt");

    Paths path = new DepthFirstPaths();
    path.init(graph, 0);
    doTest(path, 12, true);
    doTest(path, 13, false);
  }

  @Test
  public void directedTest() {
    Graph graph = new DigraphImpl();
    GraphFactory gf = new GraphFactory();
    gf.loadGraph(graph, "algorithms/graph/practice/directed/directedNoCycle13.txt");

    Paths path = new DepthFirstPaths();
    path.init(graph, 0);
    doTest(path, 12, true);
    doTest(path, 10, true);
    doTest(path, 9, true);
    doTest(path, 8, true);
    //unreachable
    Paths pathFrom11 = new DepthFirstPaths();
    pathFrom11.init(graph, 11);
    doTest(pathFrom11, 12, false);
    doTest(pathFrom11, 6, true);
    doTest(pathFrom11, 3, false);
  }

  private void doTest(Paths path, int vertex, boolean expectedHasPath) {
    Assert.assertEquals(expectedHasPath, path.hasPathTo(vertex));
    if (expectedHasPath) {
      log.info("from {} path to {} is : {}", path.from(), vertex, PathLogUtil.pathToString(path.pathTo(vertex)));
    } else {
      log.info("from {} NO path to {}", path.from(), vertex);
    }
  }
}
