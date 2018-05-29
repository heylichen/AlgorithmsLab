package algorithms.graph.practice;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    int source = 0;
    weight = 0.0;
    marked.set(source);
    exploreAdjacent(graph, source);

    while (!minPq.isEmpty()) {
      Edge edge = minPq.pop();
      Optional<Integer> optional = getUnmarkedVertex(edge);
      if(!optional.isPresent()){
        continue;
      }
      int unmarkedV = optional.get();

      weight += edge.weight();
      edges.add(edge);
      marked.set(unmarkedV);
      exploreAdjacent(graph, unmarkedV);
    }
  }

  private Optional<Integer> getUnmarkedVertex(Edge edge) {
    int v = edge.either();
    if (!marked.get(v)) {
      return Optional.of(v);
    }
    int w = edge.theOther(v);
    if (!marked.get(w)) {
      return Optional.of(w);
    }
    return Optional.empty();
  }

  private void exploreAdjacent(EdgeWeightedGraph graph, int v) {
    for (Edge adjacentEdge : graph.adjacent(v)) {
      minPq.insert(adjacentEdge);
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
