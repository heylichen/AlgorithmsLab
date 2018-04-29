package algorithms.sorting;

import algorithms.sorting.compare.SortTestContext;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Chen Li on 2018/4/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/root-context.xml")
public class AbstractContextSortTest<T extends Comparable<T>> {

  protected void sortTest(SortTestContext<T>... contexts) {
    for (SortTestContext<T> context : contexts) {
      int size = 10;
      for (int i = 1; i <= 3; i++) {
        sortTest(context, size);
        size = size * 10;
      }
    }
  }

  private void sortTest(SortTestContext<T> context, int size) {
    T[] data = context.getDataGenerator().generate(size);
    context.getSort().sort(data);
    Assert.assertTrue(isSorted(data));
  }

  private boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
    if (a == null || a.length == 0) {
      return true;
    }
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i - 1])) {
        return false;
      }
    }
    return true;
  }

  protected boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
  }
}
