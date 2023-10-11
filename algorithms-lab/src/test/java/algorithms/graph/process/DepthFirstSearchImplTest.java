package algorithms.graph.process;

import javax.annotation.Resource;

import algorithms.graph.Graph;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Chen Li on 2018/4/30.
 */
public class DepthFirstSearchImplTest extends SearchTest {

  @Getter
  @Resource(name = "depthFirstSearch")
  private Search search;
  @Getter
  @Resource(name = "tinyGraph")
  private Graph graph;
  @Getter
  @Value("${graph.undirected.source.vertex.0}")
  private int sourceVertex;
}
