package algorithms.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public abstract class AbstractIntGraph implements Graph {

  protected LinkedList<Integer>[] adjacencyListOfVertices;
  protected int verticesCount;
  protected int edgesCount;
  protected Collection<Integer> vertices;

  @Override
  public void init(int verticesCount) {
    this.verticesCount = verticesCount;
    this.edgesCount = 0;
    this.vertices = new ArrayList<>();
    adjacencyListOfVertices = new LinkedList[verticesCount];
    for (int i = 0; i < verticesCount; i++) {
      vertices.add(i);
      adjacencyListOfVertices[i] = new LinkedList<>();
    }
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

  @Override
  public Collection<Integer> getVertices() {
    return this.vertices;
  }
}
