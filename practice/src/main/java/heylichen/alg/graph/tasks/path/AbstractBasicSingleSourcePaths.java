package heylichen.alg.graph.tasks.path;

import heylichen.alg.graph.structure.Graph;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public abstract class AbstractBasicSingleSourcePaths implements SingleSourcePaths {
    protected final boolean marked[];
    protected final int edgeTo[];
    protected final int source;

    public AbstractBasicSingleSourcePaths(Graph graph, int sourceV) {
        source = sourceV;
        marked = new boolean[graph.getVertexCount()];
        edgeTo = new int[graph.getVertexCount()];
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = -1;
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!marked[v]) {
            return Collections.emptyList();
        }
        Deque<Integer> stack = new LinkedList<>();
        int currentV = v;
        do {
            stack.push(currentV);
            currentV = edgeTo[currentV];
        } while (currentV != source);
        stack.push(currentV);
        return stack;
    }

    @Override
    public int getSourceVertex() {
        return source;
    }
}
