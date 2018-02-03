package algorithms.sedgewick.sorting.quick.cutoff;

import algorithms.sedgewick.sorting.AbstractComparableOperator;

/**
 * Created by Chen Li on 2018/2/2.
 */
public class Cutoff2Sort<T extends Comparable<T>> extends AbstractComparableOperator<T> implements CutoffSort<T> {

  @Override
  public boolean cutoff(T[] arr, int low, int high) {
    if (low >= high) {
      return true;
    }
    if (low + 1 == high) {
      if (less(arr[high], arr[low])) {
        exchange(arr, low, high);
      }
      return true;
    }
    return false;
  }
}
