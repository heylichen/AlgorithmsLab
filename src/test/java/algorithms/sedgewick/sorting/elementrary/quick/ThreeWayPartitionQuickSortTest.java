package algorithms.sedgewick.sorting.elementrary.quick;

import algorithms.sedgewick.sorting.elementrary.Sort;
import algorithms.sedgewick.sorting.elementrary.function.SortTest;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/1/11.
 */
public class ThreeWayPartitionQuickSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new ThreeWayPartitionQuickSort();
  }

  @Test
  public void sortLargeArrayTest() {
    sortArrayBySize(1000);
  }
}