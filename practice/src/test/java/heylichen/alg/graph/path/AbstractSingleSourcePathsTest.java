package heylichen.alg.graph.path;

import heylichen.alg.graph.structure.Graph;
import heylichen.alg.graph.tasks.path.SingleSourcePaths;
import heylichen.test.AppTestContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractSingleSourcePathsTest extends AppTestContext {

    @Autowired
    private Graph tinyUndirectedGraph;

    @Test
    public void testSingleSourceConnectivity() {
        SingleSourcePaths paths = createPaths(tinyUndirectedGraph, 1);

        testToVertex(paths, 3, true);
        testToVertex(paths, 7, false);
        testToVertex(paths, 9, false);

        paths = createPaths(tinyUndirectedGraph, 10);
        testToVertex(paths, 12, true);
        testToVertex(paths, 6, false);
        testToVertex(paths, 8, false);

        paths = createPaths(tinyUndirectedGraph, 8);
        testToVertex(paths, 7, true);
        testToVertex(paths, 4, false);
        testToVertex(paths, 11, false);
    }

    protected abstract SingleSourcePaths createPaths(Graph graph, int source);

    private void testToVertex(SingleSourcePaths paths, int v, boolean expected) {
        int sourceV = paths.getSourceVertex();
        boolean hasPath = paths.hasPathTo(v);
        Assert.assertEquals("connected assertion failed", expected, hasPath);
        if (!hasPath) {
            log.info("source {} has no path to {}", sourceV, v);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Integer integer : paths.pathTo(v)) {
            sb.append(integer).append(" ");
        }
        log.info("source {} has path to {}, path: {}", sourceV, v, sb.toString());
    }
}
