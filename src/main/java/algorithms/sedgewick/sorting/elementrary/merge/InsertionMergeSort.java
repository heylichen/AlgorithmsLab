package algorithms.sedgewick.sorting.elementrary.merge;

import algorithms.sedgewick.sorting.elementrary.AbstractSort;
import lombok.Getter;
import lombok.Setter;

public class InsertionMergeSort extends AbstractSort {

  private Comparable[] aux;
  @Getter
  @Setter
  private int smallSize = 16;
  private InsertionSort insertionSort = new InsertionSort();

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
    if (high - low + 1 <= smallSize) {
      insertionSort.sort(arr, low, high);
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


  public class InsertionSort {

    public void sort(Comparable[] arr, int low, int high) {
      if (low >= high) {
        return;
      }
      for (int i = low + 1; i <= high; i++) {
        Comparable t = arr[i];
        //find j so that a[i] >= a[j], a[i] should be put in index j+1
        //all elements from index j+1 to i-1 should be shift right
        int j = i - 1;
        for (; j >= low; j--) {
          if (!less(t, arr[j])) {
            break;
          }
        }
        j++;
        shiftRight(arr, j, i - 1);
        arr[j] = t;
      }
    }

    protected void shiftRight(Comparable[] arr, int from, int to) {
      if (from > to) {
        return;
      }
      for (int k = to; k >= from; k--) {
        arr[k + 1] = arr[k];
      }
    }
  }

}
