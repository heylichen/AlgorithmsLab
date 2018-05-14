package algorithms.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class EdgeWeightedDigraphImpl implements EdgeWeightedDigraph {

  //vertices count is fixed once inited
  private Collection<Integer> vertices;
  //edges can be added after inited
  private int edgesCount;
  private LinkedList<DirectedEdge>[] adjacentList;

  @Override
  public void init(int verticesCount) {
    //
    Collection<Integer> list = new HashSet<>();
    for (int i = 0; i < verticesCount; i++) {
      list.add(i);
    }
    this.vertices = list;
    //
    edgesCount = 0;

    adjacentList = new LinkedList[verticesCount];
    for (int i = 0; i < verticesCount; i++) {
      adjacentList[i] = new LinkedList<>();
    }
  }

  @Override
  public void addEdge(DirectedEdge edge) {
    edgesCount++;
    adjacentList[edge.from()].add(edge);
    this.vertices.add(edge.from());
    this.vertices.add(edge.to());
  }

  @Override
  public Collection<DirectedEdge> adjacent(int vertex) {
    return adjacentList[vertex];
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
  public Collection<DirectedEdge> getEdges() {
    Collection<DirectedEdge> allEdges = new ArrayList<>();
    for (Integer vertex : vertices) {
      LinkedList<DirectedEdge> adjacentEdges = adjacentList[vertex];
      allEdges.addAll(adjacentEdges);
    }
    return allEdges;
  }
}
