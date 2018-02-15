package algorithms.sedgewick.sorting.heap.operations;

import java.util.Comparator;

/**
 * Created by Chen Li on 2018/2/11.
 */
public class NoExchangeHeapOperations<K extends Comparable<K>> extends AbstractHeapOperations<K>
    implements HeapOperations<K> {

  private Comparator<K> comparator;

  public NoExchangeHeapOperations(Comparator<K> comparator) {
    this.comparator = comparator;
  }

  @Override
  public void swim(K[] keys, int from, int to) {
    int index = from;
    int parentIndex = index / 2;
    int end = from;
    K currentV = keys[from];
    while (parentIndex >= to && isHigherPriority(currentV, keys[parentIndex])) {
      keys[index] = keys[parentIndex];
      index = parentIndex;
      parentIndex = parentIndex / 2;
    }
    keys[index] = currentV;
  }

  @Override
  public void sink(K[] keys, int from, int to) {
    int index = from;
    int childIndex = index * 2;
    K currentV = keys[from];
    while (childIndex <= to) {
      int largerChildIndex = -1;
      if (childIndex + 1 <= to && isHigherPriority(keys[childIndex + 1], keys[childIndex])) {
        largerChildIndex = childIndex + 1;
      } else {
        largerChildIndex = childIndex;
      }
      if (isHigherPriority(keys[largerChildIndex], currentV)) {
        keys[index] = keys[largerChildIndex];
        index = largerChildIndex;
        childIndex = index * 2;
      } else {
        break;
      }
    }
    if (index != from) {
      keys[index] = currentV;
    }
  }

  @Override
  protected boolean isHigherPriority(K key1, K key2) {
    return comparator.compare(key1, key2) > 0;
  }
}
