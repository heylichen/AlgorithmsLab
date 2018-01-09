package algorithms.sedgewick.sorting.elementrary.quick;

import algorithms.sedgewick.sorting.elementrary.Sort;
import algorithms.sedgewick.sorting.elementrary.function.SortTest;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/1/9.
 */
public class QuickSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new QuickSort();
  }

  @Test
  public void sortLargeArrayTest() {
    sortArrayBySize(1000);
  }
}