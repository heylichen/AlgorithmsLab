package heylichen.alg.graph.tasks.path;

import heylichen.alg.graph.structure.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DFSMultipleSourceReachability implements MultipleSourceReachability {
    private Graph graph;
    private Set<Integer> sourceVertices;
    private boolean[] marked;
    private List<Integer> reachableVertices;

    public DFSMultipleSourceReachability(Graph graph, Set<Integer> sourceVertices) {
        this.graph = graph;
        this.sourceVertices = sourceVertices;
        init();
    }

    private void init() {
        marked = new boolean[graph.getVertexCount()];
        reachableVertices = new ArrayList<>(graph.getVertexCount());
        for (Integer sourceVertex : sourceVertices) {
            if (!marked[sourceVertex]) {
                dfs(graph, sourceVertex);
            }
        }
    }

    private void dfs(Graph graph, int source) {
        marked[source] = true;
        reachableVertices.add(source);
        for (Integer adjacentVertex : graph.getAdjacentVertices(source)) {
            if (marked[adjacentVertex]) {
                continue;
            }
            dfs(graph, adjacentVertex);
        }
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> getReachableVertices() {
        return reachableVertices;
    }
}
