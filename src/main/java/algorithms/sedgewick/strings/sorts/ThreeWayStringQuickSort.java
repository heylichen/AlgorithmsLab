package algorithms.sedgewick.strings.sorts;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.strings.sorts.partition.ThreeWayStringPartitioner;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/4/6.
 */
public class ThreeWayStringQuickSort implements Sort<String> {

  private ThreeWayStringPartitioner threeWayStringPartitioner = new ThreeWayStringPartitioner();
  private int cutoffThreshold = 10;
  private SubstringInsertionSort insertionSort = new SubstringInsertionSort();

  @Override
  public void sort(String[] arr) {
    sort(arr, 0, arr.length - 1, 0);
  }

  private void sort(String[] arr, int low, int high, int d) {
    if (high - low + 1 <= cutoffThreshold) {
      insertionSort.sort(arr, low, high, d);
      return;
    }

    Pair<Integer, Integer> partitionPair = threeWayStringPartitioner.partition(arr, low, high, d);
    int i = partitionPair.getLeft();
    int j = partitionPair.getRight();
    if (i > low) {
      sort(arr, low, i, d);
    }
    if (i < j - 2 && d < arr[i + 1].length()) {
      sort(arr, i + 1, j - 1, d + 1);
    }
    if (j < high) {
      sort(arr, j, high, d);
    }
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}
