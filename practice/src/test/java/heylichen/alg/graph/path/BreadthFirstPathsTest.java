package heylichen.alg.graph.path;

import heylichen.alg.graph.structure.Graph;
import heylichen.alg.graph.tasks.path.BreadthFirstPaths;
import heylichen.alg.graph.tasks.path.SingleSourcePaths;

public class BreadthFirstPathsTest extends AbstractSingleSourcePathsTest {

    @Override
    protected SingleSourcePaths createPaths(Graph graph, int source) {
        return new BreadthFirstPaths(graph, source);
    }
}