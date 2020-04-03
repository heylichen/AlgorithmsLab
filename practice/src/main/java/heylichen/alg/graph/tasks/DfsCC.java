package heylichen.alg.graph.tasks;

import heylichen.alg.graph.structure.Graph;

public class DfsCC implements CC {
    private boolean marked[];
    private int count;
    private int id[];

    public DfsCC(Graph graph) {
        int verticesCount = graph.getVertexCount();
        marked = new boolean[verticesCount];
        count = 0;
        id = new int[verticesCount];
        findCC(graph);
    }

    private void findCC(Graph graph) {
        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (!marked[i]) {
                dfs(graph, i);
                count++;
            }
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for (Integer w : graph.getAdjacentVertices(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }

    @Override
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    @Override
    public int getComponentsCount() {
        return count;
    }

    @Override
    public int getComponentId(int v) {
        return id[v];
    }
}
