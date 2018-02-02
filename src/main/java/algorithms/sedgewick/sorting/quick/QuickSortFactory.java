package algorithms.sedgewick.sorting.quick;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.quick.cutoff.CutoffInsertionSort;
import algorithms.sedgewick.sorting.quick.cutoff.CutoffSort;
import algorithms.sedgewick.sorting.quick.partition.Partitioner;
import algorithms.sedgewick.sorting.quick.partition.SimplePartitioner;
import algorithms.sedgewick.sorting.quick.pivot.Median3PivotSelector;
import algorithms.sedgewick.sorting.quick.pivot.NintherPivotSelector;
import algorithms.sedgewick.sorting.quick.pivot.PivotSelector;

/**
 * Created by Chen Li on 2018/2/2.
 */
public class QuickSortFactory {

  public Sort basicQuickSort() {
    return new QuickSort();
  }

  public Sort media3QuickSort() {
    QuickSortSkeleton skeleton = new QuickSortSkeleton();
    PivotSelector pivotSelector = new Median3PivotSelector();
    Partitioner partitioner = new SimplePartitioner();
    CutoffSort cutoffSort = new CutoffInsertionSort();

    skeleton.setPartitioner(partitioner);
    skeleton.setPivotSelector(pivotSelector);
    skeleton.setCutoffSort(cutoffSort);
    return skeleton;
  }

  public Sort nintherQuickSort() {
    QuickSortSkeleton skeleton = new QuickSortSkeleton();
    PivotSelector pivotSelector = new NintherPivotSelector();
    Partitioner partitioner = new SimplePartitioner();
    CutoffSort cutoffSort = new CutoffInsertionSort();

    skeleton.setPartitioner(partitioner);
    skeleton.setPivotSelector(pivotSelector);
    skeleton.setCutoffSort(cutoffSort);
    return skeleton;
  }

}
