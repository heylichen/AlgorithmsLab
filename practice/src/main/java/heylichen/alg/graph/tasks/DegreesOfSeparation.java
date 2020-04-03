package heylichen.alg.graph.tasks;

import heylichen.alg.graph.structure.SymbolGraph;
import heylichen.alg.graph.tasks.path.BreadthFirstPaths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DegreesOfSeparation {
    private SymbolGraph graph;
    private String source;
    private BreadthFirstPaths breadthFirstPaths;

    public DegreesOfSeparation(SymbolGraph graph, String source) {
        this.graph = graph;
        this.source = source;
        if (!graph.contains(source)) {
            return;
        }
        int sourceVertex = graph.index(source);
        breadthFirstPaths = new BreadthFirstPaths(graph.getGraph(), sourceVertex);
    }

    public Iterable<String> shortestPathTo(String target) {
        if (!graph.contains(target)) {
            return Collections.emptyList();
        }
        int targetVertex = graph.index(target);
        Iterable<Integer> shortestPaths = breadthFirstPaths.pathTo(targetVertex);
        List<String> paths = new ArrayList<>();
        for (Integer v : shortestPaths) {
            paths.add(graph.name(v));
        }
        return paths;
    }
}
