package algorithms.strings.sorts;

/**
 * Created by Chen Li on 2018/1/10.
 */
public class SubstringInsertionSort {


  public void sort(String[] arr, int low, int high, int index) {
    if (low >= high) {
      return;
    }
    for (int i = low + 1; i <= high; i++) {
      String t = arr[i];
      //find j so that a[i] >= a[j], a[i] should be put in index j+1
      //all elements from index j+1 to i-1 should be shift right
      int j = i - 1;
      for (; j >= low; j--) {
        if (!less(t, arr[j], index)) {
          break;
        }
      }
      j++;
      shiftRight(arr, j, i - 1);
      arr[j] = t;
    }
  }

  protected void shiftRight(String[] arr, int from, int to) {
    if (from > to) {
      return;
    }
    for (int k = to; k >= from; k--) {
      arr[k + 1] = arr[k];
    }
  }

  protected boolean less(String a, String b, int index) {
    return a.substring(index).compareTo(b.substring(index)) < 0;
  }
}
