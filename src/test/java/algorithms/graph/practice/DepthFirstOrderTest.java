package algorithms.graph.practice;

import algorithms.graph.Digraph;
import algorithms.graph.GraphFactory;
import algorithms.graph.PathLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class DepthFirstOrderTest {

  @Test
  public void preTest() {
    Digraph digraph = new DigraphImpl();
    GraphFactory gf = new GraphFactory();
    gf.loadGraph(digraph, "algorithms/graph/directed/orderDigraph.txt");

    DepthFirstOrder order = new DepthFirstOrder();
    order.init(digraph);

    log.info("pre order :{}", PathLogUtil.pathToString(order.pre()));
    log.info("post order :{}", PathLogUtil.pathToString(order.post()));
    log.info("reverse post order :{}", PathLogUtil.pathToString(order.reversePost()));
  }
}
