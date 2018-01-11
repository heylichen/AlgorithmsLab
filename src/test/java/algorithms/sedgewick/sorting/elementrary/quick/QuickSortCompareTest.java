package algorithms.sedgewick.sorting.elementrary.quick;

import algorithms.sedgewick.sorting.elementrary.SortCompare;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/1/10.
 */
public class QuickSortCompareTest {
  private SortCompare sortCompare = new SortCompare();

  @Test
  public void insertionQuickTest() throws Exception {
    sortCompare.compare(InsertionQuickSort.class, QuickSort.class, 100000, 5);
    sortCompare.compare(QuickSort.class, InsertionQuickSort.class, 100000, 5);

    sortCompare.compare(InsertionQuickSort.class, QuickSort.class, 400000, 5);
    sortCompare.compare(QuickSort.class, InsertionQuickSort.class, 400000, 5);
  }

  @Test
  public void median3QuickTest() throws Exception {
    sortCompare.compare(Median3QuickSort.class, QuickSort.class, 100000, 5);
    sortCompare.compare(QuickSort.class, Median3QuickSort.class, 100000, 5);

    sortCompare.compare(Median3QuickSort.class, QuickSort.class, 400000, 5);
    sortCompare.compare(QuickSort.class, Median3QuickSort.class, 400000, 5);
  }
}
