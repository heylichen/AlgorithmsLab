package algorithms.graph.practice;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.graph.Digraph;
import algorithms.graph.process.TopologicalSort;

public class QueueTopoSort implements TopologicalSort {
  private Queue<Integer> queue;//hold all vertices with in degree ==0
  private int[] inDegree;//hold in degree of vertex indexed by it
  private LinkedHashSet<Integer> topoOrder;
  private boolean hasCycle;

  public void init(Digraph digraph) {
    int vCount = digraph.verticesCount();
    queue = new LinkedList<>();
    inDegree = new int[vCount];//all default elements is 0

    for (Integer v : digraph.getVertices()) {
      for (Integer w : digraph.adjacentVertices(v)) {
        inDegree[w] += 1;
      }
    }

    for (Integer v : digraph.getVertices()) {
      if (inDegree[v] == 0) {
        queue.add(v);
      }
    }

    topoOrder = new LinkedHashSet<>();
    while (!queue.isEmpty()) {
      Integer v = queue.remove();
      topoOrder.add(v);
      for (Integer w : digraph.adjacentVertices(v)) {
        topoOrder.add(w);
        inDegree[w] -= 1;
        if (inDegree[w] == 0) {
          queue.add(w);
        }
      }
    }
    hasCycle = topoOrder.size() < digraph.verticesCount();
    if (hasCycle) {
      topoOrder = new LinkedHashSet();
    }
  }

  public boolean isDAG() {
    return !hasCycle;
  }

  public Iterable<Integer> sort() {
    return topoOrder;
  }
}
