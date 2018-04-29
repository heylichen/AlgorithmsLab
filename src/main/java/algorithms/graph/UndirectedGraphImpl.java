package algorithms.graph;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Chen Li on 2018/4/29.
 */
public class UndirectedGraphImpl extends AbstractUndirectedGraph {

  private LinkedList<Integer>[] adjacencyListOfVertices;
  private int verticesCount;
  private int edgesCount;

  public UndirectedGraphImpl(int verticesCount) {
    this.verticesCount = verticesCount;
    this.edgesCount = 0;
    adjacencyListOfVertices = new LinkedList[verticesCount];
    for (int i = 0; i < verticesCount; i++) {
      adjacencyListOfVertices[i] = new LinkedList<>();
    }
  }

  @Override
  public void addEdge(int from, int to) {
    adjacencyListOfVertices[from].add(to);
    adjacencyListOfVertices[to].add(from);
    this.edgesCount++;
  }

  @Override
  public Collection<Integer> adjacentVertices(int vertex) {
    return adjacencyListOfVertices[vertex];
  }

  @Override
  public int verticesCount() {
    return this.verticesCount;
  }

  @Override
  public int edgesCount() {
    return this.edgesCount;
  }
}
