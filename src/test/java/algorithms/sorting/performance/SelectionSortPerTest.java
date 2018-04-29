package algorithms.sorting.performance;

import algorithms.sorting.SelectionSort;
import algorithms.sorting.Sort;

public class SelectionSortPerTest extends SortScalePerformanceTest {

  @Override
  protected Sort newInstance() {
    return new SelectionSort();
  }
}
