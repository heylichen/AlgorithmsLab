package algorithms.graph.process;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import algorithms.context.AbstractContextTest;
import algorithms.graph.Graph;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/5.
 */
@Slf4j
public class DepthFirstConnectedComponentsTest extends AbstractContextTest {

  @Resource(name = "tinyGraph")
  private Graph graph;
  @Resource(name = "depthFirstConnectedComponents")
  private ConnectedComponents connectedComponents;

  @Test
  public void listAllConnectedComponents() {
    connectedComponents.init(graph);

    Map<Integer, Set<Integer>> connectedComponentsMap = new HashMap<>(connectedComponents.count() * 2);

    for (Integer v : graph.getVertices()) {
      int componentId = connectedComponents.getComponentId(v);
      connectedComponentsMap.computeIfAbsent(componentId, k -> new LinkedHashSet<>()).add(v);
    }

    for (Map.Entry<Integer, Set<Integer>> entry : connectedComponentsMap.entrySet()) {
      log.info("componentId {}: {}", entry.getKey(), entry.getValue());
    }

  }
}