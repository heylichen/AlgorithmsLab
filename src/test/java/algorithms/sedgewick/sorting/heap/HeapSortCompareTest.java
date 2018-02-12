package algorithms.sedgewick.sorting.heap;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.compare.AbstractSortCompareTest;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/2/11.
 */
public class HeapSortCompareTest extends AbstractSortCompareTest {

  private HeapSortFactory factory = new HeapSortFactory();
  @Test
  public void lessExchHeapSortTest() throws Exception {
    Sort cutoffQuickSort = factory.defaultHeapSort();
    Sort quick = factory.lessExchHeapSort();
    sortCompare(cutoffQuickSort, quick, middleSizeAndTimesList);
  }

}
