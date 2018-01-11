package algorithms.sedgewick.sorting.elementrary.function;

import algorithms.sedgewick.sorting.elementrary.insertion.InsertionSort;
import algorithms.sedgewick.sorting.elementrary.Sort;

public class InsertionSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new InsertionSort();
  }
}