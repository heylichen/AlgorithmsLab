package algorithms.sedgewick.sorting.heap;

import algorithms.sedgewick.sorting.ComparableOperations;

/**
 * Created by Chen Li on 2018/2/6.
 */
public class BinaryHeapOperations<K extends Comparable<K>> {

  private ComparableOperations comparableOperations = new ComparableOperations();

  public void swim(K[] keys, int from, int to) {
    int index = from;
    int parentIndex = index / 2;
    while (parentIndex >= to && keys[parentIndex].compareTo(keys[index]) < 0) {
      comparableOperations.exchange(keys, parentIndex, index);
      index = parentIndex;
      parentIndex = parentIndex / 2;
    }
  }

  public void sink(K[] keys, int from, int to) {
    int index = from;
    int childIndex = index * 2;
    while (childIndex <= to) {
      int largerChildIndex = -1;
      if (comparableOperations.less(keys[childIndex], keys[childIndex + 1]) && childIndex + 1 <= to) {
        largerChildIndex = childIndex + 1;
      } else {
        largerChildIndex = childIndex;
      }
      if (comparableOperations.less(keys[index], keys[largerChildIndex])) {
        comparableOperations.exchange(keys, index, largerChildIndex);
        index = largerChildIndex;
        childIndex = index * 2;
      } else {
        break;
      }
    }
  }
}
