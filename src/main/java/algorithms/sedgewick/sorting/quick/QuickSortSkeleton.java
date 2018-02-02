package algorithms.sedgewick.sorting.quick;

import algorithms.sedgewick.sorting.AbstractSort;
import algorithms.sedgewick.sorting.quick.cutoff.CutoffSort;
import algorithms.sedgewick.sorting.quick.partition.Partitioner;
import algorithms.sedgewick.sorting.quick.pivot.PivotSelector;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/2.
 */
public class QuickSortSkeleton<T extends Comparable<T>> extends AbstractSort<T> {

  @Setter
  private Partitioner<T> partitioner;
  @Setter
  private PivotSelector<T> pivotSelector;
  @Setter
  private CutoffSort cutoffSort;

  @Override
  public void sort(T[] arr) {
    sort(arr, 0, arr.length - 1);
  }

  protected void sort(T[] arr, int low, int high) {
    if (cutoffSort.cutoff(arr, low, high)) {
      return;
    }

    int pivotIndex = pivotSelector.pivot(arr, low, high);
    exchange(arr, low, pivotIndex);
    Pair<Integer, Integer> pair = partitioner.partition(arr, low, high);

    sort(arr, low, pair.getLeft());
    sort(arr, pair.getRight(), high);
  }
}
