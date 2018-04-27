package algorithms.sorting.heap;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Chen Li on 2018/2/8.
 */
public class IndexMinPQ<K extends Comparable<K>>  implements Iterable<Integer> {

  private int capacity;
  private K[] keys;
  private int[] qp;//keep inner index in value
  private int[] pq;//keep index in value

  private int size;

  public IndexMinPQ(int capacity) {
    this.capacity = capacity;
    keys = (K[]) new Comparable[capacity + 1];
    pq = new int[capacity + 1];
    qp = new int[capacity + 1];
    for (int i = 0; i <= capacity; i++) {
      pq[i] = -1;
      qp[i] = -1;
    }
    size = 0;
  }

  public void insert(int i, K key) {
    if (i < 0 || i >= capacity) {
      throw new IllegalArgumentException();
    }
    if (contains(i)) {
      throw new IllegalArgumentException("index is already in the priority queue");
    }
    size++;
    keys[i] = key;
    qp[i] = size;
    pq[size] = i;
    swim(size);
  }

  /**
   * delete min key, reuturn it's key
   */
  public int delMin() {
    if (size == 0) {
      throw new NoSuchElementException("Priority queue underflow");
    }
    int minIndex = pq[1];
    exchange(1, size);
    size--;
    //clear
    pq[size + 1] = -1;
    keys[minIndex] = null;
    qp[minIndex] = -1;

    sink(1);
    return minIndex;
  }

  public void changeKey(int i, K key) {
    K oldKey = keys[i];
    keys[i] = key;
    int innerIndex = qp[i];
    if (key.compareTo(oldKey) >= 0) {
      sink(innerIndex);
    } else {
      swim(innerIndex);
    }
  }

  public void delete(int index) {
    K deleted = keys[index];
    K replacedBy = keys[qp[size]];

    int innerIndex = pq[index];
    exchange(innerIndex, size);
    //clear
    keys[qp[size]] = null;
    pq[qp[size]] = -1;
    qp[size] = -1;
    size--;
    //
    if (replacedBy.compareTo(deleted) >= 0) {
      sink(innerIndex);
    } else {
      swim(innerIndex);
    }
  }

  protected boolean contains(int i) {
    return qp[i] != -1;
  }


  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int minIndex() {
    if (size == 0) {
      throw new NoSuchElementException("Priority queue underflow");
    }
    return pq[1];
  }

  public K keyOf(int i) {
    if (i < 0 || i >= capacity) {
      throw new IllegalArgumentException();
    }
    if (!contains(i)) {
      throw new NoSuchElementException("index is not in the priority queue");
    }
    return keys[i];
  }

  public K minKey() {
    if (size == 0) {
      throw new NoSuchElementException("Priority queue underflow");
    }
    return keys[pq[1]];
  }

  protected void sink(int k) {
    int index = k;
    int childIndex = index * 2;
    while (childIndex <= size) {
      int selectedChildIndex = -1;
      if (childIndex + 1 <= size && isGreater(childIndex, childIndex + 1)) {
        selectedChildIndex = childIndex + 1;
      } else {
        selectedChildIndex = childIndex;
      }
      if (isGreater(index, selectedChildIndex)) {
        exchange(index, selectedChildIndex);
        index = selectedChildIndex;
        childIndex = index * 2;
      } else {
        break;
      }
    }
  }

  protected void swim(int k) {
    int index = k;
    int parentIndex = index / 2;
    while (parentIndex >= 1 && isGreater(parentIndex, index)) {
      exchange(parentIndex, index);
      index = parentIndex;
      parentIndex = parentIndex / 2;
    }
  }

  protected void exchange(int i, int j) {
    int temp = pq[i];
    pq[i] = pq[j];
    pq[j] = temp;

    qp[pq[i]] = i;
    qp[pq[j]] = j;
  }

  protected boolean isGreater(int i, int j) {
    return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
  }

  @Override
  public Iterator<Integer> iterator() {
    return new HeapIterator();
  }

  private class HeapIterator implements Iterator<Integer> {

    private IndexMinPQ copy;

    public HeapIterator() {
      copy = new IndexMinPQ(capacity);
      for (int i = 1; i <= size; i++) {
        copy.insert(pq[i], keys[pq[i]]);
      }
    }

    @Override
    public boolean hasNext() {
      return !copy.isEmpty();
    }

    @Override
    public Integer next() {
      return copy.delMin();
    }
  }

  public static void main(String[] args) {
    // insert a bunch of strings
    String[] strings = {"it", "was", "the", "best", "of", "times", "it", "was", "the", "worst"};

    IndexMinPQ<String> pq = new IndexMinPQ<>(strings.length);
    for (int i = 0; i < strings.length; i++) {
      pq.insert(i, strings[i]);
    }

    // delete and print each key
    while (!pq.isEmpty()) {
      int i = pq.delMin();
      StdOut.println(i + " " + strings[i]);
    }
    StdOut.println();

    for (int i = 0; i < strings.length; i++) {
      pq.insert(i, strings[i]);
    }

    pq.changeKey(6, "zed");
    while (!pq.isEmpty()) {
      int i = pq.delMin();
      StdOut.println(i + " " + strings[i]);
    }
//    if (1 == 1) {
//      return;
//    }

    // reinsert the same strings
    for (int i = 0; i < strings.length; i++) {
      pq.insert(i, strings[i]);
    }

    // print each key using the iterator
    for (int i : pq) {
      StdOut.println(i + " " + strings[i]);
    }
    while (!pq.isEmpty()) {
      pq.delMin();
    }

  }
}
