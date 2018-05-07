package algorithms.graph.process;

import algorithms.graph.DigraphImpl;
import algorithms.graph.Graph;
import algorithms.graph.UndirectedGraphFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectedGraphContest {

  @Value("${graph.directed.data.tiny.path}")
  private String tinyGPath;
  @Value("${graph.directed.data.no_cycle.tiny.path}")
  private String noCycleTinyGPath;
  @Autowired
  private UndirectedGraphFactory undirectedGraphFactory;

  @Bean
  public Graph tinyDirectedCyclicGraph() {
    return undirectedGraphFactory.loadGraph(newDigraphImpl(), tinyGPath);
  }

  @Bean
  public Graph tinyDirectedAcyclicGraph() {
    return undirectedGraphFactory.loadGraph(newDigraphImpl(), noCycleTinyGPath);
  }

  private DigraphImpl newDigraphImpl() {
    return new DigraphImpl();
  }
}
