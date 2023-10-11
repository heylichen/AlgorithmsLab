package algorithms.sorting.merge;

import algorithms.sorting.Sort;
import algorithms.sorting.SortTest;
import org.junit.Test;

public class BottomUpMergeSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new BottomUpMergeSort();
  }

  @Test
  public void sortLargeArrayTest() {
    sortArrayBySize(10000);
  }
}