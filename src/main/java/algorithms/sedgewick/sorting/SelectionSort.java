package algorithms.sedgewick.sorting;

public class SelectionSort<T extends Comparable<T>> extends AbstractComparableSort<T> {
  @Override
  public void sort(T[] arr) {
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
