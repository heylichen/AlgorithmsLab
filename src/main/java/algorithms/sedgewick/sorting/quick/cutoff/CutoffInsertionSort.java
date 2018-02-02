package algorithms.sedgewick.sorting.quick.cutoff;

import algorithms.sedgewick.sorting.insertion.InnerInsertionSort;

/**
 * Created by Chen Li on 2018/2/2.
 */
public class CutoffInsertionSort<T extends Comparable<T>> implements CutoffSort<T> {

  private int threshold = 10;
  private InnerInsertionSort<T> innerInsertionSort = new InnerInsertionSort<>();

  public CutoffInsertionSort() {
  }

  public CutoffInsertionSort(int threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean cutoff(T[] arr, int low, int high) {
    if (high - low + 1 <= threshold) {
      innerInsertionSort.sort(arr, low, high);
      return true;
    } else {
      return false;
    }
  }

}
