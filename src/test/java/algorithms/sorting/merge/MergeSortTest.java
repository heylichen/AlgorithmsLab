package algorithms.sorting.merge;

import algorithms.sorting.Sort;
import algorithms.sorting.SortTest;
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