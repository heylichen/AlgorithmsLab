package algorithms.sedgewick.sorting.function;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.merge.BottomUpMergeSort;
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