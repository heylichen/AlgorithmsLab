package algorithms.graph.process;

import algorithms.context.AbstractContextTest;
import algorithms.graph.Graph;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/5.
 */
public abstract class PathsTest extends AbstractContextTest {

  abstract Paths getPaths();

  abstract Graph getGraph();

  abstract int getSourceVertex();

  @Test
  public void doTest() {
    Paths paths = getPaths();
    Graph graph = getGraph();
    int source = getSourceVertex();
    paths.init(graph, source);

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
