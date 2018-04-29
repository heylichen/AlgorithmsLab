package algorithms.sorting;

import algorithms.sorting.insertion.InsertionSort;

public class InsertionSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new InsertionSort();
  }
}