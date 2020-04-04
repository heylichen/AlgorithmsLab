package heylichen.alg.graph.tasks;

import heylichen.alg.graph.structure.Graph;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class DepthFirstOrder {
    private Graph graph;
    private boolean marked[];
    @Getter
    private Queue<Integer> pre;
    @Getter
    private Queue<Integer> post;
    @Getter
    private Deque<Integer> reversePost;

    public DepthFirstOrder(Graph graph) {
        this.graph = graph;

        int vCount = graph.getVertexCount();
        marked = new boolean[vCount];
        pre = new ArrayDeque<>(vCount);
        post = new ArrayDeque<>(vCount);
        reversePost = new ArrayDeque<>(vCount);

        for (int i = 0; i < vCount; i++) {
            if (!marked[i]) {
                dfs(graph, i);
            }
        }
    }

    private void dfs(Graph graph, int vertex) {
        marked[vertex] = true;
        pre.add(vertex);

        for (Integer adjacentVertex : graph.getAdjacentVertices(vertex)) {
            if (!marked[adjacentVertex]) {
                dfs(graph, adjacentVertex);
            }
        }

        post.add(vertex);
        reversePost.push(vertex);
    }
}
