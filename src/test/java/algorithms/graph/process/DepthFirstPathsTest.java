package algorithms.graph.process;

import algorithms.graph.Graph;
import algorithms.graph.UndirectedGraphFactory;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/1.
 */
public class DepthFirstPathsTest {

  private AbstractPathsFactory factory;

  @Before
  public void setUp() throws Exception {
    SimplePathsFactory factoryImpl = new SimplePathsFactory();
    factoryImpl.setSourceVertex(0);
    factoryImpl.setInstance(new DepthFirstPaths());
    UndirectedGraphFactory graphFactory = new UndirectedGraphFactory();
    graphFactory.setEdgesFilePath("algorithms/graph/tinyG.txt");
    factoryImpl.setGraphFactory(graphFactory);
    this.factory = factoryImpl;
  }

  @Test
  public void pathToTest() {
    Paths paths = factory.loadPaths();
    Graph graph = paths.getGraph();
    int source = paths.getSourceVertex();
    for (Integer vertex : graph.getVertices()) {
      if (paths.hasPathTo(vertex)) {
        StdOut.print(source + " to " + vertex + ": ");
        for (Integer v : paths.pathTo(vertex)) {
          if (v == source) {
            System.out.print(v);
          } else {
            System.out.print("-" + v);
          }
        }
        System.out.println();
      }
    }
  }
}