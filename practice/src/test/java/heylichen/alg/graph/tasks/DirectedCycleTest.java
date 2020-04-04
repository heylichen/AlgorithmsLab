package heylichen.alg.graph.tasks;

import heylichen.alg.graph.path.PathUtils;
import heylichen.alg.graph.structure.directed.Digraph;
import heylichen.test.AppTestContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DirectedCycleTest  extends AppTestContext {
    @Autowired
    private Digraph tinyDirectedGraph;

    @Test
    public void testDirectedCycle() {
        DirectedCycle directedCycle = new DirectedCycle(tinyDirectedGraph);
        System.out.println(directedCycle.hasCycle());
        if (directedCycle.hasCycle()) {
            String pathString = PathUtils.viewPath(directedCycle.cycle());
            System.out.println(pathString);
        }
    }
}