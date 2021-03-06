package algorithms.sorting.heap.operations;

import lombok.Getter;

/**
 * Created by Chen Li on 2018/2/10.
 */
public abstract class AbstractHeapOperations<K extends Comparable<K>> implements HeapOperations<K> {

  @Getter
  protected int compares = 0;

  protected abstract boolean isHigherPriority(K key1, K key2);
}
