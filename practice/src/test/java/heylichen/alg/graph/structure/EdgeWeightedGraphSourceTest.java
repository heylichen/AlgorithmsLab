package heylichen.alg.graph.structure;

import heylichen.alg.graph.structure.weighted.EdgeWeightedGraph;
import org.junit.Test;

import java.io.IOException;

public class EdgeWeightedGraphSourceTest {
    @Test
    public void name() throws IOException {
        EdgeWeightedGraphSource gs = EdgeWeightedGraphSource.create("alg/graph/weighted/tinyEWG.txt");
        EdgeWeightedGraph g = new EdgeWeightedGraph(gs);
        System.out.println();
    }
}