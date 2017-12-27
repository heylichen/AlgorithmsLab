package algorithms.sedgewick.sorting.elementrary;

import org.junit.Test;

public class SortCompareTest {

  private SortCompare sortCompare = new SortCompare();

  @Test
  public void scalePerformanceTest() throws Exception {
    sortCompare.scaleSortPerformance(InsertionSort.class, 1000, 20000, 10);
    sortCompare.scaleSortPerformance(SelectionSort.class, 1000, 20000, 10);
  }

  @Test
  public void selectionAndInsertion() throws Exception {
    sortCompare.compare(InsertionSort.class, SelectionSort.class, 1000, 100);
    sortCompare.compare(InsertionSort.class, SelectionSort.class, 10000, 10);
  }
}