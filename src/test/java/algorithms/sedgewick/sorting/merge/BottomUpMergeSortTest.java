package algorithms.sedgewick.sorting.merge;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.SortTest;
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