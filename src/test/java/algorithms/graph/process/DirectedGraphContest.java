package algorithms.graph.process;

import algorithms.graph.Digraph;
import algorithms.graph.DigraphImpl;
import algorithms.graph.EdgeWeightedGraph;
import algorithms.graph.EdgeWeightedGraphFactory;
import algorithms.graph.EdgeWeightedGraphImpl;
import algorithms.graph.GraphFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DirectedGraphContest {

  @Value("${graph.directed.data.tiny.path}")
  private String tinyGPath;
  @Value("${graph.directed.data.no_cycle.tiny.path}")
  private String noCycleTinyGPath;
  @Value("${graph.directed.data.dag.tiny.path}")
  private String tinyDAGPath;
  @Value("${graph.directed.data.edge.weighted.tiny.path}")
  private String tinyEDGPath;
  @Autowired
  private GraphFactory graphFactory;

  @Bean
  public Digraph tinyDirectedCyclicGraph() {
    return graphFactory.loadGraph(newDigraphImpl(), tinyGPath);
  }

  @Bean
  @Scope(scopeName = "prototype")
  public Digraph tinyDAG() {
    return graphFactory.loadGraph(new DigraphImpl(), tinyDAGPath);
  }

  @Bean
  @Scope(scopeName = "prototype")
  public DepthFirstDirectedCycleDetection depthFirstDirectedCycleDetection() {
    return new DepthFirstDirectedCycleDetection();
  }

  @Bean
  @Scope(scopeName = "prototype")
  public Topological topological() {
    Topological topological = new Topological();
    topological.setDirectedCycleDetection(depthFirstDirectedCycleDetection());
    topological.setDigraph(tinyDAG());
    topological.init();
    return topological;
  }

  @Bean
  public EdgeWeightedGraphFactory edgeWeightedGraphFactory() {
    return new EdgeWeightedGraphFactory();
  }

  @Bean
  @Scope(scopeName = "prototype")
  public EdgeWeightedGraph tinyEDG() {
    EdgeWeightedGraphFactory factory = edgeWeightedGraphFactory();
    return factory.loadGraph(new EdgeWeightedGraphImpl(), tinyEDGPath);
  }

  @Bean

  public Digraph tinyDirectedAcyclicGraph() {
    return graphFactory.loadGraph(newDigraphImpl(), noCycleTinyGPath);
  }

  private DigraphImpl newDigraphImpl() {
    return new DigraphImpl();
  }
}
