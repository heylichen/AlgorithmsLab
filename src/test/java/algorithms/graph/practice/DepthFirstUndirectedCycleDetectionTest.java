package algorithms.graph.practice;

import algorithms.graph.process.CycleDetection;

/**
 * Created by Chen Li on 2018/5/22.
 */
public class DepthFirstUndirectedCycleDetectionTest extends UndirectedCycleDetectionTest{

  @Override
  protected CycleDetection getInstance() {
    return new DepthFirstUndirectedCycleDetection();
  }
}
