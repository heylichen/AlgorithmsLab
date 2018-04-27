package algorithms.sorting.heap;

import algorithms.sedgewick.sorting.ComparableOperations;
import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.heap.operations.HeapOperations;
import algorithms.sorting.heap.operations.HeapOperations;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/2/18.
 */
public abstract class AbstractHeapSort<K extends Comparable<K>> implements Sort<K> {

  @Setter
  protected HeapOperations<K> heapOperations;//must be max heap operations
  @Setter
  protected ComparableOperations comparableOperations = new ComparableOperations();
  @Getter
  @Setter
  protected String name;
  @Getter
  protected int compares = 0;

  public AbstractHeapSort(String name) {
    this.name = name;
  }

  @Override
  public void sort(K[] arr) {
    if (arr == null || arr.length <= 1) {
      return;
    }
    preprocess(arr);
    heapConstruction(arr);
    sortDown(arr);
  }

  protected void preprocess(K[] arr) {
    int minIndex = 0;
    K minValue = arr[0];
    for (int i = 1; i < arr.length; i++) {
      compares++;
      if (comparableOperations.less(arr[i], minValue)) {
        minIndex = i;
        minValue = arr[i];
      }
    }
    comparableOperations.exchange(arr, 0, minIndex);
  }

  protected void heapConstruction(K[] arr) {
    int sortLength = arr.length - 1;
    for (int i = sortLength / 2; i >= 1; i--) {
      heapOperations.sink(arr, i, sortLength);
    }
  }

  protected abstract void sortDown(K[] arr);

  @Override
  public String toString() {
    return name;
  }

  public int getCompares() {
    return compares + heapOperations.getCompares();
  }
}
