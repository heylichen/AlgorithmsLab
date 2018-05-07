package algorithms.graph.process;

import algorithms.graph.Graph;
import algorithms.graph.UndirectedGraphFactory;
import algorithms.graph.UndirectedGraphImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Chen Li on 2018/5/4.
 */
@Configuration
public class UndirectedGraphContest {

  @Value("${graph.undirected.data.tiny.path}")
  private String tinyGPath;
  @Value("${graph.undirected.data.no_cycle.tiny.path}")
  private String noCycleTinyGPath;
  @Value("${graph.undirected.data.bipartite.tiny.path}")
  private String tinyBipartiteGPath;
  @Value("${graph.undirected.data.routes.path}")
  private String routesPath;
  @Value("${graph.undirected.data.routes.delimiter.path}")
  private String delimiter;
  //movies
  @Value("${graph.undirected.data.movies.path}")
  private String moviesPath;
  @Value("${graph.undirected.data.movies.delimiter.path}")
  private String moviesDelim;

  @Autowired
  private UndirectedGraphFactory undirectedGraphFactory;

  @Bean
  public Graph tinyGraph() {
    return undirectedGraphFactory.loadGraph(newUndirectedGraphImpl(), tinyGPath);
  }

  @Bean
  public Graph noCycleTinyGraph() {
    return undirectedGraphFactory.loadGraph(newUndirectedGraphImpl(), noCycleTinyGPath);
  }

  private UndirectedGraphImpl newUndirectedGraphImpl() {
    return new UndirectedGraphImpl();
  }

  @Bean
  public Graph tinyBipartiteGraph() {
    return undirectedGraphFactory.loadGraph(newUndirectedGraphImpl(), tinyBipartiteGPath);
  }

  @Bean
  @Scope(scopeName = "prototype")
  public SymbolGraph routesSymbolGraph() {
    return createMapSymbolGraph(createEmptyGraph(), routesPath, delimiter);
  }

  @Bean
  public DegreesOfSeparation airportDegreesOfSeparation() {
    DegreesOfSeparation degreesOfSeparation = new DegreesOfSeparation();
    degreesOfSeparation.setSymbolGraph(routesSymbolGraph());
    degreesOfSeparation.setSourceVertex("JFK");
    return degreesOfSeparation;
  }

  @Bean
  public SymbolGraph moviesSymbolGraph() {
    return createMapSymbolGraph(createEmptyGraph(), moviesPath, moviesDelim);
  }

  private Graph createEmptyGraph() {
    return new UndirectedGraphImpl();
  }

  private SymbolGraph createMapSymbolGraph(Graph graph, String path, String delimiter) {
    MapSymbolGraph symbolGraph = new MapSymbolGraph();
    symbolGraph.setGraph(graph);
    symbolGraph.setFilePath(path);
    delimiter = delimiter.replace("\"", "");
    symbolGraph.setDelimiter(delimiter);
    symbolGraph.init();
    return symbolGraph;
  }
}
