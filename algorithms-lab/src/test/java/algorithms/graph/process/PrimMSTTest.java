package algorithms.graph.process;

import javax.annotation.Resource;

import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import lombok.Getter;

@Getter
public class PrimMSTTest extends AbstractMSTTest {

  @Resource(name = "primMST")
  private MST MST;

  @Resource(name = "tinyEDG")
  private EdgeWeightedGraph graph;

}
