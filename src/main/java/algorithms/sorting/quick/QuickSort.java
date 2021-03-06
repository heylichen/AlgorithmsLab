package algorithms.sorting.quick;

import java.util.Random;

import algorithms.sorting.AbstractComparableSort;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by Chen Li on 2018/1/9.
 * basic quick sort implementation
 */
public class QuickSort extends AbstractComparableSort {

  private Random random = new Random();
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

    Comparable v = arr[low];
    int i = low;
    int j = high + 1;
    while (true) {
      while (less(arr[++i], v)) {
        if (i == high) {
          break;
        }
      }
      while (less(v, arr[--j])) {
        ;
      }
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
