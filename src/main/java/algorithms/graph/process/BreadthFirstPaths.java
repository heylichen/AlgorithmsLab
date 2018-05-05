package algorithms.graph.process;

import java.util.BitSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.graph.Graph;

public class BreadthFirstPaths extends AbstractIntPaths {

  @Override
  public void init(Graph graph, int sourceVertex) {
    bitSet = new BitSet(graph.verticesCount());
    edgesTo = new int[graph.verticesCount()];
    this.sourceVertex = sourceVertex;
    this.graph = graph;

    Queue<Integer> queue = new LinkedList<>();
    bitSet.set(sourceVertex);
    queue.add(sourceVertex);

    while (!queue.isEmpty()) {
      Integer v = queue.poll();
      Collection<Integer> adjacentVertices = graph.adjacentVertices(v);
      for (Integer adjacentVertex : adjacentVertices) {
        if (!bitSet.get(adjacentVertex)) {
          bitSet.set(adjacentVertex);
          edgesTo[adjacentVertex] = v;
          queue.offer(adjacentVertex);
        }
      }
    }
  }
}
