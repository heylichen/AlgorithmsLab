package algorithms.sedgewick.sorting.quick;

import algorithms.sedgewick.sorting.AbstractSort;
import algorithms.sedgewick.sorting.quick.cutoff.CutoffSort;
import algorithms.sedgewick.sorting.quick.partition.Partitioner;
import algorithms.sedgewick.sorting.quick.pivot.PivotSelector;
import edu.princeton.cs.algs4.StdRandom;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/2.
 * a skeleton to implement quick sort variants
 */
public class QuickSortSkeleton<T extends Comparable<T>> extends AbstractSort<T> {

  @Setter
  private Partitioner<T> partitioner;
  @Setter
  private PivotSelector<T> pivotSelector;
  @Setter
  private CutoffSort cutoffSort;
  private String name;
  private boolean shuffle = true;

  public QuickSortSkeleton(String name) {
    this.name = name;
  }

  public QuickSortSkeleton(String name, boolean shuffle) {
    this.name = name;
    this.shuffle = shuffle;
  }

  public QuickSortSkeleton(Partitioner<T> partitioner,
                           PivotSelector<T> pivotSelector,
                           CutoffSort cutoffSort, String name, boolean shuffle) {
    this.partitioner = partitioner;
    this.pivotSelector = pivotSelector;
    this.cutoffSort = cutoffSort;
    this.name = name;
    this.shuffle = shuffle;
  }



  @Override
  public void sort(T[] arr) {
    if (shuffle) {
      StdRandom.shuffle(arr);
    }
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

  @Override
  public String toString() {
    return name;
  }
}
