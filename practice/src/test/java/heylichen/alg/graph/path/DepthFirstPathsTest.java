package heylichen.alg.graph.path;

import heylichen.alg.graph.structure.Graph;
import heylichen.alg.graph.tasks.path.DepthFirstPaths;
import heylichen.alg.graph.tasks.path.SingleSourcePaths;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DepthFirstPathsTest extends AbstractSingleSourcePathsTest {

    @Override
    protected SingleSourcePaths createPaths(Graph graph, int source) {
        return new DepthFirstPaths(graph, source);
    }
}