package algorithms.sedgewick.sorting.elementrary.performance;

import algorithms.sedgewick.sorting.elementrary.Sort;
import algorithms.sedgewick.sorting.elementrary.SortCompare;
import org.junit.Before;
import org.junit.Test;

public abstract class SortScalePerformanceTest {

  protected Sort sort;
  protected SortCompare sortCompare = new SortCompare();

  protected abstract Sort newInstance();

  @Before
  public void setUp() throws Exception {
    sort = newInstance();
  }

  @Test
  public void scaleTest() throws InstantiationException, IllegalAccessException {
    sortCompare.scaleSortPerformance(sort.getClass(), 1000, 20000, 10);
  }
}