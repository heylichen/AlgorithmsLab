package algorithms.graph.process;

import algorithms.graph.Graph;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/30.
 */
public abstract class SearchTest {

  protected abstract Search createInstance();

  protected abstract Graph createGraph();

  protected abstract int getSource();

  @Test
  public void testMarked() {
    Search impl = createInstance();
    Graph graph = createGraph();
    impl.init(graph, getSource());

    for (Integer vertex : graph.getVertices()) {
      if (impl.marked(vertex)) {
        System.out.print(vertex + " ");
      }
    }
    System.out.println();
    if (impl.connectedVerticesCount() != graph.verticesCount()) {
      System.out.print(" NOT ");
    }
    System.out.print("connected\n");
  }
}