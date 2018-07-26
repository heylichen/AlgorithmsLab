package algorithms.sorting.heap;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @Author lichen2@tuniu.com
 * @Date 2018/7/25 15:10
 * @Description
 */
public class FibonacciHeapTest {


  @Test
  public void mergeTest() {
    FibonacciHeap<Integer> fh = new FibonacciHeap(Integer.MIN_VALUE);
    List<Integer> list = Arrays.asList(3, 18, 39, 52);
    for (Integer integer : list) {
      fh.insert(integer);
    }
    System.out.println("ok");
  }

  @Test
  public void addTest() {
    FibonacciHeap<Integer> fh = new FibonacciHeap(Integer.MIN_VALUE);
    List<Integer> list = Arrays.asList(3, 38, 18, 39, 52, 41);
    for (Integer integer : list) {
      fh.insert(integer);
    }
    fh.deleteMin();
    System.out.println("ok");

  }

  @Test
  public void decreaseKeyTest() {
    FibonacciHeap<Integer> fh = new FibonacciHeap(Integer.MIN_VALUE);
    List<Integer> list = Arrays.asList(1, 26, 35, 24, 46, 7, 17, 30, 23, 27, 36, 37, 38, 39);
    FibonacciHeapNode<Integer> n26 = null;
    FibonacciHeapNode<Integer> n36 = null;
    for (Integer integer : list) {
      FibonacciHeapNode<Integer> n = fh.insert(integer);
      if (integer.intValue() == 26) {
        n26 = n;
      } else if (integer.intValue() == 36) {
        n36 = n;
      }
    }

    fh.deleteMin();

    fh.decreaseKey(n26, 22);
    fh.decreaseKey(n36, 21);
    System.out.println("ok");
  }
}
