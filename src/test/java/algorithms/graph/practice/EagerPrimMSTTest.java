package algorithms.graph.practice;

import javax.annotation.Resource;

import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import algorithms.graph.process.AbstractMSTTest;
import lombok.Getter;

@Getter
public class EagerPrimMSTTest extends AbstractMSTTest {

  @Resource(name = "tinyEDG")
  private EdgeWeightedGraph graph;

  @Override
  protected MST getMST() {
    return new EagerPrimMST();
  }
}

