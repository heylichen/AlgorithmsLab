package algorithms.sorting.quick.cutoff;

import algorithms.sorting.ComparableOperations;

/**
 * Created by Chen Li on 2018/2/2.
 */
public class Cutoff2Sort<T extends Comparable<T>> implements CutoffSort<T> {

  private ComparableOperations comparableOperations = new ComparableOperations();

  @Override
  public boolean cutoff(T[] arr, int low, int high) {
    if (low >= high) {
      return true;
    }
    if (low + 1 == high) {
      if (comparableOperations.less(arr[high], arr[low])) {
        comparableOperations.exchange(arr, low, high);
      }
      return true;
    }
    return false;
  }
}
