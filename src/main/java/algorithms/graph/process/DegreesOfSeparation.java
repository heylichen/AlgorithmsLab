package algorithms.graph.process;

import algorithms.graph.Graph;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Chen Li on 2018/5/6.
 */
@Getter
@Setter
@Slf4j
public class DegreesOfSeparation {


  private SymbolGraph symbolGraph;
  private String sourceVertex;
  private BreadthFirstPaths paths = new BreadthFirstPaths();

  public void report(String target) {
    if(!symbolGraph.contains(sourceVertex)){
      log.info("source vertex not in database.");
      return;
    }
    if(!symbolGraph.contains(target)){
      log.info("target vertex not in database.");
      return;
    }
    Graph graph = symbolGraph.getGraph();
    paths.init(graph, symbolGraph.index(sourceVertex));
    log.info("{}:", sourceVertex);
    for (Integer v : paths.pathTo(symbolGraph.index(target))) {
      log.info("  {}", symbolGraph.name(v));
    }
  }
}
