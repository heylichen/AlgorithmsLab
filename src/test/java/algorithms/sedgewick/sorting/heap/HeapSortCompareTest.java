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
    Sort defaultHeapSort = factory.defaultHeapSort();
    Sort noExchangeHeapSort = factory.noExchangeHeapSort();
    sortCompare(defaultHeapSort, noExchangeHeapSort, middleSizeAndTimesList);
  }

  @Test
  public void multiwayHeapSortTest() throws Exception {
    Sort defaultHeapSort = factory.defaultHeapSort();
    Sort threeWayHeapSort= factory.multiwayHeapSort(3);
    sortCompare(threeWayHeapSort, defaultHeapSort, fastSizeAndTimesList);

    Sort fourWayHeapSort= factory.multiwayHeapSort(4);
    sortCompare(fourWayHeapSort, defaultHeapSort, fastSizeAndTimesList);


    Sort eightWayHeapSort= factory.multiwayHeapSort(8);
    sortCompare(eightWayHeapSort, defaultHeapSort, fastSizeAndTimesList);
  }
}
