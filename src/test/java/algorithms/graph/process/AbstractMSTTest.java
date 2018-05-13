package algorithms.graph.process;

import java.util.Collection;

import algorithms.context.AbstractContextTest;
import algorithms.graph.Edge;
import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/13.
 */
public abstract class AbstractMSTTest extends AbstractContextTest {

  protected abstract MST getMST();

  protected abstract EdgeWeightedGraph getGraph();

  @Test
  public void doTest() {
    MST mst = getMST();
    EdgeWeightedGraph graph = getGraph();
    mst.init(graph);
    Collection<Edge> mstEdges = mst.edges();
    for (Edge mstEdge : mstEdges) {
      System.out.println(mstEdge);
    }
  }
}
