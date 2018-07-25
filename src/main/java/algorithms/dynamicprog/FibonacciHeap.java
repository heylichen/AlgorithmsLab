package algorithms.dynamicprog;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lichen2@tuniu.com
 * @Date 2018/7/25 13:24
 * @Description
 */
public class FibonacciHeap<K extends Comparable<K>> {

  private int size = 0;
  private FibonacciHeapNode<K> rootTree;
  private FibonacciHeapNode<K> minRoot;


  public void insert(K key) {
    if (key == null) {
      return;
    }
    FibonacciHeapNode<K> node = new FibonacciHeapNode<>(key);
    this.size++;

    if (minRoot == null) {
      rootTree = node;
      this.minRoot = node;
      return;
    }

    rootTree.merge(node);

    if (node.compareTo(minRoot) < 0) {
      minRoot = node;
    }
  }

  public boolean isEmpty() {
    return size <= 0;
  }

  public FibonacciHeap merge(FibonacciHeap another) {
    if (another == null || another.isEmpty()) {
      return this;
    }
    FibonacciHeap h = new FibonacciHeap();
    h.size = this.size + another.size;
    h.rootTree = this.rootTree;
    h.rootTree.merge(another.rootTree);
    if (this.minRoot.compareTo(another.minRoot) < 0) {
      h.minRoot = this.minRoot;
    } else {
      h.minRoot = another.minRoot;
    }
    return h;
  }

  public FibonacciHeapNode<K> deleteMin() {
    FibonacciHeapNode result = minRoot;
    if (result == null) {
      return result;
    }
    FibonacciHeapNode<K> child = result.getChild();
    if (child != null) {
      for (FibonacciHeapNode fibonacciHeapNode : child.siblings()) {
        rootTree.merge(fibonacciHeapNode);
      }
    }
    result.deleteMySelf();
    size--;
    if (size == 0) {
      minRoot = null;
      return result;
    }
    rootTree = result.getLeft();
    consolidate();
    return result;
  }

  private void consolidate() {
    List<FibonacciHeapNode> a = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      a.add(null);
    }
    int degree;
    FibonacciHeapNode<K> current = rootTree;
    for (FibonacciHeapNode rootNode : current.siblings()) {
      FibonacciHeapNode x = rootNode;
      degree = x.getDegree();

      while (a.get(degree) != null) {
        FibonacciHeapNode y = a.get(degree);
        if (x.compareTo(y) > 0) {
          FibonacciHeapNode t = x;
          x = y;
          y = t;
        }
        link(y, x);
        a.set(degree, null);
        degree++;
      }

      a.set(degree, x);
    }

    minRoot = null;
    for (FibonacciHeapNode node : a) {
      if (node == null) {
        continue;
      }
      if (minRoot == null) {
        minRoot = node;
        continue;
      }
      if (node.compareTo(minRoot) < 0) {
        minRoot = node;
      }
    }
    rootTree = minRoot;
  }

  private void link(FibonacciHeapNode y, FibonacciHeapNode x) {
    y.deleteMySelf();
    y.setLeft(y);
    y.setRight(y);
    if (x.getChild() == null) {
      x.setChild(y);
      x.setDegree(1);
    } else {
      x.getChild().merge(y);
      x.setDegree(x.getDegree() + 1);
    }
    y.setMarked(false);
  }
}
