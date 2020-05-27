package heylichen.alg.graph.tasks.shortestpath;

import heylichen.alg.graph.structure.directed.EdgeWeightedDigraph;
import heylichen.test.AppTestContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleSPTest extends AppTestContext {
    @Autowired
    private EdgeWeightedDigraph tinyEWD;
    @Autowired
    private EdgeWeightedDigraph mediumEWD;
    @Autowired
    private EdgeWeightedDigraph ewd1000;

    @Test
    public void name() {
        SimpleSP simpleSP = new SimpleSP(tinyEWD, 0);
        simpleSP.init();
        System.out.println(simpleSP.distTo(6));
    }


    @Test
    public void testMedium() {
        IndexedPqSP simpleSP = new IndexedPqSP(mediumEWD, 0);
        simpleSP.init();
        System.out.println(simpleSP.distTo(6));
    }

    @Test
    public void testewd1000() {
        IndexedPqSP simpleSP = new IndexedPqSP(ewd1000, 2);
        simpleSP.init();
        System.out.println(simpleSP.distTo(6));
    }
}