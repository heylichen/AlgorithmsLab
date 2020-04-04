package heylichen.alg.graph.tasks;

import heylichen.alg.graph.structure.directed.Digraph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DirectedCycle {
    private Digraph digraph;
    private boolean marked[];
    private boolean onStack[];
    private int edgeTo[];
    private Deque<Integer> cyclePath;
    private boolean cycleDetected;


    public DirectedCycle(Digraph digraph) {
        this.digraph = digraph;
        init();
    }

    private void init() {
        int vCount = digraph.getVertexCount();
        marked = new boolean[vCount];
        onStack = new boolean[vCount];
        initEdgeTo();
        cyclePath = new LinkedList<Integer>();
        for (int i = 0; i < digraph.getVertexCount(); i++) {
            if (!marked[i]) {
                dfs(digraph, i);
            }
            if (cycleDetected) {
                break;
            }
        }
    }

    private void initEdgeTo() {
        edgeTo = new int[digraph.getVertexCount()];
        for (int i = 0; i < digraph.getVertexCount(); i++) {
            edgeTo[i] = -1;
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        onStack[v] = true;

        for (Integer adjacentVertex : digraph.getAdjacentVertices(v)) {
            edgeTo[adjacentVertex] = v;
            if (onStack[adjacentVertex]) {
                buildCyclePath(adjacentVertex);
                cycleDetected = true;
                break;
            }
            if (!marked[adjacentVertex]) {
                dfs(digraph, adjacentVertex);
            }
            if (cycleDetected) {
                break;
            }
        }

        onStack[v] = false;
    }

    private void buildCyclePath(int source) {
        cyclePath.push(source);
        int currentV = edgeTo[source];

        while (currentV != -1 && currentV != source) {
            cyclePath.push(currentV);
            currentV = edgeTo[currentV];
        }
        if (currentV == source) {
            cyclePath.push(source);
        }
    }

    public boolean hasCycle() {
        return cycleDetected;
    }

    public Iterable<Integer> cycle() {
        return cyclePath;
    }
}
