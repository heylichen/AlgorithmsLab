package algorithms.sedgewick.sorting.quick;

import algorithms.sedgewick.sorting.AbstractSort;
import algorithms.sedgewick.sorting.insertion.InnerInsertionSort;

/**
 * Created by Chen Li on 2018/2/1.
 */
public class NintherPivotQuickSort extends AbstractSort {
  private InnerInsertionSort insertionSort = new InnerInsertionSort();
  private int smallArraySize = 10;

  @Override
  public void sort(Comparable[] arr) {
    sort(arr, 0, arr.length - 1);
  }

  protected void sort(Comparable[] arr, int low, int high) {
    if (high - low + 1 <= smallArraySize) {
      insertionSort.sort(arr, low, high);
      return;
    }
    if (low + 1 == high) {
      if (less(arr[high], arr[low])) {
        exchange(arr, low, high);
      }
      return;
    }
    int partitionIndex = partition(arr, low, high);
    sort(arr, low, partitionIndex - 1);
    sort(arr, partitionIndex + 1, high);
  }

  private int pivot(Comparable[] arr, int low, int high) {
    int p1 = median3(arr, low, low + 1, low + 2);
    int p2 = median3(arr, high - 2, high - 1, high);
    int middle = low + (high - low) / 2;
    int p3 = median3(arr, middle - 1, middle, middle + 1);

    return median3(arr, p1, p3, p2);
  }

  private int median3(Comparable[] arr, int i, int j, int k) {
    Comparable vi = arr[i];
    Comparable vj = arr[j];
    Comparable vk = arr[k];
    if (vi.compareTo(vj) <= 0) {
      if (vk.compareTo(vi) <= 0) {
        return i;
      } else if (vk.compareTo(vj) > 0) {
        return k;
      } else {
        return k;
      }
    } else {
      if (vk.compareTo(vj) <= 0) {
        return j;
      } else if (vk.compareTo(vi) > 0) {
        return i;
      } else {
        return k;
      }
    }
  }

  protected int partition(Comparable[] arr, int low, int high) {
    int pivot = pivot(arr, low, high);
    exchange(arr, low, pivot);
    Comparable v = arr[low];
    int i = low;
    int j = high + 1;
    while (true) {
      while (less(arr[++i], v)) if (i == high) {
        break;
      }
      while (less(v, arr[--j])) ;
      if (i < j) {
        exchange(arr, i, j);
      } else {
        break;
      }
    }
    exchange(arr, low, j);
    return j;
  }
}
