package algorithms.sorting.heap;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/7/28.
 */
@Getter
@Setter
public class BinomialHeapNode<K extends Comparable<K>> {
  private BinomialHeapNode<K> parent;
  private BinomialHeapNode<K> leftChild;
  private BinomialHeapNode<K> rightSibling;
  private K key;
  private Integer degree;

  public BinomialHeapNode(K key) {
    this(null, null, null, key, 0);
  }

  public BinomialHeapNode(BinomialHeapNode<K> parent, BinomialHeapNode<K> leftChild,
                          BinomialHeapNode<K> rightSibling, K key, Integer degree) {
    this.parent = parent;
    this.leftChild = leftChild;
    this.rightSibling = rightSibling;
    this.key = key;
    this.degree = degree;
  }

  public int compareTo(BinomialHeapNode<K> another) {
    if (another == null) {
      throw new IllegalArgumentException("can not compare to null");
    }
    return this.key.compareTo(another.getKey());
  }

  public int compareDegreeTo(BinomialHeapNode<K> another) {
    if (another == null) {
      throw new IllegalArgumentException("can not compare to null");
    }
    return this.degree.compareTo(another.getDegree());
  }

  public boolean equalDegree(BinomialHeapNode<K> another) {
    return degree.equals(another.degree);
  }

  public void addDegree(int delta) {
    this.degree += delta;
  }
}
