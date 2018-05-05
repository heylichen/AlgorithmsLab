package algorithms.graph.process;

import algorithms.graph.Graph;
import algorithms.graph.UndirectedGraphFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Chen Li on 2018/5/4.
 */
@Configuration
public class UndirectedGraphContest {

  @Value("${graph.undirected.data.tiny.path}")
  private String tinyGPath;
  @Value("${graph.undirected.source.vertex.0}")
  private int source0;

  @Bean
  public UndirectedGraphFactory undirectedGraphFactory() {
    return new UndirectedGraphFactory();
  }

  @Bean
  public Graph tinyGraph() {
    UndirectedGraphFactory undirectedGraphFactory = undirectedGraphFactory();
    return undirectedGraphFactory.loadGraph(tinyGPath);
  }

  @Bean
  public Paths depthFirstPaths() {
    Graph tinyGraph = tinyGraph();
    DepthFirstPaths paths = new DepthFirstPaths();
    paths.init(tinyGraph, source0);
    return paths;
  }

  @Bean
  public Paths breadthFirstPaths() {
    Graph tinyGraph = tinyGraph();
    Paths paths = new BreadthFirstPaths();
    paths.init(tinyGraph, source0);
    return paths;
  }

  @Bean
  public Search depthFirstSearch() {
    Graph tinyGraph = tinyGraph();
    DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
    depthFirstSearch.init(tinyGraph, source0);
    return depthFirstSearch;
  }
}
