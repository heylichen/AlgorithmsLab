package heylichen.alg.graph.tasks;

import heylichen.alg.graph.structure.Graph;

import java.util.Deque;
import java.util.LinkedList;

public class CycleDetection {
    private boolean cycle;
    private int edgeTo[];
    private boolean marked[];
    private int cycleEnd;

    public CycleDetection(Graph graph) {
        int vertexCount = graph.getVertexCount();
        marked = new boolean[vertexCount];
        edgeTo = new int[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            edgeTo[i] = -1;
        }
        cycle = false;
        for (int i = 0; i < vertexCount; i++) {
            if (marked[i]) {
                continue;
            }

            dfs(graph, i, i);
            if (cycle) {
                return;
            }
        }
    }


    private void dfs(Graph graph, int vertex, int from) {
        marked[vertex] = true;
        for (Integer adjacentVertex : graph.getAdjacentVertices(vertex)) {
            if (adjacentVertex == from) {
                continue;
            }
            edgeTo[adjacentVertex] = vertex;
            if (!marked[adjacentVertex]) {
                dfs(graph, adjacentVertex, vertex);
                if (cycle) {
                    return;
                }
            } else if (adjacentVertex != from) {
                cycleEnd = adjacentVertex;
                cycle = true;
                return;
            }
        }
    }

    public boolean hasCycle() {
        return cycle;
    }

    public Iterable<Integer> getCyclePath() {
        int v = cycleEnd;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(v);
        v = edgeTo[v];

        while (v != -1 && v != cycleEnd) {
            stack.push(v);
            v = edgeTo[v];
        }
        if (v != -1) {
            stack.push(v);
        }
        return stack;
    }

}
