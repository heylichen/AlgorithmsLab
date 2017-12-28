package algorithms.sedgewick.sorting.elementrary.performance;

import algorithms.sedgewick.sorting.elementrary.SelectionSort;
import algorithms.sedgewick.sorting.elementrary.Sort;

public class SelectionSortPerTest extends SortScalePerformanceTest {

  @Override
  protected Sort newInstance() {
    return new SelectionSort();
  }
}
