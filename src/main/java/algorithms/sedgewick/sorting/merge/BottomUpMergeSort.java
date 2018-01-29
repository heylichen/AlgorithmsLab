package algorithms.sedgewick.sorting.merge;

import algorithms.sedgewick.sorting.AbstractSort;

public class BottomUpMergeSort extends AbstractSort {

  private Comparable[] aux;

  @Override
  public void sort(Comparable[] arr) {
    if (arr == null || arr.length <= 1) {
      return;
    }
    aux = new Comparable[arr.length];

    int unit = 1;
    while (unit < arr.length) {
      //do merge sub arrays
      mergeSubArrays(arr, unit);
      unit += unit;
    }
  }

  protected void mergeSubArrays(Comparable[] arr, int unit) {
    for (int i = 0; i + unit < arr.length; ) {
      int right = i + unit * 2 - 1;
      int high = right >= arr.length ? arr.length - 1 : right;
      int middle = i + unit - 1;
      merge(arr, i, middle, high);
      i = high + 1;
    }
  }

  protected void merge(Comparable[] arr, int low, int middle, int high) {
    System.arraycopy(arr, low, aux, low, high - low + 1);
    int i = low;
    int j = middle + 1;
    int k = low;
    while (k <= high) {
      if (i > middle) {
        arr[k++] = aux[j++];
      } else if (j > high) {
        arr[k++] = aux[i++];
      } else if (less(aux[i], aux[j])) {
        arr[k++] = aux[i++];
      } else {
        arr[k++] = aux[j++];
      }
    }
  }
}
