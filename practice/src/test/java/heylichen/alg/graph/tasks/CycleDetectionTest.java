package heylichen.alg.graph.tasks;

import heylichen.alg.graph.structure.Graph;
import heylichen.alg.graph.path.PathUtils;
import heylichen.test.AppTestContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CycleDetectionTest extends AppTestContext  {
    @Autowired
    private Graph tinyUndirectedGraph;

    @Test
    public void name() {
        CycleDetection cd = new CycleDetection(tinyUndirectedGraph);
        System.out.println(cd.hasCycle());
        System.out.println(PathUtils.viewPath(cd.getCyclePath()));
    }
}