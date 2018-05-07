package algorithms.graph.process;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;

import algorithms.graph.Graph;
import org.springframework.stereotype.Component;

/**
 * Created by Chen Li on 2018/5/5.
 * undirected graphs
 */
@Component
public class DepthFirstUndirectedCycleDetection implements CycleDetection {

  private BitSet marked;
  private boolean hasCycle;
  private int[] edgesTo;
  private Deque<Integer> cycle;

  @Override
  public void init(Graph graph) {
    edgesTo = new int[graph.verticesCount()];
    marked = new BitSet(graph.verticesCount());
    hasCycle = false;

    for (Integer v : graph.getVertices()) {
      if (hasCycle()) {
        break;
      }
      if (!marked.get(v)) {
        dfs(graph, v, v);
      }
    }
  }

  /**
   * be careful to terminate recursive call
   */
  private void dfs(Graph graph, int vertex, int from) {
    marked.set(vertex);
    for (Integer adjacentVertex : graph.adjacentVertices(vertex)) {
      if (hasCycle()) {
        //be careful to terminate recursive call
        return;
      }
      if (marked.get(adjacentVertex)) {
        if (adjacentVertex != from) {
          this.hasCycle = true;
          cycle = new ArrayDeque<>();

          for (int x = vertex; x != adjacentVertex; x = edgesTo[x]) {
            cycle.push(x);
          }
          cycle.push(adjacentVertex);
          cycle.push(vertex);
          return;
        }
      } else {
        edgesTo[adjacentVertex] = vertex;
        dfs(graph, adjacentVertex, vertex);
      }
    }
  }

  @Override
  public boolean hasCycle() {
    return hasCycle;
  }

  @Override
  public Iterable<Integer> cycle() {
    return cycle;
  }
}
