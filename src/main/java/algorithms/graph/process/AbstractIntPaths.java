package algorithms.graph.process;

import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Collections;
import java.util.Deque;

import algorithms.graph.Graph;

/**
 * Created by Chen Li on 2018/5/5.
 */
public abstract class AbstractIntPaths implements Paths {

  protected BitSet bitSet;
  protected int connectedVerticesCount;
  protected int[] edgesTo;

  protected int sourceVertex;
  protected Graph graph;

  @Override
  public final boolean hasPathTo(int v) {
    return bitSet.get(v);
  }

  @Override
  public final Iterable<Integer> pathTo(int v) {

    if (!hasPathTo(v)) {
      return Collections.emptyList();
    }
    Deque<Integer> stack = new ArrayDeque<>();
    while (v != sourceVertex) {
      stack.push(v);
      v = edgesTo[v];
    }
    stack.push(v);
    return stack;
  }

  @Override
  public int from() {
    return sourceVertex;
  }
}
