package heylichen.alg.graph.tasks.path;

import heylichen.alg.graph.structure.Graph;

import java.util.Deque;
import java.util.LinkedList;

public class BreadthFirstPaths extends AbstractBasicSingleSourcePaths {

    public BreadthFirstPaths(Graph graph, int sourceV) {
        super(graph, sourceV);
        bfs(graph, source);
    }

    private void bfs(Graph graph, int v) {
        Deque<Integer> queue = new LinkedList<>();
        marked[v] = true;
        queue.add(v);

        while (!queue.isEmpty()) {
            int currentVertex = queue.remove();
            for (Integer w : graph.getAdjacentVertices(currentVertex)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = currentVertex;
                    queue.add(w);
                }
            }
        }
    }

}
