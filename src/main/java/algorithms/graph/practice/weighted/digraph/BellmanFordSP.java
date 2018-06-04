package algorithms.graph.practice.weighted.digraph;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.graph.DirectedEdge;
import algorithms.graph.EdgeWeightedDigraph;
import algorithms.graph.EdgeWeightedDigraphImpl;

/**
 * Created by Chen Li on 2018/6/4.
 * with negative weighted edge allowed, cycle allowed, but negative cycle not allowed.
 */
public class BellmanFordSP {

  private Double[] distTo;
  private DirectedEdge[] edgeTo;
  private Queue<Integer> queue;
  private DepthFirstWeightedDigraphCycleDetection cycle;
  private int relaxCount;
  private EdgeWeightedDigraph digraph;

  public void init(EdgeWeightedDigraph digraph, Integer source) {
    this.digraph = digraph;
    int vCount = digraph.verticesCount();
    distTo = new Double[vCount];
    edgeTo = new DirectedEdge[vCount];
    queue = new LinkedList<>();

    for (int i = 0; i < vCount; i++) {
      distTo[i] = Double.POSITIVE_INFINITY;
    }
    distTo[source] = 0d;

    queue.add(source);
    relaxCount = 0;
    while (!queue.isEmpty() && !hasNegativeCycle()) {
      relax(digraph);
    }
  }

  private void relax(EdgeWeightedDigraph digraph) {
    Integer vertex = queue.remove();
    for (DirectedEdge directedEdge : digraph.adjacent(vertex)) {
      Integer w = directedEdge.to();
      if (distTo[w] > distTo[vertex] + directedEdge.weight()) {
        distTo[w] = distTo[vertex] + directedEdge.weight();
        edgeTo[w] = directedEdge;
        if (!queue.contains(w)) {
          queue.add(w);
        }
      }
    }
    relaxCount++;
    if (relaxCount % digraph.verticesCount() == 0) {
      detectNegativeCycle();
    }
  }

  /**
   * build a edge weighted digraph from edgeTo
   */
  private void detectNegativeCycle() {
    EdgeWeightedDigraph digraph = new EdgeWeightedDigraphImpl();
    digraph.init(this.digraph.verticesCount());
    for (DirectedEdge directedEdge : edgeTo) {
      if (directedEdge != null) {
        digraph.addEdge(directedEdge);
      }
    }

    DepthFirstWeightedDigraphCycleDetection cycleDetection = new DepthFirstWeightedDigraphCycleDetection();
    cycleDetection.init(digraph);
    if (cycleDetection.hasCycle()) {
      this.cycle = cycleDetection;
    }
  }

  public boolean hasNegativeCycle() {
    return cycle != null;
  }

  public Iterable<Integer> cycle() {
    if (hasNegativeCycle()) {
      return this.cycle.cycle();
    } else {
      return Collections.emptyList();
    }
  }

  public Collection<DirectedEdge> pathTo(Integer vertex) {
    if (hasNegativeCycle()) {
      return Collections.emptyList();
    }
    Deque<DirectedEdge> stack = new LinkedList<>();
    DirectedEdge current = edgeTo[vertex];
    while (current != null) {
      stack.push(current);
      current = edgeTo[current.from()];
    }
    return stack;
  }
}
