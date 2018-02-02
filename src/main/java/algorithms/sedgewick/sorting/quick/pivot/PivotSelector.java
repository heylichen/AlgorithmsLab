package algorithms.sedgewick.sorting.quick.pivot;

/**
 * Created by Chen Li on 2018/1/31.
 */
public interface PivotSelector<T extends Comparable<T>> {

  int pivot(T[] arr, int low, int high);
}
