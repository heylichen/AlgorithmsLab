package algorithms.sorting.merge;

import algorithms.sedgewick.sorting.AbstractComparableSort;

public class CopyOnceMergeSort<T extends Comparable<T>> extends AbstractComparableSort<T> {

  @Override
  public void sort(T[] arr) {
    if (arr == null || arr.length <= 1) {
      return;
    }
    T[] aux = (T[]) new Comparable[arr.length];
    // int start = getMiddle(0, arr.length - 1);
    int start = 0;
    System.arraycopy(arr, start, aux, start, arr.length - start);
    sort(arr, aux, 0, arr.length - 1);
  }

  protected void sort(T[] arr, T[] aux, int low, int high) {
    if (low >= high) {
      return;
    }
    if (low + 1 == high) {
      if (less(arr[high], arr[low])) {
        exchange(arr, low, high);
      }
      return;
    }
    int middle = getMiddle(low, high);
    sort(aux, arr, low, middle);
    sort(arr, aux, middle + 1, high);
    if (less(arr[middle + 1], arr[middle])) {
      merge(arr, aux, low, middle, high);
    }
  }


  protected void merge(T[] arr, T[] aux, int low, int middle, int high) {
    int i = low;
    int j = middle + 1;
    int k = low;
    while (k <= high) {
      if (i > middle) {
        break;
      } else if (j > high) {
        arr[k++] = aux[i++];
      } else if (less(aux[i], arr[j])) {
        arr[k++] = aux[i++];
      } else {
        arr[k++] = arr[j++];
      }
    }
  }

  protected int getMiddle(int low, int high) {
    return (low + high) / 2;
  }
}