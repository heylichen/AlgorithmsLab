package algorithms.graph.process;

import java.util.BitSet;

import algorithms.graph.Graph;
import org.springframework.stereotype.Component;

/**
 * Created by Chen Li on 2018/5/5.
 */
@Component
public class DepthFirstTwoColorability implements TwoColorability {

  private BitSet marked;
  private BitSet color;
  private boolean bipartite;

  @Override
  public void init(Graph graph) {
    marked = new BitSet(graph.verticesCount());
    color = new BitSet(graph.verticesCount());
    bipartite = true;
    for (Integer v : graph.getVertices()) {
      if (!marked.get(v)) {
        dfs(graph, v);
      }
      if (!this.bipartite) {
        return;
      }
    }
  }

  private void dfs(Graph graph, int vertex) {
    if (!this.bipartite) {
      return;
    }
    marked.set(vertex);
    boolean revertColor = !color.get(vertex);
    for (Integer adjacentV : graph.adjacentVertices(vertex)) {
      if (marked.get(adjacentV)) {
        if (color.get(adjacentV) != revertColor) {
          this.bipartite = false;
          break;
        }
      } else {
        color.set(adjacentV, revertColor);
        dfs(graph, adjacentV);
      }
    }
  }

  @Override
  public boolean isBipartite() {
    return bipartite;
  }
}



