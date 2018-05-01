package algorithms.graph.process;

import java.util.BitSet;
import java.util.Collection;

import algorithms.graph.Graph;

/**
 * Created by Chen Li on 2018/4/30.
 */
public class DepthFirstSearch implements Search {

  private BitSet bitSet;
  private int connectedVerticesCount;

  @Override
  public void init(Graph graph, int vertex) {
    bitSet = new BitSet(graph.verticesCount());
    connectedVerticesCount = 0;
    doInit(graph, vertex, bitSet);
  }

  private void doInit(Graph graph, int vertex, BitSet bitSet) {
    connectedVerticesCount++;
    bitSet.set(vertex);
    Collection<Integer> adjacencyVertices = graph.adjacentVertices(vertex);
    for (Integer adjacencyVertex : adjacencyVertices) {
      if (!bitSet.get(adjacencyVertex)) {
        doInit(graph, adjacencyVertex, bitSet);
      }
    }
  }

  @Override
  public boolean marked(int vertex) {
    return bitSet.get(vertex);
  }

  @Override
  public int connectedVerticesCount() {
    return connectedVerticesCount;
  }
}
