package algorithms.graph.process;

import java.util.Collection;

import algorithms.context.AbstractContextTest;
import algorithms.graph.Edge;
import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/13.
 */
@Slf4j
public abstract class AbstractMSTTest extends AbstractContextTest {

  protected abstract MST getMST();

  protected abstract EdgeWeightedGraph getGraph();

  @Test
  public void doTest() throws Exception {
    MST mst = getMST();
    EdgeWeightedGraph graph = getGraph();
    mst.init(graph);
    Collection<Edge> mstEdges = mst.edges();
    StringBuilder sb = new StringBuilder();
    for (Edge mstEdge : mstEdges) {
      sb.append(mstEdge).append("\n");
    }
    System.out.println(sb);
    Thread.sleep(10);
  }
}
