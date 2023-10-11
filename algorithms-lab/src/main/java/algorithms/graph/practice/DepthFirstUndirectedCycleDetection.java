package algorithms.graph.practice;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import algorithms.graph.Graph;
import algorithms.graph.process.CycleDetection;

/**
 * Created by Chen Li on 2018/5/22.
 */
public class DepthFirstUndirectedCycleDetection implements CycleDetection {

  private BitSet marked;
  private int[] edgeTo;
  private boolean hasCycle;
  private List<Integer> cycleVertices;

  @Override
  public void init(Graph graph) {
    edgeTo = new int[graph.verticesCount()];
    marked = new BitSet(graph.verticesCount());
    for (Integer vertex : graph.getVertices()) {
      //another connected component
      if (!marked.get(vertex)) {
        visit(graph, vertex, vertex);
      }
      //if found cycle, don't bother to try another connected component
      if (hasCycle) {
        break;
      }
    }
  }

  private void visit(Graph graph, Integer v, Integer from) {
    marked.set(v);

    for (Integer adjacentV : graph.adjacentVertices(v)) {
      if (marked.get(adjacentV)) {
        if (adjacentV != from) {
          //cycle detected!
          cycleVertices = new ArrayList<>();
          Integer current = v;
          while (current != adjacentV) {
            cycleVertices.add(current);
            current = edgeTo[current];
          }
          cycleVertices.add(adjacentV);
          cycleVertices.add(v);
          hasCycle = true;
          return;
        }
      } else {
        edgeTo[adjacentV] = v;
        visit(graph, adjacentV, v);
      }
      if (hasCycle) {
        return;
      }
    }
  }

  @Override
  public boolean hasCycle() {
    return hasCycle;
  }

  @Override
  public Iterable<Integer> cycle() {
    return cycleVertices;
  }
}
