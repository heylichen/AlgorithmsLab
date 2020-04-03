package heylichen.alg.graph;

import heylichen.alg.graph.structure.ClasspathGraphSource;
import heylichen.alg.graph.structure.Edge;
import heylichen.alg.graph.structure.GraphSource;
import heylichen.alg.graph.structure.UndirectedGraph;
import org.junit.Test;

public class UndirectedGraphTest {

    @Test
    public void name() {
        GraphSource<Edge> gs = new ClasspathGraphSource("/alg/graph/tinyG.txt");
        UndirectedGraph ug = new UndirectedGraph(gs);
        System.out.println();
    }
}
