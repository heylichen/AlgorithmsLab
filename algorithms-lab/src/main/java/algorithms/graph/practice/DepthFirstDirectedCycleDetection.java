package algorithms.graph.practice;

import java.util.BitSet;
import java.util.Deque;
import java.util.LinkedList;

import algorithms.graph.Graph;
import algorithms.graph.process.CycleDetection;

/**
 * Created by Chen Li on 2018/5/23.
 */
public class DepthFirstDirectedCycleDetection implements CycleDetection {

  private BitSet marked;
  private boolean hasCycle;
  private Deque<Integer> cycleVertices;
  //can not use int[] edge to record cycle path
  private Deque<Integer> stack;

  @Override
  public void init(Graph graph) {
    stack = new LinkedList<>();
    marked = new BitSet(graph.verticesCount());
    for (Integer vertex : graph.getVertices()) {
      //another connected component
      if (!marked.get(vertex)) {
        visit(graph, vertex);
      }
      //if found cycle, don't bother to try another connected component
      if (hasCycle) {
        break;
      }
    }
  }

  private void visit(Graph graph, Integer v) {
    stack.push(v);
    marked.set(v);

    for (Integer adjacentV : graph.adjacentVertices(v)) {
      //attention, use marked is bug, only test this connected component
      if (stack.contains(adjacentV)) {
        //cycle detected!
        cycleVertices = new LinkedList<>();
        Integer current = stack.pop();
        while (current != adjacentV && !stack.isEmpty()) {
          cycleVertices.push(current);
          current = stack.pop();
        }
        cycleVertices.push(adjacentV);
        cycleVertices.push(v);
        hasCycle = true;
        return;

      } else {
        visit(graph, adjacentV);
      }
      if (hasCycle) {
        return;
      }
    }
    stack.pop();
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
