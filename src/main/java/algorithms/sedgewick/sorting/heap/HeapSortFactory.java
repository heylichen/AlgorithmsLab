package algorithms.sedgewick.sorting.heap;

import algorithms.sedgewick.sorting.ComparableOperations;

/**
 * Created by Chen Li on 2018/2/10.
 */
public class HeapSortFactory {

  public HeapSort bestHeapSort() {
    return lessExchHeapSort();
  }

  public HeapSort defaultHeapSort() {
    HeapSort heapSort = new HeapSort("defaultHeapSort");
    heapSort.setHeapOperations(new DefaultMaxHeapOperations());
    heapSort.setComparableOperations(new ComparableOperations());
    return heapSort;
  }

  public HeapSort lessExchHeapSort() {
    HeapSort heapSort = new HeapSort("lessExchHeapSort");
    heapSort.setHeapOperations(new LessExchHeapOperations());
    heapSort.setComparableOperations(new ComparableOperations());
    return heapSort;
  }
}
