package algorithms.graph.practice;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;

import algorithms.graph.Edge;
import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import algorithms.sorting.PriorityQueue;
import algorithms.sorting.heap.PriorityQueueFactory;

/**
 * Created by Chen Li on 2018/5/28.
 */
public class PrimMST implements MST {

  private List<Edge> edges;
  private PriorityQueue<Edge> minPq;
  private BitSet marked;
  private Double weight;

  @Override
  public void init(EdgeWeightedGraph graph) {

    int vCount = graph.verticesCount();
    edges = new ArrayList<>();
    marked = new BitSet(vCount);
    minPq = new PriorityQueueFactory().minPQ(vCount);

    //
    weight = 0.0;
    marked.set(0);
    for (Edge edge : graph.adjacent(0)) {
      minPq.insert(edge);
    }

    while (!minPq.isEmpty()) {
      Edge edge = minPq.pop();
      int v = edge.either();
      int w = edge.theOther(v);

      int unmarkedV;
      if (!marked.get(v)) {
        unmarkedV = v;
      } else if (!marked.get(w)) {
        unmarkedV = w;
      } else {
        continue;
      }
      weight += edge.weight();
      edges.add(edge);
      marked.set(unmarkedV);
      for (Edge adjacentEdge : graph.adjacent(unmarkedV)) {
        minPq.insert(adjacentEdge);
      }
    }
  }


  @Override
  public Collection<Edge> edges() {
    return edges;
  }

  @Override
  public Double weight() {
    return weight;
  }
}
