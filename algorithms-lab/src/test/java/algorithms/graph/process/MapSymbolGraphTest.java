package algorithms.graph.process;

import javax.annotation.Resource;

import lombok.Getter;

/**
 * Created by Chen Li on 2018/5/5.
 */
public class MapSymbolGraphTest extends SymbolGraphTest {

  @Getter
  @Resource(name = "routesSymbolGraph")
  private SymbolGraph symbolGraph;
  @Getter
  @Resource(name = "moviesSymbolGraph")
  private SymbolGraph moviesSymbolGraph;

}
