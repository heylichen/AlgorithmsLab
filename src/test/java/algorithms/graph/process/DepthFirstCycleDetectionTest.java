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
public class DepthFirstCycleDetectionTest extends AbstractContextTest {

  @Getter
  @Resource(name = "depthFirstCycleDetection")
  private CycleDetection cycleDetection;
  @Getter
  @Resource(name = "tinyGraph")
  private Graph graph;
  @Getter
  @Resource(name = "noCycleTinyGraph")
  private Graph noCycleTinyGraph;

  @Test
  public void hasCycleTest() {
    cycleDetection.init(graph);
    boolean hasCycle = cycleDetection.hasCycle();
    Assert.assertEquals(true, hasCycle);
    log.info("has cycle :{}", hasCycle);
  }

  @Test
  public void noCycleTest() {
    cycleDetection.init(noCycleTinyGraph);
    boolean hasCycle = cycleDetection.hasCycle();
    Assert.assertEquals(false, hasCycle);
    log.info("has cycle :{}", hasCycle);
  }
}