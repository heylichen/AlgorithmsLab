package algorithms.graph.process;

import algorithms.graph.Graph;
import algorithms.graph.UndirectedGraphFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
  @Autowired
  private UndirectedGraphFactory undirectedGraphFactory;

  @Bean
  public Graph tinyGraph() {
    return undirectedGraphFactory.loadGraph(tinyGPath);
  }
}
