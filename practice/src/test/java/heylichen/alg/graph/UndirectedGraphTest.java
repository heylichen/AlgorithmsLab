package heylichen.alg.graph;

import heylichen.alg.graph.structure.ClasspathGraphSource;
import heylichen.alg.graph.structure.UndirectedGraph;
import org.junit.Test;

import java.io.IOException;

public class UndirectedGraphTest {

    @Test
    public void name() throws IOException {
        UndirectedGraph ug = new UndirectedGraph(ClasspathGraphSource.create("/alg/graph/tinyG.txt"));
        System.out.println();
    }
}
