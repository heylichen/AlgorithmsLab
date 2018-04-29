package algorithms.sorting.quick;

import algorithms.sorting.Sort;
import algorithms.sorting.SortTest;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/1/28.
 */
public class FastThreeWayPartitionQuickSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    QuickSortFactory factory = new QuickSortFactory();
    return factory.fast3WayPartitionQuickSort();
  }

  @Test
  public void sortLargeArrayTest() {
    sortArrayBySize(1000);
  }
}