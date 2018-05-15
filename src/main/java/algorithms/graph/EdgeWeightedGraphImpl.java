package algorithms.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class EdgeWeightedGraphImpl implements EdgeWeightedGraph {

  //vertices count is fixed once inited
  private Collection<Integer> vertices;
  //edges can be added after inited
  private int edgesCount;
  private LinkedList<Edge>[] adjacentList;

  public void init(int verticesCount) {
    //
    Collection<Integer> list = new ArrayList<>();
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
  public void addEdge(Edge edge) {
    edgesCount++;
    Integer one = edge.either();
    Integer theOther = edge.theOther(one);

    adjacentList[one].add(edge);
    adjacentList[theOther].add(edge);
  }

  @Override
  public Collection<Edge> adjacent(int vertex) {
    return adjacentList[vertex];
  }

  @Override
  public int verticesCount() {
    return this.vertices.size();
  }

  @Override
  public Collection<Integer> getVertices() {
    return this.vertices;
  }

  @Override
  public int edgesCount() {
    return edgesCount;
  }

  @Override
  public Collection<Edge> getEdges() {
    Collection<Edge> allEdges = new HashSet<>();
    for (Integer vertex : vertices) {
      LinkedList<Edge> adjacentEdges = adjacentList[vertex];
      allEdges.addAll(adjacentEdges);
    }
    return allEdges;
  }
}
