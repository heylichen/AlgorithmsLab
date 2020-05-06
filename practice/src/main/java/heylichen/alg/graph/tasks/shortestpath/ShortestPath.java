package heylichen.alg.graph.tasks.shortestpath;

import heylichen.alg.graph.structure.directed.DirectedEdge;

public interface ShortestPath {

    double distTo(int v);

    boolean hasPathTo(int v);

    Iterable<DirectedEdge> pathTo(int v);
}
