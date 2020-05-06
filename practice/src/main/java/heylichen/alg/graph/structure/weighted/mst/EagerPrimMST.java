package heylichen.alg.graph.structure.weighted.mst;

import heylichen.alg.graph.structure.weighted.EdgeWeightedGraph;
import heylichen.alg.graph.structure.weighted.WeightedEdge;
import heylichen.sort.pq.IndexedPriorityQueue;
import heylichen.sort.pq.MinDoublePriorityComparator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EagerPrimMST implements MST {
    private EdgeWeightedGraph graph;
    private Double distTo[];
    private boolean marked[];
    private List<WeightedEdge> edges;
    private double totalWeight;

    public EagerPrimMST(EdgeWeightedGraph graph) {
        this.graph = graph;
        init();
    }

    private void init() {
        int vCount = graph.getVertexCount();
        IndexedPriorityQueue<Double> indexedPq = new IndexedPriorityQueue<>(new MinDoublePriorityComparator(), vCount);
        distTo = new Double[vCount];
        WeightedEdge[] edgeTo = new WeightedEdge[vCount];
        marked = new boolean[vCount];
        for (int i = 0; i < vCount; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        indexedPq.insert(0, 0d);
        while (!indexedPq.isEmpty()) {
            int v = indexedPq.delMin();
            marked[v] = true;
            for (WeightedEdge edge : graph.getAdjacent(v)) {
                int w = edge.theOther(v);
                if (marked[w]) {
                    continue;
                }
                if (indexedPq.contains(w)) {
                    if (edge.getWeight().compareTo(distTo[w]) < 0) {
                        indexedPq.change(w, edge.getWeight());
                        distTo[w] = edge.getWeight();
                        edgeTo[w] = edge;
                    }
                } else {
                    indexedPq.insert(w, edge.getWeight());
                    distTo[w] = edge.getWeight();
                    edgeTo[w] = edge;
                }
            }
        }

        initEdges(edgeTo);
    }

    private void initEdges(WeightedEdge[] edgeTo) {
        edges = new ArrayList<>(edgeTo.length - 1);
        BigDecimal totalWeightBd = BigDecimal.ZERO;
        for (WeightedEdge edge : edgeTo) {
            if (edge != null) {
                totalWeightBd = totalWeightBd.add(BigDecimal.valueOf(edge.getWeight()));
                edges.add(edge);
            }
        }
        this.totalWeight = totalWeightBd.doubleValue();
    }

    @Override
    public Iterable<WeightedEdge> edges() {
        return edges;
    }

    @Override
    public double weight() {
        return totalWeight;
    }
}
