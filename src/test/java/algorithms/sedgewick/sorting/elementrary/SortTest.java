package algorithms.sedgewick.sorting.elementrary;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    sortArrayBySize(10);
  }

  protected void sortArrayBySize(int size) {
    Integer[] dataArr = new Integer[size];
    for (int i = 0; i < dataArr.length; i++) {
      dataArr[i] = dataArr.length - i;
    }
    sort.sort(dataArr);
    Assert.assertTrue(isSorted(dataArr));
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