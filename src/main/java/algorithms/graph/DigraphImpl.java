package algorithms.graph;

/**
 * directed graph
 */
public class DigraphImpl extends AbstractIntGraph implements Digraph {

  @Override
  public void addEdge(int from, int to) {
    adjacencyListOfVertices[from].add(to);
    this.edgesCount++;
  }

  @Override
  public Digraph reverse() {
    DigraphImpl copy = new DigraphImpl();
    copy.init(this.verticesCount);

    for (Integer v : getVertices()) {
      for (Integer adjacentVertex : adjacentVertices(v)) {
        copy.addEdge(adjacentVertex, v);
      }
    }
    return copy;
  }
}
