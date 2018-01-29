package algorithms.sedgewick.sorting.quick;

import algorithms.sedgewick.sorting.AbstractSort;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by Chen Li on 2018/1/23.
 */
public class Fast3WayPartitionQuickSort extends AbstractSort {
  @Override
  public void sort(Comparable[] arr) {
    StdRandom.shuffle(arr);
    sort(arr, 0, arr.length - 1);
  }

  protected void sort(Comparable[] arr, int low, int high) {
    if (low >= high) {
      return;
    }
    if (low + 1 == high) {
      if (less(arr[high], arr[low])) {
        exchange(arr, low, high);
      }
      return;
    }
    //fast 3 way partition
    Comparable v = arr[low];
    int i = low + 1;
    int j = high;
    int p = i;
    int q = j;
    while (i <= j) {
      while (i <= j) {
        int compared = arr[i].compareTo(v);
        if (compared == 0) {
          exchange(arr, i, p);
          p++;
          i++;
          continue;
        } else if (compared < 0) {
          i++;
          continue;
        } else {
          //>
          break;
        }
      }
      while (i <= j) {
        int compared = arr[j].compareTo(v);
        if (compared == 0) {
          exchange(arr, j, q);
          q--;
          j--;
        } else if (compared > 0) {
          j--;
        } else {
          break;
        }
      }
      if (i <= j) {
        exchange(arr, i, j);
        i++;
        j--;
      }
    }

    //swap equal keys to right position
    swapSubArray(arr, low, j, p - 1);
    swapSubArray(arr, i, high, q);

    int r1 = low + j - p;
    sort(arr, low, r1);
    int l1 = high - q + i;
    sort(arr, l1, high);
  }

  /**
   * swap left subarray and right sub array
   *
   * @param arr    the array
   * @param low    from index
   * @param high   to index
   * @param middle the index splitting the array, to low ~ middle,  middle+1 ~ high
   */
  protected void swapSubArray(Comparable[] arr, int low, int high, int middle) {
    int size = high - low + 1;
    int leftSize = middle - low + 1;
    int rightSize = size - leftSize;
    if (size < 1 || leftSize < 1 || rightSize < 1) {
      return;
    }
    int halfSize = size / 2;
    if (leftSize <= halfSize) {
      //from left sub array
      for (int i = low; i <= middle; i++) {
        exchange(arr, i, i + rightSize);
      }
    } else {
      //from right sub array
      for (int i = middle + 1; i <= high; i++) {
        exchange(arr, i, i - leftSize);
      }
    }
  }
}
