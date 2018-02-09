package algorithms.sedgewick.sorting.heap;

import algorithms.sedgewick.sorting.AbstractComparableSort;
import algorithms.sedgewick.sorting.ComparableOperations;

/**
 * Created by Chen Li on 2018/2/6.
 */
public class HeapSort<K extends Comparable<K>> extends AbstractComparableSort<K> {

  private BinaryHeapOperations heapOperations = new BinaryHeapOperations();
  private ComparableOperations comparableOperations = new ComparableOperations();

  @Override
  public void sort(K[] arr) {
    if (arr == null || arr.length <= 1) {
      return;
    }
    //put min at arr[0]
    int minIndex = 0;
    K minValue = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (comparableOperations.less(arr[i], minValue)) {
        minIndex= i;
        minValue = arr[i];
      }
    }
    comparableOperations.exchange(arr, 0, minIndex);

    //heap construction
    int sortLength = arr.length - 1;
    for (int i = sortLength / 2; i >= 1; i--) {
      heapOperations.sink(arr, i, sortLength - 1);
    }
    //
    int k = sortLength - 1;
    while (k > 1) {
      comparableOperations.exchange(arr, 1, k--);
      heapOperations.sink(arr, 1, k);
    }
  }
}
