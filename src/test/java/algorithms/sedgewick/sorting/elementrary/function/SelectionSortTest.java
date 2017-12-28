package algorithms.sedgewick.sorting.elementrary.function;

import algorithms.sedgewick.sorting.elementrary.SelectionSort;
import algorithms.sedgewick.sorting.elementrary.Sort;

public class SelectionSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new SelectionSort();
  }
}
