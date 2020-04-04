package heylichen.alg.graph.tasks;

import heylichen.alg.graph.structure.directed.Digraph;

public class Topological {
    private DirectedCycle directedCycle;
    private DepthFirstOrder order;

    public Topological(Digraph digraph) {
        this.directedCycle = new DirectedCycle(digraph);
        this.order = new DepthFirstOrder(digraph);
    }

    public boolean isDAG() {
        return !directedCycle.hasCycle();
    }

    public Iterable<Integer> order() {
        if (!isDAG()) {
            throw new UnsupportedOperationException("Not a DAG, no topological order!");
        }
        return order.getReversePost();
    }
}
