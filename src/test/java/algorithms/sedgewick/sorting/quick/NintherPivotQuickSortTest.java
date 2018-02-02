package algorithms.sedgewick.sorting.quick;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.function.SortTest;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/2/1.
 */
public class NintherPivotQuickSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    QuickSortFactory factory = new QuickSortFactory();
    return factory.nintherQuickSort();
  }

  @Test
  public void sortLargeArrayTest() {
    sortArrayBySize(1000);
  }
}