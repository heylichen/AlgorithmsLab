package algorithms.sedgewick.sorting;

import algorithms.sedgewick.sorting.Sort;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public abstract class SortTest {
  private Sort sort;

  protected abstract Sort newInstance();

  @Before
  public void setUp() throws Exception {
    sort = newInstance();
  }

  @Test
  public void sortTest() {
    sortArrayBySize(0);
    sortArrayBySize(1);
    sortArrayBySize(1000);
  }

  @Test
  public void entropyOptimalSortTest() {
    Integer[] dataArr = new Integer[14];
    for (int i = 0; i < 10; i++) {
      dataArr[i] = 4;
    }
    dataArr[10] = 1;
    dataArr[11] = 2;
    dataArr[12] = 5;
    dataArr[13] = 6;
    sort.sort(dataArr);
    Assert.assertTrue(isSorted(dataArr));
  }

  protected void sortArrayBySize(int size) {
    Integer[] dataArr = new Integer[size];
    for (int i = 0; i < dataArr.length; i++) {
      dataArr[i] = dataArr.length - i;
    }
    Integer[] backup = new Integer[size];
    System.arraycopy(dataArr, 0, backup, 0, size);
    sort.sort(dataArr);
    Assert.assertTrue(isSorted(dataArr));
    CollectionUtils.isEqualCollection(Arrays.asList(dataArr), Arrays.asList(backup));
  }

  public void show(Comparable[] a) { // Print the array, on a single line.
    for (int i = 0; i < a.length; i++)
      System.out.print(a[i] + " ");
    System.out.println();
  }

  public boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
    if (a == null || a.length == 0) {
      return true;
    }
    for (int i = 1; i < a.length; i++)
      if (less(a[i], a[i - 1])) return false;
    return true;
  }

  protected boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }
}