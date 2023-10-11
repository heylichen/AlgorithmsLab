package algorithms.sorting.quick.pivot;

/**
 * Created by Chen Li on 2018/2/2.
 */
public class NintherPivotSelector<T extends Comparable<T>> extends Median3PivotSelector<T>
    implements PivotSelector<T> {

  @Override
  public int pivot(T[] arr, int low, int high) {
    int p1 = median3(arr, low, low + 1, low + 2);
    int p2 = median3(arr, high - 2, high - 1, high);
    int middle = low + (high - low) / 2;
    int p3 = median3(arr, middle - 1, middle, middle + 1);

    return median3(arr, p1, p3, p2);
  }
}
