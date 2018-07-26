package algorithms.sorting.heap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FibonacciHeapNode<K extends Comparable<K>> {

  //structure
  private int degree = 0;
  private boolean marked = false;
  private FibonacciHeapNode parent;
  private FibonacciHeapNode left;
  private FibonacciHeapNode right;
  private FibonacciHeapNode child;
  //data
  private K key;

  public FibonacciHeapNode(K key) {
    this.key = key;
    this.left = this;
    this.right = this;
    this.parent = null;
    this.child = null;
  }

  public boolean noSibling() {
    return left == this && right == this;
  }

  public void deleteMySelf() {
    if (noSibling()) {
      return;
    }
    FibonacciHeapNode siblingA = left;
    FibonacciHeapNode siblingB = right;
    relink(siblingA, siblingB);
    relink(siblingB, siblingA);
  }

  private void relink(FibonacciHeapNode siblingA, FibonacciHeapNode siblingB) {
    if (siblingA.left == this) {
      siblingA.left = siblingB;
    } else {
      siblingA.right = siblingB;
    }
  }

  public Collection<FibonacciHeapNode> siblings() {
    Map<Integer, FibonacciHeapNode> map = new HashMap<>();
    FibonacciHeapNode current = this;
    map.put(System.identityHashCode(current), current);
    Integer ref;
    FibonacciHeapNode tryNode = null;
    while (true) {
      tryNode = current.left;
      ref = System.identityHashCode(tryNode);
      if (map.containsKey(ref)) {
        tryNode = current.right;
        ref = System.identityHashCode(tryNode);
        if (map.containsKey(ref)) {
          break;
        }
      }
      map.put(ref, tryNode);
      current = tryNode;
    }
    return map.values();
  }

  public void merge(FibonacciHeapNode b) {
    if (b == null) {
      return;
    }
    FibonacciHeapNode a = this;
    boolean aNoSibling = a.noSibling();
    boolean bNoSibling = b.noSibling();
    if (aNoSibling && bNoSibling) {
      a.left = b;
      a.right = b;
      b.left = a;
      b.right = a;
      return;
    }
    if (!aNoSibling && !bNoSibling) {
      FibonacciHeapNode rightOfA = a.right;
      FibonacciHeapNode rightOfB = b.right;
      a.right = b;
      b.right = a;
      rightOfA.left = rightOfB;
      rightOfB.left = rightOfA;
      return;
    }

    if (aNoSibling && !bNoSibling) {
      FibonacciHeapNode t = a;
      a = b;
      b = t;
    }
    //a has sibling, b no sibling now
    FibonacciHeapNode rightOfA = a.right;
    a.right = b;
    rightOfA.left = b;
    b.left = a;
    b.right = rightOfA;
  }

  public void addDegree(int delta) {
    this.degree += delta;
  }

  public int compareTo(FibonacciHeapNode<K> another) {
    return this.key.compareTo(another.key);
  }
}