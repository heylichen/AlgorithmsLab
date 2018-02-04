package algorithms.sedgewick.sorting.merge;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.merge.MergeSort;
import org.junit.Test;

public class MergeSortTest extends SortTest {
  @Override
  protected Sort newInstance() {
    return new MergeSort();
  }

  @Test
  public void sortLargeArrayTest() {
    sortArrayBySize(10000);
  }
}