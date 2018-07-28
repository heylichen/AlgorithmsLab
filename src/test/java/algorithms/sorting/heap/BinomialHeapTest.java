package algorithms.sorting.heap;

import org.junit.Test;

/**
 * Created by Chen Li on 2018/7/28.
 */
public class BinomialHeapTest {

  @Test
  public void name() {
    BinomialHeap<Integer> bh = new BinomialHeap<>();
    for (int i = 1; i <= 4; i++) {
      bh.insert(i);
    }
    System.out.println("ok");

  }
}