package algorithms.graph.practice;

import java.util.BitSet;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.graph.Digraph;

public class DepthFirstOrder {

  private BitSet marked;
  private Queue<Integer> pre;
  private Queue<Integer> post;
  private Deque<Integer> stack;

  public void init(Digraph digraph) {
    int vCount = digraph.verticesCount();
    marked = new BitSet(vCount);
    pre = new LinkedList<>();
    post = new LinkedList<>();
    stack = new LinkedList<>();

    for (Integer vertex : digraph.getVertices()) {
      if (!marked.get(vertex)) {
        visit(digraph, vertex);
      }
    }
  }

  private void visit(Digraph digraph, Integer v) {
    marked.set(v);
    pre.add(v);
    for (Integer w : digraph.adjacentVertices(v)) {
      if (!marked.get(w)) {
        visit(digraph, w);
      }
    }
    post.add(v);
    stack.push(v);
  }

  public Iterable<Integer> pre() {
    return pre;
  }

  public Iterable<Integer> post() {
    return post;
  }

  public Iterable<Integer> reversePost() {
    return stack;
  }
}
