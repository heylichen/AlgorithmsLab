package algorithms.sorting.quick;

import algorithms.sorting.Sort;
import algorithms.sorting.quick.cutoff.Cutoff1Sort;
import algorithms.sorting.quick.cutoff.CutoffInsertionSort;
import algorithms.sorting.quick.cutoff.CutoffSort;
import algorithms.sorting.quick.partition.Fast3WayPartitioner;
import algorithms.sorting.quick.partition.Partitioner;
import algorithms.sorting.quick.partition.SimplePartitioner;
import algorithms.sorting.quick.partition.ThreeWayPartitioner;
import algorithms.sorting.quick.pivot.Median3PivotSelector;
import algorithms.sorting.quick.pivot.NintherPivotSelector;
import algorithms.sorting.quick.pivot.PivotSelector;
import algorithms.sorting.quick.pivot.RandomMedian3PivotSelector;
import algorithms.sorting.quick.pivot.RandomPivotSelector;

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

  public Sort randomMedian3QuickSort() {
    QuickSortSkeleton skeleton = basicQuickSortSkeleton("randomMedian3QuickSort", false);

    PivotSelector pivotSelector = new RandomMedian3PivotSelector();
    CutoffSort cutoffSort = new CutoffInsertionSort();
    skeleton.setPivotSelector(pivotSelector);
    skeleton.setCutoffSort(cutoffSort);
    return skeleton;
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
