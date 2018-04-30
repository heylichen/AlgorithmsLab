package algorithms.graph.process;

import algorithms.graph.Graph;
import algorithms.graph.UndirectedGraphFactory;

/**
 * Created by Chen Li on 2018/4/30.
 */
public class DepthFirstSearchImplTest extends SearchTest {

  @Override
  protected Search createInstance() {
    return new DepthFirstSearchImpl();
  }

  @Override
  protected Graph createGraph() {
    return new UndirectedGraphFactory().loadEdges("algorithms/graph/tinyG.txt");
  }

  @Override
  protected int getSource() {
    return 9;
  }
}
