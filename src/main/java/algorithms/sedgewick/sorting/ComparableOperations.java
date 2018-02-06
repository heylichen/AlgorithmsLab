package algorithms.sedgewick.sorting;

/**
 * Created by Chen Li on 2018/2/6.
 */
public class ComparableOperations<T extends Comparable<T>> {

  public boolean less(T a, T b) {
    return a.compareTo(b) < 0;
  }

  public void exchange(T[] arr, int i, int j) {
    if (i == j) {
      return;
    }
    T t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }
}
