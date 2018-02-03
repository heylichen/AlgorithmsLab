package algorithms.sedgewick.sorting.quick;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.quick.cutoff.Cutoff1Sort;
import algorithms.sedgewick.sorting.quick.cutoff.CutoffInsertionSort;
import algorithms.sedgewick.sorting.quick.cutoff.CutoffSort;
import algorithms.sedgewick.sorting.quick.partition.Fast3WayPartitioner;
import algorithms.sedgewick.sorting.quick.partition.Partitioner;
import algorithms.sedgewick.sorting.quick.partition.SimplePartitioner;
import algorithms.sedgewick.sorting.quick.partition.ThreeWayPartitioner;
import algorithms.sedgewick.sorting.quick.pivot.Median3PivotSelector;
import algorithms.sedgewick.sorting.quick.pivot.NintherPivotSelector;
import algorithms.sedgewick.sorting.quick.pivot.PivotSelector;
import algorithms.sedgewick.sorting.quick.pivot.RandomPivotSelector;

/**
 * Created by Chen Li on 2018/2/2.
 * factory to create quick sort variants
 */
public class QuickSortFactory {

  public Sort bestQuickSort() {
    QuickSortSkeleton skeleton = basicQuickSortSkeleton("bestQuickSort", false);

    Partitioner partitioner = new Fast3WayPartitioner();
    PivotSelector pivotSelector = new Median3PivotSelector();
    CutoffSort cutoffSort = new CutoffInsertionSort();
    skeleton.setPivotSelector(pivotSelector);
    skeleton.setCutoffSort(cutoffSort);
    skeleton.setPartitioner(partitioner);
    return skeleton;
  }

  public Sort basicQuickSort() {
    return new QuickSort();
  }

  public Sort cutoffQuickSort() {
    return cutoffQuickSortSkeleton("cutoffQuickSort", true, new CutoffInsertionSort());
  }

  public Sort median3QuickSort() {
    QuickSortSkeleton skeleton = basicQuickSortSkeleton("median3QuickSort", false);

    PivotSelector pivotSelector = new Median3PivotSelector();
    CutoffSort cutoffSort = new CutoffInsertionSort();
    skeleton.setPivotSelector(pivotSelector);
    skeleton.setCutoffSort(cutoffSort);
    return skeleton;
  }

  public Sort nintherQuickSort() {
    QuickSortSkeleton skeleton = basicQuickSortSkeleton("nintherQuickSort", false);
    PivotSelector pivotSelector = new NintherPivotSelector();
    CutoffSort cutoffSort = new CutoffInsertionSort();
    skeleton.setPivotSelector(pivotSelector);
    skeleton.setCutoffSort(cutoffSort);
    return skeleton;
  }

  public Sort threeWayPartitionQuickSort() {
    QuickSortSkeleton skeleton = basicQuickSortSkeleton("3WayPartitionQuickSort", true);
    Partitioner partitioner = new ThreeWayPartitioner();
    CutoffSort cutoffSort = new CutoffInsertionSort();
    skeleton.setPartitioner(partitioner);
    skeleton.setCutoffSort(cutoffSort);
    return skeleton;
  }

  public Sort fast3WayPartitionQuickSort() {
    QuickSortSkeleton skeleton = basicQuickSortSkeleton("fast3WayPartitionQuickSort", true);

    Partitioner partitioner = new Fast3WayPartitioner();
    CutoffSort cutoffSort = new CutoffInsertionSort();
    skeleton.setPartitioner(partitioner);
    skeleton.setCutoffSort(cutoffSort);
    return skeleton;
  }

  private QuickSortSkeleton cutoffQuickSortSkeleton(String name, boolean shuffle, CutoffSort cutoffSort) {
    QuickSortSkeleton skeleton = basicQuickSortSkeleton(name, shuffle);
    skeleton.setCutoffSort(cutoffSort);
    return skeleton;
  }

  private QuickSortSkeleton basicQuickSortSkeleton(String name, boolean shuffle) {
    QuickSortSkeleton skeleton = new QuickSortSkeleton(name, shuffle);
    PivotSelector pivotSelector = new RandomPivotSelector();
    Partitioner partitioner = new SimplePartitioner();
    CutoffSort cutoffSort = new Cutoff1Sort();

    skeleton.setPartitioner(partitioner);
    skeleton.setPivotSelector(pivotSelector);
    skeleton.setCutoffSort(cutoffSort);
    return skeleton;
  }
}
