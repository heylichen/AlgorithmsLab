package algorithms.sedgewick.sorting.heap.operations;

import java.util.Comparator;

/**
 * Created by Chen Li on 2018/2/15.
 */
public class MultiwayHeapOperations<K extends Comparable<K>> extends AbstractHeapOperations<K>
    implements HeapOperations<K> {

  private Comparator<K> comparator;
  private int ways = 2;

  public MultiwayHeapOperations(Comparator<K> comparator, int k) {
    this.comparator = comparator;
    this.ways = k;
    if (ways < 2) {
      throw new IllegalArgumentException("ways must be larger than 1!");
    }
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
    return comparator.compare(key1, key2) > 0;
  }

  public int getParent(int child) {
    return (child + ways - 2) / ways;
  }


  public int getMaxPriorityChildOf(K[] keys, int index, int rightBound) {
    int from = index * ways - (ways - 2);
    if (from > rightBound) {
      return -1;
    }
    int to = from + (ways - 1);
    if (to > rightBound) {
      to = rightBound;
    }
    int maxPriorityChild = from;
    K maxPriorityChildKey = keys[from];
    for (int i = from + 1; i <= to; i++) {
      K currentKey = keys[i];
      if (isHigherPriority(currentKey, maxPriorityChildKey)) {
        maxPriorityChild = i;
        maxPriorityChildKey = currentKey;
      }
    }

    return maxPriorityChild;
  }
}
