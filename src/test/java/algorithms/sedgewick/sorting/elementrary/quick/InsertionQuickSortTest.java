package algorithms.sedgewick.sorting.elementrary.quick;

import algorithms.sedgewick.sorting.elementrary.Sort;
import algorithms.sedgewick.sorting.elementrary.function.SortTest;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/1/10.
 */
public class InsertionQuickSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new InsertionQuickSort();
  }

  @Test
  public void sortLargeArrayTest() {
    sortArrayBySize(1000);
  }
}