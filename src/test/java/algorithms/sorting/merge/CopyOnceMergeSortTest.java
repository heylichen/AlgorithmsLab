package algorithms.sorting.merge;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.SortTest;
import org.junit.Test;

public class CopyOnceMergeSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new CopyOnceMergeSort();
  }

  @Test
  public void sortLargeArrayTest() {
    sortArrayBySize(10000);
  }
}