package algorithms.sedgewick.sorting.heap;

import algorithms.sedgewick.sorting.ComparableOperations;
import algorithms.sedgewick.sorting.heap.operations.HeapOperationsFactory;

/**
 * Created by Chen Li on 2018/2/10.
 */
public class HeapSortFactory {

  private HeapOperationsFactory operationsFactory = new HeapOperationsFactory();

  public HeapSort bestHeapSort() {
    return noExchangeHeapSort();
  }

  public HeapSort defaultHeapSort() {
    HeapSort heapSort = new HeapSort("defaultHeapSort");
    heapSort.setHeapOperations(operationsFactory.basicMaxHeapOperations());
    heapSort.setComparableOperations(new ComparableOperations());
    return heapSort;
  }

  public HeapSort noExchangeHeapSort() {
    HeapSort heapSort = new HeapSort("noExchangeHeapSort");
    heapSort.setHeapOperations(operationsFactory.noExchangeMaxHeapOperations());
    heapSort.setComparableOperations(new ComparableOperations());
    return heapSort;
  }

  public HeapSort multiwayHeapSort(int ways) {
    HeapSort heapSort = new HeapSort("multiwayHeapSort:" + ways);
    heapSort.setHeapOperations(operationsFactory.multiwayMaxHeapOperations(ways));
    heapSort.setComparableOperations(new ComparableOperations());
    return heapSort;
  }

  public HeapSort lessCompareHeapSort() {
    HeapSort heapSort = new HeapSort("lessCompare");
    heapSort.setHeapOperations(operationsFactory.noExchangeMaxHeapOperations());
    heapSort.setComparableOperations(new ComparableOperations());
    return heapSort;
  }
}
