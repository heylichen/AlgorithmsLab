package algorithms.sedgewick.sorting.quick;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.function.SortTest;
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