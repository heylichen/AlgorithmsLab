package algorithms.graph;

import java.util.Collection;

/**
 * Created by Chen Li on 2018/4/29.
 * API for an undirected graph
 */
public interface Graph {

  void init(int verticesCount);

  void addEdge(int from, int to);

  Collection<Integer> adjacentVertices(int vertex);

  int verticesCount();

  int edgesCount();

  Collection<Integer> getVertices();
}
