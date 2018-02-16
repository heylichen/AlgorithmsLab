package algorithms.sedgewick.sorting.heap;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.SortTest;

/**
 * Created by Chen Li on 2018/2/16.
 */
public class LessCompareHeapSortTest extends SortTest {

  @Override
  protected Sort newInstance() {
    return new HeapSortFactory().lessCompareHeapSort( );
  }
}