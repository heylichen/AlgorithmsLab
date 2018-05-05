package algorithms.graph.process;

import javax.annotation.Resource;

import algorithms.graph.Graph;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Chen Li on 2018/5/5.
 */
public class BreadthFirstPathsTest extends PathsTest {

  @Getter
  @Resource(name = "breadthFirstPaths")
  private Paths paths;
  @Getter
  @Resource(name = "tinyGraph")
  private Graph graph;
  @Getter
  @Value("${graph.undirected.source.vertex.0}")
  private int sourceVertex;

}
