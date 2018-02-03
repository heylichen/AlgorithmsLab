package algorithms.sedgewick.sorting.performance;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.compare.RandomArraySortCompare;
import org.junit.Before;
import org.junit.Test;

public abstract class SortScalePerformanceTest {

  protected Sort sort;
  protected RandomArraySortCompare sortCompare = new RandomArraySortCompare();

  protected abstract Sort newInstance();

  @Before
  public void setUp() throws Exception {
    sort = newInstance();
  }

  @Test
  public void scaleTest() throws InstantiationException, IllegalAccessException {
    sortCompare.scaleSortPerformance(sort, 1000, 20000, 10);
  }
}