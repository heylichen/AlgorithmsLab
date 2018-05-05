package algorithms.graph.process;

import javax.annotation.Resource;

import lombok.Getter;

/**
 * Created by Chen Li on 2018/4/30.
 */
public class DepthFirstSearchImplTest extends SearchTest {
  @Getter
  @Resource(name="depthFirstSearch")
  private Search search;
}
