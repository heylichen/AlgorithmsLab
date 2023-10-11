package algorithms.graph.process;

import algorithms.context.AbstractContextTest;
import algorithms.graph.Graph;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/5.
 */
@Slf4j
public abstract class SymbolGraphTest extends AbstractContextTest {

  protected abstract SymbolGraph getSymbolGraph();

  protected abstract SymbolGraph getMoviesSymbolGraph();

  @Test
  public void routesTest() {
    SymbolGraph symbolGraph = getSymbolGraph();
    printAdjacentVertices(symbolGraph, "ORD");
    Assert.assertEquals(6, symbolGraph.getGraph().adjacentVertices(symbolGraph.index("ORD")).size());
  }

  @Test
  public void moviesTest() {
    SymbolGraph symbolGraph = getMoviesSymbolGraph();
    printAdjacentVertices(symbolGraph,"Coogan, Keith");
  }

  private void printAdjacentVertices(SymbolGraph symbolGraph, String key) {
    Graph graph = symbolGraph.getGraph();
    int v = symbolGraph.index(key);
    if (v == -1) {
      return;
    }
    for (Integer ord : graph.adjacentVertices(v)) {
      log.info(" {}", symbolGraph.name(ord));
    }
  }
}