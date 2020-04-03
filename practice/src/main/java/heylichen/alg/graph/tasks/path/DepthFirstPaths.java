package heylichen.alg.graph.tasks.path;

import heylichen.alg.graph.structure.Graph;

public class DepthFirstPaths extends AbstractBasicSingleSourcePaths {

    public DepthFirstPaths(Graph graph, int sourceV) {
        super(graph, sourceV);
        dfs(graph, sourceV);
    }


    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (Integer w : graph.getAdjacentVertices(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

}
