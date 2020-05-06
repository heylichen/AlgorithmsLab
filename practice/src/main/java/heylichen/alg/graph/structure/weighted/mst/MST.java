package heylichen.alg.graph.structure.weighted.mst;

import heylichen.alg.graph.structure.weighted.WeightedEdge;

/**
 * @author lichen
 * @date 2020/4/8 15:41
 * @desc
 */
public interface MST {

    Iterable<WeightedEdge> edges();

    double weight();
}
