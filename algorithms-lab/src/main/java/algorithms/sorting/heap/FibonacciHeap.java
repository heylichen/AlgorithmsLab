package algorithms.sorting.heap;

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
  private K infinityMin;

  public FibonacciHeap(K infinityMin) {
    this.infinityMin = infinityMin;
  }

  public FibonacciHeapNode<K> insert(K key) {
    if (key == null) {
      return null;
    }
    FibonacciHeapNode<K> node = new FibonacciHeapNode<>(key);
    this.size++;

    if (minRoot == null) {
      rootTree = node;
      this.minRoot = node;
      return node;
    }

    rootTree.merge(node);

    if (node.compareTo(minRoot) < 0) {
      minRoot = node;
    }
    return node;
  }

  public boolean isEmpty() {
    return size <= 0;
  }

  public FibonacciHeap merge(FibonacciHeap another) {
    if (another == null || another.isEmpty()) {
      return this;
    }
    FibonacciHeap h = new FibonacciHeap(this.infinityMin);
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

  public void decreaseKey(FibonacciHeapNode<K> x, K toKey) {
    int delta = toKey.compareTo(x.getKey());
    if (toKey == null || delta == 0) {
      return;
    }
    if (delta > 0) {
      throw new IllegalArgumentException("toKey must not be greater than key");
    }
    x.setKey(toKey);
    FibonacciHeapNode<K> y = x.getParent();
    if (y != null && x.compareTo(y) < 0) {
      cut(x, y);
      cascadeCut(y);
    }
    if (x.compareTo(minRoot) < 0) {
      this.minRoot = x;
    }
  }

  public void deleteNode(FibonacciHeapNode<K> x) {
    decreaseKey(x, this.infinityMin);
    deleteMin();
  }

  /**
   * remove x from the child list of y, decrementing y:degree
   * add x to the root list of H
   */
  private void cut(FibonacciHeapNode<K> x, FibonacciHeapNode<K> y) {
    y.setChild(x.getLeft());
    x.deleteMySelf();
    x.setLeft(x);
    x.setRight(x);
    x.setParent(null);
    x.setMarked(false);
    rootTree.merge(x);
    y.addDegree(-1);
  }

  private void cascadeCut(FibonacciHeapNode<K> y) {
    FibonacciHeapNode<K> z = y.getParent();
    if (z == null) {
      return;
    }
    if (!y.isMarked()) {
      y.setMarked(true);
    } else {
      cut(y, z);
      cascadeCut(z);
    }
  }


  public FibonacciHeapNode<K> deleteMin() {
    FibonacciHeapNode result = minRoot;
    if (result == null) {
      return result;
    }
    FibonacciHeapNode<K> child = result.getChild();
    if (child != null) {
      for (FibonacciHeapNode fibonacciHeapNode : child.siblings()) {
        fibonacciHeapNode.setParent(null);
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
    y.setParent(x);
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
