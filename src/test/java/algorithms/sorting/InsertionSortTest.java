package algorithms.sorting;

import algorithms.sedgewick.sorting.insertion.InsertionSort;

public class InsertionSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new InsertionSort();
  }
}