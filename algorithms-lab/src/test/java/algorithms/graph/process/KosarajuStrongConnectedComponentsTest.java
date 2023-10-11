package algorithms.graph.process;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import algorithms.context.AbstractContextTest;
import algorithms.graph.Digraph;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class KosarajuStrongConnectedComponentsTest extends AbstractContextTest {

  @Resource(name = "tinyDAG")
  private Digraph graph;
  @Resource(name = "kosarajuStrongConnectedComponents")
  private ConnectedComponents<Digraph> strongCC;

  @Test
  public void doTest() {
    strongCC.init(graph);

    Map<Integer, Set<Integer>> connectedComponentsMap = new HashMap<>(strongCC.count() * 2);

    for (Integer v : graph.getVertices()) {
      int componentId = strongCC.getComponentId(v);
      connectedComponentsMap.computeIfAbsent(componentId, k -> new LinkedHashSet<>()).add(v);
    }

    for (Map.Entry<Integer, Set<Integer>> entry : connectedComponentsMap.entrySet()) {
      log.info("componentId {}: {}", entry.getKey(), entry.getValue());
    }
  }
}