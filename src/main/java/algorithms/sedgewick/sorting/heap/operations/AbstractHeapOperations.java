package algorithms.sedgewick.sorting.heap.operations;

/**
 * Created by Chen Li on 2018/2/10.
 */
public abstract class AbstractHeapOperations<K extends Comparable<K>> implements HeapOperations<K> {

  protected abstract boolean isHigherPriority(K key1, K key2);
}
