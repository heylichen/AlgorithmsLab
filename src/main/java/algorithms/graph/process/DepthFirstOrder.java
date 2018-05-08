package algorithms.graph.process;

import java.util.BitSet;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.graph.Digraph;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/5/8.
 * different iterate orders for digraph based on depthFirstSearch
 */
@Getter
@Setter
public class DepthFirstOrder {

  private BitSet marked;
  private Queue<Integer> pre;
  private Queue<Integer> post;
  private Deque<Integer> reversePost;

  public DepthFirstOrder(Digraph digraph) {
    pre = new LinkedList<>();
    post = new LinkedList<>();
    reversePost = new LinkedList<>();

    marked = new BitSet(digraph.verticesCount());
    init(digraph);
  }

  private void init(Digraph digraph) {
    for (Integer v : digraph.getVertices()) {
      if (!marked.get(v)) {
        dfs(digraph, v);
      }
    }
  }

  private void dfs(Digraph digraph, int v) {
    marked.set(v);
    pre.add(v);
    for (Integer adjacentVertex : digraph.adjacentVertices(v)) {
      if (!marked.get(adjacentVertex)) {
        dfs(digraph, adjacentVertex);
      }
    }
    post.add(v);
    reversePost.push(v);
  }
}
