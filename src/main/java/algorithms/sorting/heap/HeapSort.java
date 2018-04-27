package algorithms.sorting.heap;

/**
 * Created by Chen Li on 2018/2/6.
 */
public class HeapSort<K extends Comparable<K>> extends AbstractHeapSort<K> {

  public HeapSort(String name) {
    super(name);
  }

  @Override
  protected void sortDown(K[] arr) {
    int k = arr.length - 1;
    while (k > 1) {
      comparableOperations.exchange(arr, 1, k--);
      heapOperations.sink(arr, 1, k);
    }
  }
}
