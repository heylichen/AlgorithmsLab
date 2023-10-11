package algorithms.graph.process;

import algorithms.context.AbstractContextTest;
import algorithms.graph.Graph;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/30.
 */
public abstract class SearchTest extends AbstractContextTest {

  protected abstract Search getSearch();

  protected abstract Graph getGraph();

  protected abstract int getSourceVertex();

  @Test

  public void testMarked() {
    Search search = getSearch();
    Graph graph = getGraph();
    search.init(graph,getSourceVertex());

    for (Integer vertex : graph.getVertices()) {
      if (search.marked(vertex)) {
        System.out.print(vertex + " ");
      }
    }
    System.out.println();
    if (search.connectedVerticesCount() != graph.verticesCount()) {
      System.out.print(" NOT ");
    }
    System.out.print("connected\n");
  }
}