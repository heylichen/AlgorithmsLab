package algorithms.graph.practice;

import java.util.HashSet;
import java.util.Set;

import algorithms.graph.GraphFactory;
import algorithms.graph.process.CycleDetection;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/22.
 */
public abstract class UndirectedCycleDetectionTest {

  protected abstract CycleDetection getInstance();

  @Test
  public void cycleDetectedTest() {
    //create graph
    GraphImpl impl = new GraphImpl();
    GraphFactory gf = new GraphFactory();
    gf.loadGraph(impl, "algorithms/graph/practice/undirected/undirectedCycle13.txt");
    //has cycle
    CycleDetection cycleDetection = getInstance();
    cycleDetection.init(impl);
    Assert.assertEquals(true, cycleDetection.hasCycle());
    //cycle path
    Set<Integer> cycleVertices = new HashSet<>();
    for (Integer integer : cycleDetection.cycle()) {
      cycleVertices.add(integer);
    }
    Assert.assertEquals(Sets.newHashSet(7,11,12,3), cycleVertices);
  }
}
