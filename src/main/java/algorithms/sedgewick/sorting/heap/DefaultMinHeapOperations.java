package algorithms.sedgewick.sorting.heap;

/**
 * Created by Chen Li on 2018/2/10.
 */
public class DefaultMinHeapOperations<K extends Comparable<K>> extends AbstractHeapOperations<K> implements MinHeapOperations<K> {

  @Override
  protected boolean isHigherPriority(K key1, K key2) {
    return key1.compareTo(key2) < 0;
  }
}
