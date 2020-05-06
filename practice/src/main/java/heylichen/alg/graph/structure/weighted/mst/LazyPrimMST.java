package heylichen.alg.graph.structure.weighted.mst;

import heylichen.alg.graph.structure.weighted.EdgeWeightedGraph;
import heylichen.alg.graph.structure.weighted.WeightedEdge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author lichen
 * @date 2020/4/8 15:43
 * @desc
 */
public class LazyPrimMST implements MST {
    private EdgeWeightedGraph graph;
    private List<WeightedEdge> edges;
    private boolean marked[];
    private static final int START_VERTEX = 0;
    private double weight;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        this.graph = graph;
        init();
    }

    private void init() {
        edges = new ArrayList<>();
        marked = new boolean[graph.getVertexCount()];
        PriorityQueue<WeightedEdge> weightedEdgesPQ = new PriorityQueue<>();
        visitV(weightedEdgesPQ, START_VERTEX);

        while (!weightedEdgesPQ.isEmpty()) {
            WeightedEdge edge = weightedEdgesPQ.poll();
            Integer unmarkedV = getUnmarkedVertex(edge);
            if (unmarkedV == null) {
                continue;
            }
            edges.add(edge);
            visitV(weightedEdgesPQ, unmarkedV);
        }
        calculateTotalWeight();
    }

    private void visitV(PriorityQueue<WeightedEdge> weightedEdgesPQ, int v) {
        marked[v] = true;
        for (WeightedEdge weightedEdge : graph.getAdjacent(v)) {
            Integer theOtherV = weightedEdge.theOther(v);
            if (!marked[theOtherV]) {
                weightedEdgesPQ.add(weightedEdge);
            }
        }
    }

    private void calculateTotalWeight() {
        double totalWeight = 0d;
        for (WeightedEdge edge : edges) {
            totalWeight += edge.getWeight();
        }
        this.weight = totalWeight;
    }

    private Integer getUnmarkedVertex(WeightedEdge edge) {
        Integer v = edge.either();
        if (!marked[v]) {
            return v;
        }
        v = edge.theOther(v);
        if (!marked[v]) {
            return v;
        } else {
            return null;
        }
    }

    @Override
    public Iterable<WeightedEdge> edges() {
        return edges;
    }

    @Override
    public double weight() {
        return weight;
    }
}
