package algorithms.graph.process;

import javax.annotation.Resource;

import algorithms.graph.Graph;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Chen Li on 2018/5/1.
 */
public class DepthFirstPathsTest extends PathsTest {

  @Getter
  @Resource(name = "depthFirstPaths")
  private Paths paths;
  @Getter
  @Resource(name = "tinyGraph")
  private Graph graph;
  @Getter
  @Value("${graph.undirected.source.vertex.0}")
  private int sourceVertex;
}