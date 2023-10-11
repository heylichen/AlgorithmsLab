package algorithms.graph.practice;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.graph.DirectedEdge;
import algorithms.graph.EdgeWeightedDigraph;
import algorithms.graph.ShortestPaths;

public class DijkstraShortestPathsV1 implements ShortestPaths {

  private double[] distTo;
  private Queue<DirectedEdge> queue;
  private DirectedEdge[] edgeTo;

  @Override
  public void init(EdgeWeightedDigraph graph, Integer source) {
    int vCount = graph.verticesCount();

    distTo = new double[vCount];
    edgeTo = new DirectedEdge[vCount];
    queue = new LinkedList<>();

    for (int i = 0; i < vCount; i++) {
      distTo[i] = Double.POSITIVE_INFINITY;
    }

    distTo[source] = 0;
    edgeTo[source] = null;

    addEdgesToQueue(graph, source);
    //
    while (!queue.isEmpty()) {
      DirectedEdge edge = queue.remove();
      Integer from = edge.from();
      Integer to = edge.to();
      if (distTo[to] > distTo[from] + edge.weight()) {
        distTo[to] = distTo[from] + edge.weight();
        edgeTo[to] = edge;
        addEdgesToQueue(graph, to);
      }
    }
  }

  private void addEdgesToQueue(EdgeWeightedDigraph graph, Integer vertex) {
    if (!queue.contains(vertex)) {
      for (DirectedEdge adjacentEdge : graph.adjacent(vertex)) {
        queue.add(adjacentEdge);
      }
    }
  }

  @Override
  public Double distanceTo(Integer vertex) {
    return distTo[vertex];
  }

  @Override
  public boolean hasPathTo(Integer vertex) {
    return distanceTo(vertex) < Double.POSITIVE_INFINITY;
  }

  @Override
  public Collection<DirectedEdge> pathTo(Integer vertex) {
    if (!hasPathTo(vertex)) {
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
