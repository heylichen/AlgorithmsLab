package heylichen.alg.graph.structure.directed;

import lombok.Getter;

@Getter
public class DirectedEdge {
    private final int from;
    private final int to;
    private final double weight;

    public DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String toString() {
        return from + " -> " + to + " " + weight;
    }
}
