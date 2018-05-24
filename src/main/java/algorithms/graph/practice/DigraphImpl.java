package algorithms.graph.practice;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import algorithms.graph.Digraph;

/**
 * Created by Chen Li on 2018/5/23.
 */
public class DigraphImpl implements Digraph {

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

  @Override
  public Digraph reverse() {
    DigraphImpl impl = new DigraphImpl();
    impl.init(this.verticesCount());
    for (Integer v : this.getVertices()) {
      for (Integer adjacentV : this.adjacentVertices(v)) {
        impl.addEdge(adjacentV, v);
      }
    }
    return impl;
  }
}
