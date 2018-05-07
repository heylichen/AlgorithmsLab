package algorithms.graph.process;

import javax.annotation.Resource;

import algorithms.graph.Graph;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Chen Li on 2018/5/5.
 */
@Slf4j
public class DepthFirstGraphCycleDetectionTest extends AbstractCycleDetectionTest {

  @Getter
  @Resource(name = "depthFirstUndirectedCycleDetection")
  private CycleDetection cycleDetection;
  @Getter
  @Resource(name = "tinyGraph")
  private Graph cycleGraph;
  @Getter
  @Resource(name = "noCycleTinyGraph")
  private Graph acyclicGraph;

}