package algorithms.graph.process;

import algorithms.graph.Graph;
import algorithms.graph.UndirectedGraphFactory;

/**
 * Created by Chen Li on 2018/4/30.
 */
public class DepthFirstSearchImplTest extends SearchTest {

  @Override
  protected Search createInstance() {
    return new DepthFirstSearch();
  }

  @Override
  protected Graph createGraph() {
    UndirectedGraphFactory impl = new UndirectedGraphFactory();
    impl.setEdgesFilePath("algorithms/graph/tinyG.txt");
    return impl.loadGraph();
  }

  @Override
  protected int getSource() {
    return 9;
  }
}
