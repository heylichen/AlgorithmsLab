package algorithms.graph.process;

import javax.annotation.Resource;

import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Chen Li on 2018/5/11.
 */
@Slf4j
@Getter
public class LazyPrimMSTTest extends AbstractMSTTest {

  @Resource(name = "lazyPrimMST")
  private MST MST;

  @Resource(name = "tinyEDG")
  private EdgeWeightedGraph graph;

}