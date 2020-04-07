package heylichen.alg.graph.structure.weighted;

import lombok.Getter;

@Getter
public class WeightedEdge implements Comparable<WeightedEdge> {
    private final Integer v;
    private final Integer w;
    private final Double weight;

    public WeightedEdge(Integer v, Integer w, Double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public Integer either() {
        return v;
    }

    public Integer theOther(Integer vertex) {
        if (vertex.equals(v)) {
            return w;
        } else {
            return v;
        }
    }

    @Override
    public int compareTo(WeightedEdge o) {
        return this.weight.compareTo(o.getWeight());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(v).append(" ").append(w).append(" ").append(weight);
        return sb.toString();
    }
}
