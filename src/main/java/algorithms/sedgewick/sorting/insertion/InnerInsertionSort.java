package algorithms.sedgewick.sorting.insertion;

import algorithms.sedgewick.sorting.AbstractSort;

/**
 * Created by Chen Li on 2018/1/10.
 */
public class InnerInsertionSort extends AbstractSort {
  @Override
  public void sort(Comparable[] arr) {
    if (arr == null) {
      return;
    }
    sort(arr, 0, arr.length - 1);
  }

  public void sort(Comparable[] arr, int low, int high) {
    if (low >= high) {
      return;
    }
    for (int i = low + 1; i <= high; i++) {
      Comparable t = arr[i];
      //find j so that a[i] >= a[j], a[i] should be put in index j+1
      //all elements from index j+1 to i-1 should be shift right
      int j = i - 1;
      for (; j >= low; j--) {
        if (!less(t, arr[j])) {
          break;
        }
      }
      j++;
      shiftRight(arr, j, i - 1);
      arr[j] = t;
    }
  }

  protected void shiftRight(Comparable[] arr, int from, int to) {
    if (from > to) {
      return;
    }
    for (int k = to; k >= from; k--) {
      arr[k + 1] = arr[k];
    }
  }
}
