package algorithms.sedgewick.strings.sorts;

import algorithms.sedgewick.sorting.AbstractContextSortTest;
import algorithms.sedgewick.sorting.compare.SortTestContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Chen Li on 2018/4/6.
 */
public class ThreeWayStringQuickSortTest extends AbstractContextSortTest<String> {

  @Autowired
  private SortTestContext<String> stringSortContext;

  @Autowired
  private SortTestContext<String> equalStringSortContext;

  @Test
  public void doTest() {
    ThreeWayStringQuickSort sort = new ThreeWayStringQuickSort();
    stringSortContext.setSort(sort);
    equalStringSortContext.setSort(sort);
    sortTest(equalStringSortContext, stringSortContext);
  }
}