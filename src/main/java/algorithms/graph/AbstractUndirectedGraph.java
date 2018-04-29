package algorithms.graph;

/**
 * Created by Chen Li on 2018/4/29.
 */
public abstract class AbstractUndirectedGraph implements Graph {

  @Override
  public int degree(int vertex) {
    return this.adjacentVertices(vertex).size();
  }

  public String toString() {
    int verticesCount = verticesCount();
    String s = verticesCount + " vertices, " + edgesCount() + " edges\n";
    for (int v = 0; v < verticesCount; v++) {
      s += v + ": ";
      for (int w : this.adjacentVertices(v)) {
        s += w + " ";
      }
      s += "\n";
    }
    return s;
  }
}
