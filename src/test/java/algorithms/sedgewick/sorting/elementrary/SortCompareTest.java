package algorithms.sedgewick.sorting.elementrary;

import org.junit.Test;

public class SortCompareTest {

  private SortCompare sortCompare = new SortCompare();

  @Test
  public void selectionAndInsertion() throws Exception {
    sortCompare.compare(InsertionSort.class, SelectionSort.class, 1000, 100);
    sortCompare.compare(InsertionSort.class, SelectionSort.class, 10000, 10);
    sortCompare.compare(ShellSort.class, InsertionSort.class, 100000, 10);
  }

  @Test
  public void shellVsMergeTest() throws Exception {
    sortCompare.compare(MergeSort.class, ShellSort.class, 1000000, 10);
  }
}