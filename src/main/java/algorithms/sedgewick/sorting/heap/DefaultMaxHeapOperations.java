package algorithms.sedgewick.sorting.heap;

/**
 * Created by Chen Li on 2018/2/10.
 */
public class DefaultMaxHeapOperations<K extends Comparable<K>> extends AbstractHeapOperations<K>
    implements MaxHeapOperations<K> {

  @Override
  protected boolean isHigherPriority(K key1, K key2) {
    return key1.compareTo(key2) > 0;
  }
}
