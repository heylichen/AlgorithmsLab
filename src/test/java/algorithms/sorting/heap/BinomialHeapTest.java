package algorithms.sorting.heap;

import org.junit.Assert;
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

  @Test
  public void deleteMin() {
    BinomialHeap<Integer> bh = new BinomialHeap<>();
    BinomialHeapNode<Integer> n5;
    for (int i = 1; i <= 8; i++) {
      BinomialHeapNode<Integer> n = bh.insert(9 - i);
    }
    for (int i = 1; i <= 8; i++) {
      Assert.assertEquals("", i, bh.deleteMin().getKey().intValue());
    }
    System.out.println("ok");
  }
}