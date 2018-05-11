package algorithms.graph.process;

import java.util.BitSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.graph.Edge;
import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.MST;
import algorithms.sorting.PriorityQueue;
import algorithms.sorting.heap.PriorityQueueFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Chen Li on 2018/5/11.
 */
@Component
@Scope(scopeName = "prototype")
public class LazyPrimMST implements MST {

  private BitSet marked;
  private Queue<Edge> mstEdges;
  private PriorityQueue<Edge> queue;
  private double weight;


  @Override
  public void init(EdgeWeightedGraph graph) {
    int vCount = graph.verticesCount();
    marked = new BitSet(vCount);
    mstEdges = new LinkedList<>();
    PriorityQueueFactory factory = new PriorityQueueFactory();
    queue = factory.minPQ(graph.edgesCount());

    visit(graph, 0);
    while (!queue.isEmpty()) {
      Edge minEdge = queue.pop();
      int v = minEdge.either();
      int w = minEdge.theOther(v);
      if (!marked.get(v)) {
        mstEdges.add(minEdge);
        weight += minEdge.weight();
        visit(graph, v);
      } else if (!marked.get(w)) {
        mstEdges.add(minEdge);
        weight += minEdge.weight();
        visit(graph, w);
      }
    }

  }

  private void visit(EdgeWeightedGraph graph, Integer v) {
    marked.set(v);
    for (Edge edge : graph.adjacent(v)) {
      int w = edge.theOther(v);
      if (marked.get(w)) {
        continue;
      }
      queue.insert(edge);
    }
  }

  @Override
  public Collection<Edge> edges() {
    return mstEdges;
  }

  @Override
  public Double weight() {
    return weight;
  }
}
