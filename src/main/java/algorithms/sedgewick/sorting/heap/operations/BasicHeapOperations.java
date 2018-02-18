package algorithms.sedgewick.sorting.heap.operations;

import java.util.Comparator;

/**
 * Created by Chen Li on 2018/2/15.
 */
public class BasicHeapOperations<K extends Comparable<K>> extends AbstractBinaryHeapOperations<K> {

  private Comparator<K> comparator;

  public BasicHeapOperations(Comparator<K> comparator) {
    this.comparator = comparator;
  }

  public void swim(K[] keys, int from, int to) {
    int index = from;
    int parentIndex = getParent(index);
    while (parentIndex >= to && isHigherPriority(keys[index], keys[parentIndex])) {
      exchange(keys, parentIndex, index);
      index = parentIndex;
      parentIndex = getParent(parentIndex);
    }
  }

  public void sink(K[] keys, int from, int to) {
    int index = from;
    int childIndex = getMaxPriorityChildOf(keys, from, to);
    while (childIndex != -1) {
      if (isHigherPriority(keys[childIndex], keys[index])) {
        exchange(keys, index, childIndex);
        index = childIndex;
        childIndex = getMaxPriorityChildOf(keys, childIndex, to);
      } else {
        break;
      }
    }
  }

  protected void exchange(K[] arr, int i, int j) {
    if (i == j) {
      return;
    }
    K t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }

  @Override
  protected boolean isHigherPriority(K key1, K key2) {
    return comparator.compare(key1, key2) > 0;
  }
}
