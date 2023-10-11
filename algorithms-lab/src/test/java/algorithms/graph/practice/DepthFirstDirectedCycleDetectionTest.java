package algorithms.graph.practice;

import algorithms.graph.process.CycleDetection;

public class DepthFirstDirectedCycleDetectionTest extends DirectedCycleDetectionTest {

  @Override
  protected CycleDetection getInstance() {
    return new DepthFirstDirectedCycleDetection();
  }
}
