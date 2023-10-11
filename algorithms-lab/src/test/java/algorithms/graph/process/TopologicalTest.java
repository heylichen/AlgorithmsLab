package algorithms.graph.process;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import algorithms.context.AbstractContextTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/5/8.
 */
@Slf4j
public class TopologicalTest extends AbstractContextTest {

  @Resource(name = "topological")
  private Topological topological;

  @Test
  public void topologicalSort() {
    List<Integer> list = new ArrayList<>();
    for (Integer v : topological.getOrder()) {
      list.add(v);
    }
    log.info("{}", list);

  }
}