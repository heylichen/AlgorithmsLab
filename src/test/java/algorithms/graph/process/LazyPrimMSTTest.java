package algorithms.graph.process;

import java.util.Collection;

import javax.annotation.Resource;

import algorithms.context.AbstractContextTest;
import algorithms.graph.Edge;
import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/11.
 */
@Slf4j
public class LazyPrimMSTTest extends AbstractContextTest {

  @Resource(name = "lazyPrimMST")
  private MST mst;

  @Resource(name = "tinyEDG")
  private EdgeWeightedGraph graph;


  @Test
  public void doTest() {
    mst.init(graph);
    Collection<Edge> mstEdges = mst.edges();
    for (Edge mstEdge : mstEdges) {
      System.out.println(mstEdge);
    }
  }
}