package algorithms.graph.process;

import javax.annotation.Resource;

import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Chen Li on 2018/5/15.
 */
@Slf4j
@Getter
public class KruskalMSTTest extends AbstractMSTTest {

  @Resource(name = "kruskalMST")
  private MST MST;

  @Resource(name = "tinyEDG")
  private EdgeWeightedGraph graph;

}