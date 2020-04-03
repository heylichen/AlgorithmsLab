package heylichen.alg.graph.tasks;

import heylichen.alg.graph.structure.Graph;

public class TwoColor {

    private boolean twoColorable;
    private boolean color[];
    private boolean marked[];

    public TwoColor(Graph graph) {
        int vertexCount = graph.getVertexCount();
        marked = new boolean[vertexCount];
        color = new boolean[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            if (marked[i]) {
                continue;
            }

            dfs(graph, i, true);
            if (!this.twoColorable) {
                return;
            }
        }
    }

    private void dfs(Graph graph, int v, boolean red) {
        marked[v] = true;
        color[v] = red;

        for (Integer adjacentVertex : graph.getAdjacentVertices(v)) {
            if (!marked[adjacentVertex]) {
                dfs(graph, adjacentVertex, !red);
                if (!this.twoColorable) {
                    return;
                }
                continue;
            }
            if (color[adjacentVertex] == red) {
                this.twoColorable = false;
                return;
            }
        }
    }

    public boolean isBipartite() {
        return twoColorable;
    }
}
