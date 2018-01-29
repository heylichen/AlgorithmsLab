package algorithms.sedgewick.sorting.quick;

import algorithms.sedgewick.sorting.AbstractSort;
import edu.princeton.cs.algs4.StdRandom;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/1/11.
 */
public class ThreeWayPartitionQuickSort extends AbstractSort {
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
    Pair<Integer, Integer> pair = partition(arr, low, high);
    sort(arr, low, pair.getLeft());
    sort(arr, pair.getRight(), high);
  }

  protected Pair<Integer, Integer> partition(Comparable[] arr, int low, int high) {
    Comparable v = arr[low];
    int i = low + 1;
    int j = i;
    int n = high;
    while (j <= n) {
      int compared = arr[i].compareTo(v);
      if (compared == 0) {
        j++;
      } else if (compared < 0) {
        exchange(arr, i++, j++);
      } else {
        exchange(arr, j, n--);
      }
    }
    exchange(arr, low, i - 1);
    return Pair.of(i - 1, j);
  }
}
