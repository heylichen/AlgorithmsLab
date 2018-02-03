package algorithms.sedgewick.sorting.performance;

import algorithms.sedgewick.sorting.ShellSort;
import algorithms.sedgewick.sorting.Sort;
import org.junit.Test;

public class ShellSortPerTest extends SortScalePerformanceTest {

  @Override
  protected Sort newInstance() {
    return new ShellSort();
  }

  @Test
  public void scaleTest() throws InstantiationException, IllegalAccessException {
    sortCompare.scaleSortPerformance(sort, 1000, 320000, 10);
  }
}
