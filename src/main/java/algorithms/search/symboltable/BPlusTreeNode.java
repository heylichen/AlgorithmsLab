package algorithms.search.symboltable;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Triple;

/**
 * Created by Chen Li on 2017/8/19.
 */
@Getter
@Setter
public class BPlusTreeNode<K extends Comparable<K>, V> {
  public static final BPlusTreeNode EMPTY_NODE = new BPlusTreeNode(0);

  private Object[] keys;
  private Object[] values;
  private BPlusTreeNode<K, V>[] children;
  private boolean leaf;
  private int size;
  private int t;
  private int maxSize;
  private int capacity;

  public BPlusTreeNode() {

  }

  public BPlusTreeNode(int t) {
    this.t = t;
    this.maxSize = Math.max(2 * t - 1, 0);
    this.capacity = maxSize + 1;
    this.keys = new Object[capacity];
    this.values = new Object[capacity];
    this.children = (BPlusTreeNode<K, V>[]) new BPlusTreeNode[capacity + 1];
    this.size = 0;
  }

  public void increaseSize(int count) {
    this.size = this.size + count;
  }

  public void decreaseSize(int count) {
    this.size = this.size - count;
  }

  public static final <K extends Comparable<K>, V> BPlusTreeNode<K, V> emptyNode() {
    return (BPlusTreeNode<K, V>) EMPTY_NODE;
  }

  public void deleteFromLeaf(int index) {

  }

  public void insertIntoLeaf(K key, V value) {
    int rank = rank(key);
    if (isKeyAt(key, rank)) {
      values[rank] = value;
      return;
    }
    shiftRight(rank);
    keys[rank] = key;
    values[rank] = value;
    size++;
  }

  public void insertIntoInternalNode(int at, K key, V value, BPlusTreeNode<K, V> leftChild, BPlusTreeNode<K, V> rightChild) {
    if (isKeyAt(key, at)) {
      values[at] = value;
      return;
    }
    shiftRight(at);
    keys[at] = key;
    values[at] = value;
    size++;
    //children
    children[at] = leftChild;
    children[at + 1] = rightChild;
  }

  public Triple<BPlusTreeNode<K, V>, K, BPlusTreeNode<K, V>> splitIfOverflow() {
    if (!isOverflow()) {
      throw new IllegalStateException("operation not supported when not overflow!");
    }
    int halfSize = size / 2;
    BPlusTreeNode<K, V> right = new BPlusTreeNode<>(t);
    K liftedKey = (K) keys[halfSize];
    if (isLeaf()) {
      System.arraycopy(this.keys, halfSize, right.getKeys(), 0, size - halfSize);

      right.setSize(size - halfSize);
      right.setLeaf(true);
      this.size = halfSize;
      return Triple.of(this, liftedKey, right);
    }
    int rightStart = halfSize + 1;
    System.arraycopy(this.keys, rightStart, right.getKeys(), 0, size - rightStart);
    System.arraycopy(this.children, rightStart, right.getChildren(), 0, size - rightStart + 1);
    right.setLeaf(this.isLeaf());
    right.setSize(size - halfSize - 1);
    this.setSize(halfSize);
    return Triple.of(this, liftedKey, right);
  }

  public void shiftLeft(int from) {
    int size = size();

    shiftLeftOnePosition(keys, from, size - 1);
    shiftLeftOnePosition(values, from, size - 1);
    if (getChildrenSize() >= 0) {
      shiftLeftOnePosition(children, from, size);
    }
  }

  public void shiftRight(int from) {
    int size = size();

    shiftRightOnePosition(keys, from, size - 1);
    shiftRightOnePosition(values, from, size - 1);
    shiftRightOnePosition(children, from + 1, size);
  }

  private void shiftRightOnePosition(Object[] arr, int startPos, int endPos) {
    for (int i = endPos + 1; i > startPos; i--) {
      arr[i] = arr[i - 1];
    }
  }

  private void shiftLeftOnePosition(Object[] arr, int startPos, int endPos) {
    for (int i = startPos; i <= endPos; i++) {
      arr[i - 1] = arr[i];
    }
  }

  public int size() {
    return size;
  }

  public boolean isOverflow() {
    return size > maxSize;
  }

  public boolean isKeyAt(K key, int rank) {
    return rank < size && key.compareTo((K) keys[rank]) == 0;
  }


  public int rank(K key) {
    int left = 0;
    int right = size - 1;
    while (left <= right) {
      int middle = left + (right - left) / 2;
      int compare = key.compareTo((K) keys[middle]);

      if (compare == 0) {
        return middle;
      } else if (compare < 0) {
        right = middle - 1;
      } else {
        left = middle + 1;
      }
    }
    return left;
  }

  public void justDeleteOnLeaf(int at) {
    shiftLeft(at + 1);
    this.size--;
  }

  public int getChildrenSize() {
    return this.isLeaf() ? 0 : size() + 1;
  }

  public boolean isEmpty() {
    return size <= 0;
  }

  public boolean isMinDegree() {
    return size == t - 1;
  }

  public int getSaturation() {
    return size - (t - 1);
  }
}
