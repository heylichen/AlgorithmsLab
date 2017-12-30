package algorithms.sedgewick.sorting.elementrary.function;

import algorithms.sedgewick.sorting.elementrary.MergeSort;
import algorithms.sedgewick.sorting.elementrary.Sort;
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