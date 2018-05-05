package algorithms.graph.process;

import algorithms.context.AbstractContextTest;
import algorithms.graph.Graph;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/30.
 */
public abstract class SearchTest extends AbstractContextTest {

  protected abstract Search getSearch();

  @Test
  public void testMarked() {
    Search impl = getSearch();
    Graph graph = impl.getGraph();

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