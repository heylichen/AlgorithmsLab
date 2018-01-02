package algorithms.sedgewick.sorting.elementrary.function;

import algorithms.sedgewick.sorting.elementrary.Sort;
import algorithms.sedgewick.sorting.elementrary.merge.CopyOnceMergeSort;
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