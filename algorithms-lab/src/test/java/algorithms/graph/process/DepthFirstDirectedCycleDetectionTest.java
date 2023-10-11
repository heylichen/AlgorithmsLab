package algorithms.graph.process;

import javax.annotation.Resource;

import algorithms.graph.Graph;
import lombok.Getter;

public class DepthFirstDirectedCycleDetectionTest extends AbstractCycleDetectionTest {

  @Getter
  @Resource(name = "depthFirstDirectedCycleDetection")
  private CycleDetection cycleDetection;
  @Getter
  @Resource(name = "tinyDirectedCyclicGraph")
  private Graph cycleGraph;
  @Getter
  @Resource(name = "tinyDirectedAcyclicGraph")
  private Graph acyclicGraph;

}