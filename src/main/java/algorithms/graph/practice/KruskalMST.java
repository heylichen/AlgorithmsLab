package algorithms.graph.practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import algorithms.fundamentals.UnionFind;
import algorithms.fundamentals.WeightedQuickUnionImpl;
import algorithms.graph.Edge;
import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import algorithms.sorting.PriorityQueue;
import algorithms.sorting.heap.PriorityQueueFactory;

/**
 * Created by Chen Li on 2018/5/29.
 */
public class KruskalMST implements MST {

  private List<Edge> mst;
  private double weight;

  @Override
  public void init(EdgeWeightedGraph graph) {
    PriorityQueue<Edge> minPq = new PriorityQueueFactory().minPQ(graph.edgesCount());
    //put all edges in minPriorityQueue in order to retrieve the minimum every time
    for (Edge edge : graph.getEdges()) {
      minPq.insert(edge);
    }

    UnionFind unionFind = new WeightedQuickUnionImpl();
    //use UnionFind to detect cycle
    unionFind.init(graph.verticesCount());
    mst = new ArrayList<>();
    while (!minPq.isEmpty()) {
      Edge edge = minPq.pop();
      int v = edge.either();
      int w = edge.theOther(v);
      if (!unionFind.connected(v, w)) {
        unionFind.union(v, w);
        mst.add(edge);
        weight += edge.weight();
      }
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
