package algorithms.graph.practice.weighted.digraph;

import java.util.BitSet;
import java.util.Deque;
import java.util.LinkedList;

import algorithms.graph.DirectedEdge;
import algorithms.graph.EdgeWeightedDigraph;

/**
 * Created by Chen Li on 2018/5/23.
 */
public class DepthFirstWeightedDigraphCycleDetection {

  private BitSet marked;
  private boolean hasCycle;
  private Deque<Integer> cycleVertices;
  //can not use int[] edge to record cycle path
  private Deque<Integer> stack;


  public void init(EdgeWeightedDigraph graph) {
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

  private void visit(EdgeWeightedDigraph graph, Integer v) {
    stack.push(v);
    marked.set(v);
    for (DirectedEdge directedEdge : graph.adjacent(v)) {
      //attention, use marked is bug, only test this connected component
      Integer adjacentV = directedEdge.to();
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


  public boolean hasCycle() {
    return hasCycle;
  }


  public Iterable<Integer> cycle() {
    return cycleVertices;
  }
}
