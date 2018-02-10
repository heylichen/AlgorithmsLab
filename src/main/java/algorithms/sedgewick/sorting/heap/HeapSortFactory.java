package algorithms.sedgewick.sorting.heap;

import algorithms.sedgewick.sorting.ComparableOperations;

/**
 * Created by Chen Li on 2018/2/10.
 */
public class HeapSortFactory {

  public HeapSort defaultHepSort() {
    HeapSort heapSort = new HeapSort();
    heapSort.setHeapOperations(new DefaultMaxHeapOperations());
    heapSort.setComparableOperations(new ComparableOperations());
    return heapSort;
  }
}
