package algorithms.sedgewick.sorting.function;

import algorithms.sedgewick.sorting.insertion.InsertionSort;
import algorithms.sedgewick.sorting.Sort;

public class InsertionSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new InsertionSort();
  }
}