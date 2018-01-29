package algorithms.sedgewick.sorting;

public abstract class AbstractSort implements Sort {
  protected boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }

  protected void exchange(Comparable[] arr, int i, int j) {
//    Preconditions.checkArgument(i >= 0 && i < arr.length, "array index i out of bounds!");
//    Preconditions.checkArgument(j >= 0 && j < arr.length, "array index j out of bounds!");
    if (i == j) {
      return;
    }
    Comparable t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }
}
