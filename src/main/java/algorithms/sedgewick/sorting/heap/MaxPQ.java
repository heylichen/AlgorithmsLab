package algorithms.sedgewick.sorting.heap;

import algorithms.sedgewick.sorting.AbstractComparableOperator;
import algorithms.sedgewick.sorting.PriorityQueue;

/**
 * Created by Chen Li on 2018/2/5.
 */
public class MaxPQ<K extends Comparable<K>> extends AbstractComparableOperator<K> implements PriorityQueue<K> {

  private K[] keys;
  private int size = 0;

  public MaxPQ(K[] keys) {
    this.keys = (K[]) new Object[keys.length + 1];
    System.arraycopy(keys, 0, this.keys, 1, keys.length);
  }

  public MaxPQ(int capacity) {
    this.keys = (K[]) new Comparable[capacity + 1];
  }

  @Override
  public void insert(K key) {
    int index = size + 1;
    keys[index] = key;
    //swim

    int parentIndex = index / 2;
    while (parentIndex >= 1 && keys[parentIndex].compareTo(keys[index]) < 0) {
      exchange(keys, parentIndex, index);
      index = parentIndex;
      parentIndex = parentIndex / 2;
    }
    size++;
  }

  @Override
  public K peek() {
    return keys[1];
  }

  @Override
  public K pop() {
    K v = keys[1];
    keys[1] = keys[size];
    size--;
    //sink
    int index = 1;
    int childIndex = index * 2;
    while (childIndex <=size) {
      int largerChildIndex = -1;
      if (less(keys[childIndex], keys[childIndex + 1]) && childIndex + 1 <= size) {
        largerChildIndex = childIndex + 1;
      } else {
        largerChildIndex = childIndex;
      }
      if (less(keys[index], keys[largerChildIndex])) {
        exchange(keys, index, largerChildIndex);
        index = largerChildIndex;
        childIndex = index * 2;
      } else {
        break;
      }
    }
    return v;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }
}
