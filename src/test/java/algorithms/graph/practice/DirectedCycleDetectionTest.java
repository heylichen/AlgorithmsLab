package algorithms.graph.practice;

import java.util.HashSet;
import java.util.Set;

import algorithms.graph.GraphFactory;
import algorithms.graph.process.CycleDetection;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/22.
 */
@Slf4j
public abstract class DirectedCycleDetectionTest {

  protected abstract CycleDetection getInstance();

  @Test
  public void cycleDetectedTest() {
    //create graph
    DigraphImpl impl = new DigraphImpl();
    GraphFactory gf = new GraphFactory();
    gf.loadGraph(impl, "algorithms/graph/practice/directed/directedCycle13.txt");
    //has cycle
    CycleDetection cycleDetection = getInstance();
    cycleDetection.init(impl);
    Assert.assertEquals(true, cycleDetection.hasCycle());
    log.info("cycle :{}", StringUtils.join(cycleDetection.cycle(),"->"));

    //cycle path
    Set<Integer> cycleVertices = new HashSet<>();
    for (Integer integer : cycleDetection.cycle()) {
      cycleVertices.add(integer);
    }
    Assert.assertEquals(Sets.newHashSet(7, 11, 12, 3), cycleVertices);
  }

  @Test
  public void noCycleTest() {
    DigraphImpl impl = new DigraphImpl();
    GraphFactory gf = new GraphFactory();
    gf.loadGraph(impl, "algorithms/graph/practice/directed/directedNoCycle13.txt");
    //has cycle
    CycleDetection cycleDetection = getInstance();
    cycleDetection.init(impl);
    Assert.assertEquals(false, cycleDetection.hasCycle());

  }
}
