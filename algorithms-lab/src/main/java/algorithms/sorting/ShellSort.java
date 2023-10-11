package algorithms.sorting;

public class ShellSort<T extends Comparable<T>> extends AbstractComparableSort<T> {

  @Override
  public void sort(T[] arr) {
    if (arr == null || arr.length <= 1) {
      return;
    }
    int h = getMaxSequenceLength(arr.length);
    while (h >= 1) {
      partialSort(arr, h);
      h = h / 3;
    }
  }

  protected void partialSort(T[] arr, int h) {
    for (int i = h; i < arr.length; i++) {

      int j = i - h;
      T currentValue = arr[i];
      while (j >= 0 && less(currentValue, arr[j])) {
        j = j - h;
      }
      j = j + h;
      shiftRight(arr, j, i - h, h);
      arr[j] = currentValue;
    }
  }

  protected void shiftRight(T[] arr, int from, int to, int step) {
    if (from > to) {
      return;
    }
    for (int k = to; k >= from; ) {
      arr[k + step] = arr[k];
      k = k - step;
    }
  }

  protected int getMaxSequenceLength(int size) {
    int h = 1;
    size = size / 3;
    while (h < size) {
      h = h * 3 + 1;
    }
    return h;
  }
}
