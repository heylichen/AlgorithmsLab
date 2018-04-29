package algorithms.sorting.quick;

import algorithms.sorting.quick.pivot.PivotSelector;
import algorithms.sorting.quick.pivot.RandomPivotSelector;
import lombok.Data;

/**
 * Created by Chen Li on 2018/1/31.
 */
@Data
public class QuickSelect {
  private PivotSelector pivotSelector = new RandomPivotSelector();

  public <T extends Comparable<T>> int select(T[] arr, int low, int high, int k) {
    int i = low;
    int j = high;
    while (true) {
      if (i == j) {
        return i;
      }
      int pivotIndex = pivotSelector.pivot(arr, i, j);
      pivotIndex = partition(arr, i, j, pivotIndex);
      if (pivotIndex == k) {
        return pivotIndex;
      } else if (pivotIndex < k) {
        i = pivotIndex + 1;
      } else {
        j = pivotIndex - 1;
      }
    }
  }

  protected <T extends Comparable<T>> int partition(T[] arr, int low, int high, int pivotIndex) {
    T pivotValue = arr[pivotIndex];
    exchange(arr, pivotIndex, high);
    int index = low;
    for (int i = low; i < high; i++) {
      if (arr[i].compareTo(pivotValue) < 0) {
        exchange(arr, i, index);
        index++;
      }
    }
    exchange(arr, index, high);
    return index;
  }

  private void exchange(Object[] arr, int i, int j) {
    if (i == j) {
      return;
    }
    Object temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
