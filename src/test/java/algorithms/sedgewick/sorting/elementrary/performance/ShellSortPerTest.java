package algorithms.sedgewick.sorting.elementrary.performance;

import algorithms.sedgewick.sorting.elementrary.ShellSort;
import algorithms.sedgewick.sorting.elementrary.Sort;
import org.junit.Test;

public class ShellSortPerTest extends SortScalePerformanceTest {

  @Override
  protected Sort newInstance() {
    return new ShellSort();
  }

  @Test
  public void scaleTest() throws InstantiationException, IllegalAccessException {
    sortCompare.scaleSortPerformance(sort.getClass(), 1000, 320000, 10);
  }
}
