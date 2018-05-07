package algorithms.graph.process;

import java.util.ArrayList;
import java.util.List;

import algorithms.context.AbstractContextTest;
import algorithms.graph.Graph;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public abstract class AbstractCycleDetectionTest extends AbstractContextTest {

  abstract Graph getCycleGraph();

  abstract Graph getAcyclicGraph();

  abstract CycleDetection getCycleDetection();

  @Test
  public void hasCycleTest() {
    CycleDetection cycleDetection = getCycleDetection();
    cycleDetection.init(getCycleGraph());
    boolean hasCycle = cycleDetection.hasCycle();
    Assert.assertEquals(true, hasCycle);

    List<Integer> path = new ArrayList<>();
    for (Integer v : cycleDetection.cycle()) {
      path.add(v);
    }

    log.info("has cycle :{} , cycle: {}", hasCycle, StringUtils.join(path, "->"));
  }

  @Test
  public void noCycleTest() {
    CycleDetection cycleDetection = getCycleDetection();
    getCycleDetection().init(getAcyclicGraph());
    boolean hasCycle = cycleDetection.hasCycle();
    Assert.assertEquals(false, hasCycle);
    log.info("has cycle :{}", hasCycle);
  }
}
