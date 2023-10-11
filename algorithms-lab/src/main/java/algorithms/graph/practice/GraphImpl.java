package algorithms.graph.practice;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import algorithms.graph.Graph;

/**
 * Created by Chen Li on 2018/5/20.
 */
public class GraphImpl implements Graph {

  private LinkedList<Integer>[] adjacentVerticesList;
  private Set<Integer> vertices;
  private int edgesCount;

  @Override
  public void init(int verticesCount) {
    vertices = new LinkedHashSet<>(verticesCount);
    adjacentVerticesList = new LinkedList[verticesCount];
    for (int i = 0; i < verticesCount; i++) {
      adjacentVerticesList[i] = new LinkedList<>();
    }
  }

  @Override
  public void addEdge(int from, int to) {
    adjacentVerticesList[from].add(to);
    adjacentVerticesList[to].add(from);
    vertices.add(from);
    vertices.add(to);
    edgesCount++;
  }

  @Override
  public Collection<Integer> adjacentVertices(int vertex) {
    return adjacentVerticesList[vertex];
  }

  @Override
  public int verticesCount() {
    return vertices.size();
  }

  @Override
  public int edgesCount() {
    return edgesCount;
  }

  @Override
  public Collection<Integer> getVertices() {
    return vertices;
  }
}
