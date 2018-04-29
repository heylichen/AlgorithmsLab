package algorithms.graph;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/29.
 */
public class UndirectedGraphImplTest {

  @Test
  public void createTest() {
    UndirectedGraphFactory undirectedGraphFactory = new UndirectedGraphFactory();
    Graph graph = undirectedGraphFactory.loadEdges("algorithms/graph/tinyG.txt");
    Assert.assertEquals(13, graph.verticesCount());
    Assert.assertEquals(13, graph.edgesCount());
    System.out.println(graph.toString());
  }
}