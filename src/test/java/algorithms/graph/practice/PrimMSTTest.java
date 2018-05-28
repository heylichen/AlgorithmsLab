package algorithms.graph.practice;

import javax.annotation.Resource;

import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import algorithms.graph.process.AbstractMSTTest;
import algorithms.graph.process.PrimMST;
import lombok.Getter;

/**
 * Created by Chen Li on 2018/5/28.
 */
@Getter
public class PrimMSTTest extends AbstractMSTTest {

  @Resource(name = "tinyEDG")
  private EdgeWeightedGraph graph;

  @Override
  protected MST getMST() {
    return new PrimMST();
  }
}

