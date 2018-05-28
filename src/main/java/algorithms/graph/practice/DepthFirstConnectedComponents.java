package algorithms.graph.practice;

import java.util.BitSet;

import algorithms.graph.Graph;
import algorithms.graph.process.ConnectedComponents;

/**
 * Created by Chen Li on 2018/5/27.
 */
public class DepthFirstConnectedComponents implements ConnectedComponents<Graph> {

  private BitSet marked;
  private int componentIds[];
  private int count = 0;

  @Override
  public void init(Graph graph) {
    int vCount = graph.verticesCount();
    componentIds = new int[vCount];
    marked = new BitSet(vCount);

    for (Integer v : graph.getVertices()) {
      if (!marked.get(v)) {
        visit(graph, v);
        count++;
      }
    }
  }

  private void visit(Graph graph, int v) {
    marked.set(v);
    componentIds[v] = count;

    for (Integer adjacentV : graph.adjacentVertices(v)) {
      if (!marked.get(adjacentV)) {
        visit(graph, adjacentV);
      }
    }
  }

  @Override
  public boolean connected(int v, int w) {
    return componentIds[v] == componentIds[w];
  }

  @Override
  public int count() {
    return count;
  }

  @Override
  public int getComponentId(int vertex) {
    return componentIds[vertex];
  }
}
