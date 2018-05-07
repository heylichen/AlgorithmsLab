package algorithms.graph.process;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;

import algorithms.graph.Graph;
import org.springframework.stereotype.Component;

@Component
public class DepthFirstDirectedCycleDetection implements CycleDetection {

  private BitSet marked;
  private int[] edgesTo;
  private BitSet onStack;
  private Deque<Integer> cycle;

  @Override
  public void init(Graph graph) {
    edgesTo = new int[graph.verticesCount()];
    marked = new BitSet(graph.verticesCount());
    onStack = new BitSet();

    for (Integer v : graph.getVertices()) {
      if (hasCycle()) {
        break;
      }
      if (!marked.get(v)) {
        dfs(graph, v);
      }
    }
  }

  /**
   * be careful to terminate recursive call
   */
  private void dfs(Graph graph, int vertex) {
    marked.set(vertex);
    onStack.set(vertex);
    for (Integer adjacentVertex : graph.adjacentVertices(vertex)) {
      if (hasCycle()) {
        //be careful to terminate recursive call
        return;
      } else if (!marked.get(adjacentVertex)) {
        edgesTo[adjacentVertex] = vertex;
        dfs(graph, adjacentVertex);
      } else if (onStack.get(adjacentVertex)) {
        cycle = new ArrayDeque<>();
        for (int x = vertex; x != adjacentVertex; x = edgesTo[x]) {
          cycle.push(x);
        }
        cycle.push(adjacentVertex);
        cycle.push(vertex);
        return;
      }
    }
    onStack.set(vertex, false);
  }

  @Override
  public boolean hasCycle() {
    return cycle!=null;
  }

  @Override
  public Iterable<Integer> cycle() {
    return cycle;
  }
}
