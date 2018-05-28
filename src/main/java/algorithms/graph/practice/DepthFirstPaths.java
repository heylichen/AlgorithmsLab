package algorithms.graph.practice;

import java.util.BitSet;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import algorithms.graph.Graph;
import algorithms.graph.process.Paths;

/**
 * Created by Chen Li on 2018/5/25.
 */
public class DepthFirstPaths implements Paths {

  private BitSet marked;
  private int[] edgeTo;
  private int source;
  public static final Iterable<Integer> EMPTY = new Iterable<Integer>() {
    @Override
    public Iterator<Integer> iterator() {
      return new Iterator<Integer>() {
        @Override
        public boolean hasNext() {
          return false;
        }

        @Override
        public Integer next() {
          return null;
        }
      };
    }
  };

  @Override
  public void init(Graph graph, int sourceVertex) {
    int vCount = graph.verticesCount();
    marked = new BitSet(vCount);
    edgeTo = new int[vCount];

    this.source = sourceVertex;
    edgeTo[sourceVertex] = sourceVertex;

    visit(graph, sourceVertex);
  }

  private void visit(Graph graph, int vertex) {
    if (marked.get(vertex)) {
      return;
    }
    marked.set(vertex);
    for (Integer adjacentV : graph.adjacentVertices(vertex)) {
      if (marked.get(adjacentV)) {
        continue;
      }
      edgeTo[adjacentV] = vertex;
      visit(graph, adjacentV);
    }
  }


  @Override
  public boolean hasPathTo(int v) {
    return marked.get(v);
  }

  @Override
  public Iterable<Integer> pathTo(int v) {
    if (!hasPathTo(v)) {
      return EMPTY;
    }
    Deque<Integer> stack = new LinkedList<>();
    stack.push(v);
    int currentV = edgeTo[v];
    while (currentV != source) {
      stack.push(currentV);
      currentV = edgeTo[currentV];
    }
    stack.push(source);
    return stack;
  }

  @Override
  public int from() {
    return source;
  }
}
