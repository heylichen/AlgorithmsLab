package algorithms.graph.practice;

import algorithms.graph.Digraph;
import algorithms.graph.GraphFactory;
import algorithms.graph.PathLogUtil;
import algorithms.graph.process.TopologicalSort;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public abstract class AbstractTopoSortTest {

  protected abstract TopologicalSort getInstance();

  @Test
  public void testSort() {
    Digraph digraph = new DigraphImpl();
    GraphFactory gf = new GraphFactory();
    gf.loadGraph(digraph, "algorithms/graph/directed/orderDigraph.txt");

    TopologicalSort topologicalSort = getInstance();
    topologicalSort.init(digraph);

    log.info("is DAG {}, topo order : {}", topologicalSort.isDAG(),
             PathLogUtil.pathToString(topologicalSort.sort()));



  }

  @Test
  public void complicatedDAGTest() {
    Digraph digraph = new DigraphImpl();
    GraphFactory gf = new GraphFactory();
    gf.loadGraph(digraph, "algorithms/graph/directed/toposort.txt");

    TopologicalSort topologicalSort = getInstance();
    topologicalSort.init(digraph);

    log.info("is DAG {}, topo order : {}", topologicalSort.isDAG(),
             PathLogUtil.pathToString(topologicalSort.sort()));
  }


  @Test
  public void complicatedCycleTest() {
    Digraph digraph = new DigraphImpl();
    GraphFactory gf = new GraphFactory();
    gf.loadGraph(digraph, "algorithms/graph/directed/noTopoSort.txt");

    TopologicalSort topologicalSort = getInstance();
    topologicalSort.init(digraph);

    log.info("is DAG {}, topo order : {}", topologicalSort.isDAG(),
             PathLogUtil.pathToString(topologicalSort.sort()));
  }
}
