package algorithms.sedgewick.sorting.heap;

import algorithms.sedgewick.sorting.ComparableOperations;
import algorithms.sedgewick.sorting.Sort;

/**
 * Created by Chen Li on 2018/2/6.
 */
public class HeapSort<K extends Comparable<K>> implements Sort<K> {

  private BinaryHeapOperations heapOperations = new BinaryHeapOperations();
  private ComparableOperations comparableOperations = new ComparableOperations();

  @Override
  public void sort(K[] arr) {
    //heap construction
    for (int i = arr.length / 2; i >= 1; i--) {
      heapOperations.sink(arr, i, arr.length - 1);
    }
    //
    int k = arr.length - 1;
    while (k > 1) {
      comparableOperations.exchange(arr, 1, k--);
      heapOperations.sink(arr, 1, k);
    }
  }
}
