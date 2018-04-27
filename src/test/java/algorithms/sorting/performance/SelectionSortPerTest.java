package algorithms.sorting.performance;

import algorithms.sedgewick.sorting.SelectionSort;
import algorithms.sedgewick.sorting.Sort;

public class SelectionSortPerTest extends SortScalePerformanceTest {

  @Override
  protected Sort newInstance() {
    return new SelectionSort();
  }
}
