package algorithms.sedgewick.sorting.heap;

import java.util.ArrayList;
import java.util.List;

import algorithms.sedgewick.sorting.PriorityQueue;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/2/10.
 */
public abstract class AbstractPriorityTest {

  protected abstract PriorityQueue<Double> newInstance(int capacity);

  @Test
  public void insertTest() {
    for (int i = 0; i < 4; i++) {
      testOnece();
    }
  }

  protected void testOnece() {
    int size = 1000;
    PriorityQueue<Double> pq = newInstance(size);

    List<Double> list = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      Double v = StdRandom.uniform();
      list.add(v);
//      pq.insert((double) (size - i));
    }
    for (Double aDouble : list) {
      pq.insert(aDouble);
    }

    Double[] array = new Double[size];
    int j = 0;
    while (!pq.isEmpty()) {
      array[j++] = pq.pop();
    }
    boolean check = checkSorted(array);
    Assert.assertTrue(check);
  }

  protected boolean checkSorted(Comparable[] a) { // Test whether the array entries are in order.
    if (a == null || a.length == 0) {
      return true;
    }
    for (int i = 1; i < a.length; i++) {
      if (!rightOrder(a[i - 1], a[i])) {
        return false;
      }
    }
    return true;
  }

  protected abstract boolean rightOrder(Comparable k1, Comparable k2);
}
