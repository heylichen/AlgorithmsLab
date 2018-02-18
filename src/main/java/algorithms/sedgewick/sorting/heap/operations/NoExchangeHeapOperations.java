package algorithms.sedgewick.sorting.heap.operations;

import java.util.Comparator;

/**
 * Created by Chen Li on 2018/2/11.
 */
public class NoExchangeHeapOperations<K extends Comparable<K>> extends AbstractBinaryHeapOperations<K> {

  private Comparator<K> comparator;

  public NoExchangeHeapOperations(Comparator<K> comparator) {
    this.comparator = comparator;
  }

  @Override
  public void swim(K[] keys, int from, int to) {
    int index = from;
    int parentIndex = getParent(index);
    K currentV = keys[from];
    while (parentIndex >= to && isHigherPriority(currentV, keys[parentIndex])) {
      keys[index] = keys[parentIndex];
      index = parentIndex;
      parentIndex = getParent(parentIndex);
    }
    keys[index] = currentV;
  }

  @Override
  public void sink(K[] keys, int from, int to) {
    int childIndex = getMaxPriorityChildOf(keys, from, to);
    if (childIndex == -1) {
      return;
    }
    int index = from;
    K currentV = keys[from];
    while (childIndex != -1) {
      if (isHigherPriority(keys[childIndex], currentV)) {
        keys[index] = keys[childIndex];
        index = childIndex;
        childIndex = getMaxPriorityChildOf(keys, index, to);
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
    compares++;
    return comparator.compare(key1, key2) > 0;
  }
}
