package algorithms.graph.practice;

import algorithms.graph.Graph;
import algorithms.graph.GraphFactory;
import algorithms.graph.process.ConnectedComponents;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/27.
 */
public class DepthFirstConnectedComponentsTest {

  @Test
  public void undirectedTest() {
    Graph graph = new GraphImpl();
    GraphFactory gf = new GraphFactory();
    gf.loadGraph(graph, "algorithms/graph/practice/directed/directedNoCycle14.txt");

    ConnectedComponents connectedComponents = new DepthFirstConnectedComponents();
    connectedComponents.init(graph);

    Assert.assertEquals(true, connectedComponents.connected(0, 10));
    Assert.assertEquals(false, connectedComponents.connected(0, 13));
  }

  @Test
  public void directedTest() {
    Graph graph = new DigraphImpl();
    GraphFactory gf = new GraphFactory();
    gf.loadGraph(graph, "algorithms/graph/practice/directed/directedNoCycle14.txt");

    ConnectedComponents connectedComponents = new DepthFirstConnectedComponents();
    connectedComponents.init(graph);

    Assert.assertEquals(true, connectedComponents.connected(0, 10));
    Assert.assertEquals(false, connectedComponents.connected(0, 13));
  }

}
