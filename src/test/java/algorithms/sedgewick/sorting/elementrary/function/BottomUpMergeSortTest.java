package algorithms.sedgewick.sorting.elementrary.function;

import algorithms.sedgewick.sorting.elementrary.Sort;
import algorithms.sedgewick.sorting.elementrary.merge.BottomUpMergeSort;
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