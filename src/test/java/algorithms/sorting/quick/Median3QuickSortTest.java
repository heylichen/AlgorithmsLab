package algorithms.sorting.quick;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.SortTest;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/1/11.
 */
public class Median3QuickSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    QuickSortFactory factory = new QuickSortFactory();
    return factory.median3QuickSort();
  }

  @Test
  public void sortLargeArrayTest() {
    sortArrayBySize(1000);
  }
}