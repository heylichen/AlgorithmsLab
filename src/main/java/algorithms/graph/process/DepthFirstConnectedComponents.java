package algorithms.graph.process;

import java.util.BitSet;

import algorithms.graph.Graph;
import org.springframework.stereotype.Component;

/**
 * Created by Chen Li on 2018/5/5.
 */
@Component
public class DepthFirstConnectedComponents implements ConnectedComponents {

  private BitSet marked;
  private int ids[];
  private int count;

  @Override
  public void init(Graph graph) {
    marked = new BitSet(graph.verticesCount());
    ids = new int[graph.verticesCount()];
    count = 0;

    for (Integer v : graph.getVertices()) {
      if (!marked.get(v)) {
        doInit(graph, v);
        count++;
      }
    }
  }

  private void doInit(Graph graph, int v) {
    marked.set(v);
    ids[v] = count;
    for (Integer w : graph.adjacentVertices(v)) {
      if(!marked.get(w)){
        doInit(graph, w);
      }
    }
  }

  @Override
  public boolean connected(int v, int w) {
    return ids[v] == ids[w];
  }

  @Override
  public int count() {
    return count;
  }

  @Override
  public int getComponentId(int vertex) {
    return ids[vertex];
  }
}
