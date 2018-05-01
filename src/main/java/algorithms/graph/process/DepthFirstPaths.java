package algorithms.graph.process;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;

import algorithms.graph.Graph;
import lombok.Getter;

/**
 * Created by Chen Li on 2018/5/1.
 */
public class DepthFirstPaths implements Paths {

  private BitSet bitSet;
  private int connectedVerticesCount;
  private int[] edgesTo;
  @Getter
  private int sourceVertex;
  @Getter
  private Graph graph;

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

  @Override
  public boolean hasPathTo(int v) {
    return bitSet.get(v);
  }

  @Override
  public Iterable<Integer> pathTo(int v) {

    if (!hasPathTo(v)) {
      return Collections.emptyList();
    }
    Deque<Integer> stack = new ArrayDeque<>();
    while (v != sourceVertex) {
      stack.push(v);
      v = edgesTo[v];
    }
    stack.push(v);
    return stack;
  }
}
