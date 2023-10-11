package algorithms.graph.process;

import algorithms.graph.Graph;

/**
 * Created by Chen Li on 2018/5/5.
 * Support this query: Can the vertices of a given graph be assigned
 * one of two colors in such a way that no edge connects vertices of the same color ?
 * which is equivalent to this question: Is the graph bipartite ?
 */
public interface TwoColorability {

  void init(Graph graph);

  boolean isBipartite();
}
