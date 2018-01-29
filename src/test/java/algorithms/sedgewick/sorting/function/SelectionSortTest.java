package algorithms.sedgewick.sorting.function;

import algorithms.sedgewick.sorting.SelectionSort;
import algorithms.sedgewick.sorting.Sort;

public class SelectionSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new SelectionSort();
  }
}
