package algorithms.sorting.heap;

import algorithms.sorting.Sort;
import algorithms.sorting.SortTest;

/**
 * Created by Chen Li on 2018/2/16.
 */
public class LessCompareHeapSortTest extends SortTest {

  @Override
  protected Sort newInstance() {
    return new HeapSortFactory().lessCompareHeapSort();
  }
}