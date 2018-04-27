package algorithms.sorting.merge;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.SortTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class InsertionMergeSortTest extends SortTest {

  @Override
  protected Sort newInstance() {
    return new InsertionMergeSort();
  }

  @Test
  public void sortLargeArrayTest() {
    long start = System.currentTimeMillis();
    sortArrayBySize(200000);
    long end = System.currentTimeMillis();
    System.out.println(end - start);
  }
}