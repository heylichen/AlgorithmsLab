package algorithms.graph.process;

import algorithms.graph.Graph;
import algorithms.graph.UndirectedGraphFactory;
import lombok.Data;

/**
 * Created by Chen Li on 2018/5/1.
 */
@Data
public class SimplePathsFactory extends AbstractPathsFactory {

  private Paths instance;
  private UndirectedGraphFactory graphFactory;
  private int sourceVertex;


  @Override
  protected Paths createPaths() {
    return instance;
  }

  @Override
  protected Graph createGraph() {
    return graphFactory.loadGraph();
  }
}
