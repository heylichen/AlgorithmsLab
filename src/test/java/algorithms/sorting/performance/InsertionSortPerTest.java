package algorithms.sorting.performance;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.insertion.InsertionSort;

public class InsertionSortPerTest extends SortScalePerformanceTest {

  @Override
  protected Sort newInstance() {
    return new InsertionSort();
  }
}
