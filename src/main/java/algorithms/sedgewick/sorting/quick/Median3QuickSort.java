package algorithms.sedgewick.sorting.quick;

import algorithms.sedgewick.sorting.AbstractSort;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by Chen Li on 2018/1/10.
 */
public class Median3QuickSort extends AbstractSort {

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
    int partitionIndex = partition(arr, low, high);
    sort(arr, low, partitionIndex - 1);
    sort(arr, partitionIndex + 1, high);
  }

  protected int partition(Comparable[] arr, int low, int high) {
    //midian of three rearrange, so that arr[low] is middle value, arr[high] is max value
    int middle = (low + high) / 2;
    if (less(arr[high], arr[low])) {
      exchange(arr, low, high);
      //now low <= high
    }
    if (arr[low].compareTo(arr[middle]) >= 0) {
      //middle <= low <= high
    } else if (less(arr[high], arr[middle])) {
      // low <= high < middle
      Comparable t = arr[high];
      arr[high] = arr[middle];
      arr[middle] = arr[low];
      arr[low] = t;
    } else {
      // low < middle <=high
      exchange(arr, low, middle);
    }

    Comparable v = arr[low];
    int i = low;
    int j = high + 1;
    while (true) {
      while (less(arr[++i], v)) ;
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
