package heylichen.alg.graph.path;

import heylichen.alg.graph.structure.weighted.WeightedEdge;

public class PathUtils {

    public static final String viewPath(Iterable<Integer> path) {
        StringBuilder sb = new StringBuilder();
        for (Integer integer : path) {
            sb.append(integer).append(" ");
        }
        return sb.toString();
    }

    public static final String viewEdges(Iterable<WeightedEdge> edges) {
        StringBuilder sb = new StringBuilder("\n ");
        for (WeightedEdge edge : edges) {
            Integer v = edge.either();
            sb.append(v).append(' ').append(edge.theOther(v)).append(' ').append(edge.getWeight()).append(" \n ");
        }
        return sb.toString();
    }
}
