package algorithms.graph.process;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.fundamentals.UnionFind;
import algorithms.fundamentals.WeightedQuickUnionImpl;
import algorithms.graph.Edge;
import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import algorithms.sorting.PriorityQueue;
import algorithms.sorting.heap.PriorityQueueFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Chen Li on 2018/5/15.
 */
@Component
@Scope(scopeName = "prototype")
public class KruskalMST implements MST {

  private Queue<Edge> mst;
  private Double weight;

  @Override
  public void init(EdgeWeightedGraph graph) {
    mst = new LinkedList<>();
    UnionFind uf = new WeightedQuickUnionImpl();
    uf.init(graph.edgesCount());

    PriorityQueue<Edge> pq = new PriorityQueueFactory().minPQ(graph.edgesCount());
    for (Edge edge : graph.getEdges()) {
      pq.insert(edge);
    }
    weight= 0.0;
    int mstCount = graph.verticesCount() - 1;
    while (!pq.isEmpty() && mst.size() < mstCount) {
      Edge minEdge = pq.pop();
      Integer v = minEdge.either();
      Integer w = minEdge.theOther(v);
      if (uf.connected(v, w)) {
        continue;
      }
      uf.union(v, w);
      mst.add(minEdge);
      weight += minEdge.weight();
    }
  }

  @Override
  public Collection<Edge> edges() {
    return mst;
  }

  @Override
  public Double weight() {
    return weight;
  }
}
