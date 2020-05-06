package heylichen.alg.graph.tasks.shortestpath;

import heylichen.alg.graph.structure.directed.EdgeWeightedDigraph;
import heylichen.test.AppTestContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleSPTest extends AppTestContext {
    @Autowired
    private EdgeWeightedDigraph tinyEWD;

    @Test
    public void name() {
        SimpleSP simpleSP = new SimpleSP(tinyEWD, 0);
        simpleSP.init();
        System.out.println(simpleSP.distTo(6));
    }
}