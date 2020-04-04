package heylichen.alg.graph.tasks;

import heylichen.alg.graph.path.PathUtils;
import heylichen.alg.graph.structure.directed.Digraph;
import heylichen.test.AppTestContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class TopologicalTest extends AppTestContext {
    @Autowired
    private Digraph tinyDAG;

    @Test
    public void testTopologicalOrder() {
        testDAG(tinyDAG);
    }

    private void testDAG(Digraph tinyDAG) {
        Topological topological = new Topological(tinyDAG);
        viewDag(topological);
    }

    private void viewDag(Topological topological) {
        if (!topological.isDAG()) {
            log.info("not a DAG");
            return;
        }

        String path = PathUtils.viewPath(topological.order());
        log.info("this DAG has topological order: {}", path);
    }
}