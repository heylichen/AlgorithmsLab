package algorithms.sedgewick.sorting.heap.operations;

import java.util.Comparator;

/**
 * Created by Chen Li on 2018/2/15.
 */
public class HeapOperationsFactory<K extends Comparable<K>> {

  private final Comparator<K> NORMAL_COMPARATOR = (k1, k2) -> k1.compareTo(k2);
  private final Comparator<K> INVERSE_COMPARATOR = (k1, k2) -> -k1.compareTo(k2);

  public HeapOperations basicMinHeapOperations() {
    return createBasicHeapOperations(INVERSE_COMPARATOR);
  }

  public HeapOperations basicMaxHeapOperations() {
    return createBasicHeapOperations(NORMAL_COMPARATOR);
  }

  public HeapOperations noExchangeMinHeapOperations() {
    return createNoExchangeHeapOperations(INVERSE_COMPARATOR);
  }

  public HeapOperations noExchangeMaxHeapOperations() {
    return createNoExchangeHeapOperations(NORMAL_COMPARATOR);
  }

  private HeapOperations createBasicHeapOperations(Comparator<K> comparator) {
    return new BasicHeapOperations(comparator);
  }

  private HeapOperations createNoExchangeHeapOperations(Comparator<K> comparator) {
    return new NoExchangeHeapOperations(comparator);
  }
}
