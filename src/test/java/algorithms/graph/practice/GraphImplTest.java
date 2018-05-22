package algorithms.graph.practice;

import algorithms.graph.GraphFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/20.
 */
public class GraphImplTest {

  @Test
  public void name() {
    GraphImpl impl = new GraphImpl();
    GraphFactory gf = new GraphFactory();
    gf.loadGraph(impl, "algorithms/graph/process/tinyG.txt");
    Assert.assertEquals(13, impl.verticesCount());
    
    for (Integer integer : impl.adjacentVertices(0)) {
      System.out.println(integer);
    }
  }
}