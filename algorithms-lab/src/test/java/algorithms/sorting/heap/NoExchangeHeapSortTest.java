package algorithms.sorting.heap;

import algorithms.sorting.Sort;
import algorithms.sorting.SortTest;

/**
 * Created by Chen Li on 2018/2/11.
 */
public class NoExchangeHeapSortTest extends SortTest {

  @Override
  protected Sort newInstance() {
    return new HeapSortFactory().noExchangeHeapSort();
  }
}
