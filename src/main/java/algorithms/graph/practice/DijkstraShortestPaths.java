package algorithms.graph.practice;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

import algorithms.graph.DirectedEdge;
import algorithms.graph.EdgeWeightedDigraph;
import algorithms.graph.ShortestPaths;
import algorithms.sorting.heap.IndexMinPQ;

/**
 * Created by Chen Li on 2018/5/31.
 */
public class DijkstraShortestPaths implements ShortestPaths {

  private double distTo[];
  private DirectedEdge[] edgeTo;
  private IndexMinPQ<Double> pq;

  @Override
  public void init(EdgeWeightedDigraph graph, Integer source) {
    int vCount = graph.verticesCount();

    distTo = new double[vCount];
    edgeTo = new DirectedEdge[vCount];
    pq = new IndexMinPQ<>(vCount);

    for (int i = 0; i < vCount; i++) {
      distTo[i] = Double.POSITIVE_INFINITY;
    }
    distTo[source] = 0;
    pq.insert(source, 0d);

    while (!pq.isEmpty()) {
      Integer v = pq.delMin();
      relaxation(graph, v);
    }
  }

  private void relaxation(EdgeWeightedDigraph graph, Integer vertex) {
    for (DirectedEdge edge : graph.adjacent(vertex)) {
      Integer to = edge.to();
      if (distTo[to] > distTo[vertex] + edge.weight()) {
        distTo[to] = distTo[vertex] + edge.weight();
        edgeTo[to] = edge;
        if (pq.contains(to)) {
          pq.changeKey(to, distTo[to]);
        } else {
          pq.insert(to, distTo[to]);
        }
      }
    }
  }

  @Override
  public Double distanceTo(Integer vertex) {
    return distTo[vertex];
  }

  @Override
  public boolean hasPathTo(Integer vertex) {
    return distTo[vertex] < Double.POSITIVE_INFINITY;
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
