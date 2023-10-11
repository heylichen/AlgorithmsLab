package algorithms.graph.practice;

import javax.annotation.Resource;

import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import algorithms.graph.process.AbstractMSTTest;
import lombok.Getter;

/**
 * Created by Chen Li on 2018/5/29.
 */
@Getter
public class KruskalMSTTest extends AbstractMSTTest {

  @Resource(name = "tinyEDG")
  private EdgeWeightedGraph graph;

  @Override
  protected MST getMST() {
    return new KruskalMST();
  }
}
