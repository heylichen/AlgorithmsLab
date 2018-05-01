package algorithms.graph.process;

import algorithms.graph.Graph;

/**
 * Created by Chen Li on 2018/5/1.
 */
public abstract class AbstractPathsFactory {

  public Paths loadPaths() {
    Paths paths = createPaths();
    Graph graph = createGraph();
    int sourceV = getSourceVertex();
    paths.init(graph, sourceV);
    return paths;
  }

  protected abstract Paths createPaths();

  protected abstract Graph createGraph();

  protected abstract int getSourceVertex();
}
