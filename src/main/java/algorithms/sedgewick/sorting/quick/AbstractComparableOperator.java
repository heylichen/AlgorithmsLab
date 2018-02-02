package algorithms.sedgewick.sorting.quick;

/**
 * Created by Chen Li on 2018/2/1.
 */
public abstract class AbstractComparableOperator<T extends Comparable<T>> {

  protected boolean less(T a, T b) {
    return a.compareTo(b) < 0;
  }

  protected void exchange(T[] arr, int i, int j) {
    if (i == j) {
      return;
    }
    T t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }
}
