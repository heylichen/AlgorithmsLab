package algorithms.sorting.performance;

import algorithms.sorting.Sort;
import algorithms.sorting.insertion.InsertionSort;

public class InsertionSortPerTest extends SortScalePerformanceTest {

  @Override
  protected Sort newInstance() {
    return new InsertionSort();
  }
}
