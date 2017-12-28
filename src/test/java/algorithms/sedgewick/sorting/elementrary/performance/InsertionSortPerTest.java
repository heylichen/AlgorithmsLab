package algorithms.sedgewick.sorting.elementrary.performance;

import algorithms.sedgewick.sorting.elementrary.InsertionSort;
import algorithms.sedgewick.sorting.elementrary.Sort;

public class InsertionSortPerTest extends SortScalePerformanceTest {

  @Override
  protected Sort newInstance() {
    return new InsertionSort();
  }
}
