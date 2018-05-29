package algorithms.graph.practice;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithms.graph.Edge;
import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import algorithms.sorting.heap.IndexMinPQ;

public class EagerPrimMST implements MST {

  private BitSet marked;
  private double[] distTo;
  //keep the minimum weight of edge , connecting a non-mst vertex to current mst, array index is the non-mst vertex
  private IndexMinPQ<Double> weightToVertices;
  //index is vertex, key is the weight of minimum weighted edge connecting a non-mst vertex to current mst
  private Map<Integer, Edge> minEdge;
  //map vertex to the minimum weighted edge
  private List<Edge> mst;
  //result mst
  private double weight;
  //total weight of mst

  @Override
  public void init(EdgeWeightedGraph graph) {
    //allocate spaces
    int vCount = graph.verticesCount();
    marked = new BitSet(vCount);
    distTo = new double[vCount];
    weightToVertices = new IndexMinPQ<>(vCount);
    minEdge = new HashMap<>(vCount);
    mst = new ArrayList<>();

    for (int i = 0; i < vCount; i++) {
      distTo[i] = Double.POSITIVE_INFINITY;
    }
    //start from 0
    int source = 0;
    marked.set(source);
    distTo[source] = 0;
    exploreAdjacent(graph, source);

    while (!weightToVertices.isEmpty()) {
      int minV = weightToVertices.delMin();
      marked.set(minV);
      Edge edge = minEdge.get(minV);
      mst.add(edge);
      weight += edge.weight();

      exploreAdjacent(graph, minV);
    }
  }

  private void exploreAdjacent(EdgeWeightedGraph graph, int v) {
    for (Edge edge1 : graph.adjacent(v)) {
      int w = edge1.theOther(v);
      if (marked.get(w)) {
        continue;
      }
      if (distTo[w] <= edge1.weight()) {
        continue;
      }
      distTo[w] = edge1.weight();
      minEdge.put(w, edge1);
      if (weightToVertices.contains(w)) {
        weightToVertices.changeKey(w, edge1.weight());
      } else {
        weightToVertices.insert(w, edge1.weight());
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
