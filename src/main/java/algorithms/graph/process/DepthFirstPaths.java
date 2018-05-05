package algorithms.graph.process;

import java.util.BitSet;
import java.util.Collection;

import algorithms.graph.Graph;
import org.springframework.stereotype.Component;

/**
 * Created by Chen Li on 2018/5/1.
 */
@Component
public class DepthFirstPaths extends AbstractIntPaths {

  @Override
  public void init(Graph graph, int vertex) {
    this.sourceVertex = vertex;
    this.graph = graph;
    bitSet = new BitSet(graph.verticesCount());
    connectedVerticesCount = 0;
    edgesTo = new int[graph.verticesCount()];
    for (int i = 0; i < edgesTo.length; i++) {
      edgesTo[i] = -1;
    }

    doInit(graph, vertex, bitSet);
  }

  private void doInit(Graph graph, int vertex, BitSet bitSet) {
    connectedVerticesCount++;
    bitSet.set(vertex);
    Collection<Integer> adjacencyVertices = graph.adjacentVertices(vertex);
    for (Integer adjacencyVertex : adjacencyVertices) {
      if (!bitSet.get(adjacencyVertex)) {
        edgesTo[adjacencyVertex] = vertex;
        doInit(graph, adjacencyVertex, bitSet);
      }
    }
  }
}
