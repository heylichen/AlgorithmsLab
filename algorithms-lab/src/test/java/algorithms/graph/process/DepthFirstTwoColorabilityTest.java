package algorithms.graph.process;

import javax.annotation.Resource;

import algorithms.context.AbstractContextTest;
import algorithms.graph.Graph;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/5.
 */
@Slf4j
public class DepthFirstTwoColorabilityTest extends AbstractContextTest {

  @Getter
  @Resource(name = "depthFirstTwoColorability")
  private TwoColorability twoColorability;
  @Getter
  @Resource(name = "tinyGraph")
  private Graph graph;
  @Getter
  @Resource(name = "tinyBipartiteGraph")
  private Graph tinyBipartiteGraph;

  @Test
  public void notBipartite() {
    twoColorability.init(graph);
    boolean bipartite = twoColorability.isBipartite();
    Assert.assertEquals(false, bipartite);
    log.info("is bipartite:{}", bipartite);
  }

  @Test
  public void bipartite() {
    twoColorability.init(tinyBipartiteGraph);
    boolean bipartite = twoColorability.isBipartite();
    Assert.assertEquals(true, bipartite);
    log.info("is bipartite:{}", bipartite);
  }
}