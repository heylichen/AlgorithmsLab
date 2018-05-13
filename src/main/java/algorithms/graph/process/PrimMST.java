package algorithms.graph.process;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;

import algorithms.graph.Edge;
import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import algorithms.sorting.heap.IndexMinPQ;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class PrimMST implements MST {

  private BitSet marked;
  private Edge[] edgeTo;
  private Double[] distanceTo;
  private IndexMinPQ<Double> queue;
  //
  private List<Edge> edgeList;
  private Double totalWeight;

  @Override
  public void init(EdgeWeightedGraph graph) {
    int vCount = graph.verticesCount();
    marked = new BitSet(vCount);
    edgeTo = new Edge[vCount];
    distanceTo = new Double[vCount];
    queue = new IndexMinPQ<>(vCount);
    //
    for (int i = 0; i < vCount; i++) {
      distanceTo[i] = Double.POSITIVE_INFINITY;
    }
    distanceTo[0] = 0.0;
    queue.insert(0, 0.0);

    while (!queue.isEmpty()) {
      visit(graph, queue.delMin());
    }
  }

  private void visit(EdgeWeightedGraph graph, Integer v) {
    marked.set(v);
    for (Edge edge : graph.adjacent(v)) {
      Integer w = edge.theOther(v);
      if (marked.get(w)) {
        continue;
      }

      if (edge.weight() < distanceTo[w]) {
        edgeTo[w] = edge;
        distanceTo[w] = edge.weight();
        if (queue.contains(w)) {
          queue.changeKey(w, edge.weight());
        } else {
          queue.insert(w, edge.weight());
        }
      }
    }
  }

  @Override
  public Collection<Edge> edges() {
    if (edgeList == null) {
      edgeList = new ArrayList<>();
      for (Edge edge : edgeTo) {
        if (edge != null) {
          edgeList.add(edge);
        }
      }
    }
    return edgeList;
  }

  @Override
  public Double weight() {
    if (totalWeight == null) {
      for (Edge edge : edgeTo) {
        totalWeight += edge.weight();
      }
    }
    return totalWeight;
  }
}
