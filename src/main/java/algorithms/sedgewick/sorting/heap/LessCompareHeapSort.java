package algorithms.sedgewick.sorting.heap;

import algorithms.sedgewick.sorting.ComparableOperations;
import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.heap.operations.HeapOperations;
import algorithms.sedgewick.sorting.heap.operations.HeapOperationsFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/2/6.
 * Sink to the bottom, then swim
 * Most items reinserted into the heap during sortdown
 * go all the way to the bottom. Floyd observed in 1964 that we can thus save time by
 * avoiding the check for whether the item has reached its position, simply promoting
 * the larger of the two children until the bottom is reached, then moving back up the
 * heap to the proper position. This idea cuts the number of compares by a factor of 2
 * asymptotically—close to the number used by mergesort (for a randomly-ordered array)
 */
public class LessCompareHeapSort<K extends Comparable<K>> implements Sort<K> {

  private HeapOperationsFactory heapOperationsFactory = new HeapOperationsFactory();
  @Setter
  private HeapOperations<K> heapOperations = heapOperationsFactory.basicMaxHeapOperations();
  //must be max heap operations
  @Setter
  private ComparableOperations comparableOperations = new ComparableOperations();
  @Getter
  @Setter
  private String name;

  public LessCompareHeapSort() {
  }

  public LessCompareHeapSort(String name) {
    this.name = name;
  }

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
        minIndex = i;
        minValue = arr[i];
      }
    }
    comparableOperations.exchange(arr, 0, minIndex);

    //heap construction
    int sortLength = arr.length - 1;
    for (int i = sortLength / 2; i >= 1; i--) {
      heapOperations.sink(arr, i, sortLength);
    }
    //
    int k = sortLength;
    while (k > 1) {
      comparableOperations.exchange(arr, 1, k--);
      sinkToBottomThenSwim(arr, 1, k);
    }
  }

  private void sinkToBottomThenSwim(K[] keys, int from, int to) {
    int index = from;
    int childIndex = index * 2;
    K currentV = keys[from];
    while (childIndex <= to) {
      int largerChildIndex = -1;
      if (childIndex + 1 <= to && comparableOperations.less(keys[childIndex], keys[childIndex + 1])) {
        largerChildIndex = childIndex + 1;
      } else {
        largerChildIndex = childIndex;
      }
      keys[index] = keys[largerChildIndex];
      index = largerChildIndex;
      childIndex = index * 2;
    }
    if (index != from) {
      keys[index] = currentV;
    }
    //swim
    heapOperations.swim(keys, index, 1);
  }

  @Override
  public String toString() {
    return name;
  }
}
