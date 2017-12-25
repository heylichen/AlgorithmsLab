package algorithms.sedgewick.sorting.elementrary;

public class SelectionSort extends AbstractSort {
  @Override
  public void sort(Comparable[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int min = i;
      for (int j = i + 1; j < arr.length; j++) {
        if(less(arr[j],arr[min])){
          min = j;
        }
      }
      exchange(arr, i, min);
    }
  }
}
