package algorithms.sedgewick.sorting.elementrary;

public class InsertionSort extends AbstractSort {
  @Override
  public void sort(Comparable[] arr) {
    if (arr == null || arr.length == 1) {
      return;
    }
    for (int i = 1; i < arr.length; i++) {
      Comparable t = arr[i];
      int j = 0;
      for (; j <= i; j++) {
        if (less(t, arr[j])) {
          break;
        }
      }
      if (j == i) {
        //no shift
      } else {
        shiftRight(arr, j, i - 1);
      }
      arr[j] = t;
    }
  }

  protected void shiftRight(Comparable[] arr, int from, int to) {
    for (int k = to; k > from; k--) {
      arr[k + 1] = arr[k];
    }
  }
}
