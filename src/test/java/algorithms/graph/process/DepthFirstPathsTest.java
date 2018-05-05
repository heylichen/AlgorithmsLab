package algorithms.graph.process;

import javax.annotation.Resource;

import algorithms.context.AbstractContextTest;
import algorithms.graph.Graph;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/1.
 */
public class DepthFirstPathsTest extends AbstractContextTest {

  @Resource(name="depthFirstPaths")
  private Paths paths;

  @Test
  public void doTest() {
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