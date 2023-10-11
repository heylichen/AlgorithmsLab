package algorithms.graph;

/**
 * Created by Chen Li on 2018/4/29.
 */
public class UndirectedGraphImpl extends AbstractIntGraph {

  @Override
  public void addEdge(int from, int to) {
    adjacencyListOfVertices[from].add(to);
    adjacencyListOfVertices[to].add(from);
    this.edgesCount++;
  }
}
