package algorithms.sedgewick.sorting.elementrary.merge;

import algorithms.sedgewick.sorting.elementrary.AbstractSort;

public class MergeSort extends AbstractSort {
  private Comparable[] aux;

  @Override
  public void sort(Comparable[] arr) {
    if (arr == null || arr.length <= 1) {
      return;
    }
    aux = new Comparable[arr.length];
    sort(arr, 0, arr.length - 1);
  }

  protected void sort(Comparable[] arr, int low, int high) {
    if (low >= high) {
      return;
    }
    if (low + 1 == high) {
      if (less(arr[high], arr[low])) {
        exchange(arr, low, high);
      }
      return;
    }
    int middle = (low + high) / 2;
    sort(arr, low, middle);
    sort(arr, middle + 1, high);
    if (less(arr[middle + 1], arr[middle])) {
      merge(arr, low, middle, high);
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
