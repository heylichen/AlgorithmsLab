package algorithms.graph.process;

import java.util.BitSet;

import algorithms.graph.Graph;
import org.springframework.stereotype.Component;

/**
 * Created by Chen Li on 2018/5/5.
 */
@Component
public class DepthFirstCycleDetection implements CycleDetection {

  private BitSet marked;
  private boolean hasCycle;

  @Override
  public void init(Graph graph) {
    marked = new BitSet(graph.verticesCount());
    hasCycle = false;

    for (Integer v : graph.getVertices()) {
      if (!marked.get(v)) {
        dfs(graph, v, v);
      }
      if (hasCycle) {
        break;
      }
    }
  }

  private void dfs(Graph graph, int vertex, int from) {
    if (hasCycle) {
      return;
    }
    marked.set(vertex);
    for (Integer adjacentVertex : graph.adjacentVertices(vertex)) {
      if (marked.get(adjacentVertex)) {
        if (adjacentVertex != from) {
          this.hasCycle = true;
          return;
        }
      } else {
        dfs(graph, adjacentVertex, vertex);
      }
    }
  }

  @Override
  public boolean hasCycle() {
    return hasCycle;
  }
}
