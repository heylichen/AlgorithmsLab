package heylichen.alg.graph.tasks.path;

import heylichen.alg.graph.structure.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DFSMultipleSourceReachability implements MultipleSourceReachability {
    private Graph graph;
    private Collection<Integer> sourceVertices;
    private boolean[] marked;
    private List<Integer> reachableVertices;

    public DFSMultipleSourceReachability(Graph graph, Collection<Integer> sourceVertices) {
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
    public Collection<Integer> getReachableVertices() {
        return reachableVertices;
    }
}
