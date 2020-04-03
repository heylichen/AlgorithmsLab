package heylichen.alg.graph.tasks;

import heylichen.alg.graph.structure.Graph;
import heylichen.test.AppTestContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TwoColorTest extends AppTestContext {
    @Autowired
    private Graph tinyUndirectedGraph;

    @Test
    public void name() {
        TwoColor tc = new TwoColor(tinyUndirectedGraph);
        System.out.println(tc.isBipartite());
    }
}