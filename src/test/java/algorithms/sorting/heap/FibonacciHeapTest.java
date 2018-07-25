package algorithms.sorting.heap;

import java.util.Arrays;
import java.util.List;

import algorithms.dynamicprog.FibonacciHeap;
import org.junit.Test;

/**
 * @Author lichen2@tuniu.com
 * @Date 2018/7/25 15:10
 * @Description
 */
public class FibonacciHeapTest {


  @Test
  public void mergeTest() {
    FibonacciHeap<Integer> fh = new FibonacciHeap();
    List<Integer> list = Arrays.asList(3, 18, 39, 52);
    for (Integer integer : list) {
      fh.insert(integer);
    }
    System.out.println("ok");
  }

  @Test
  public void addTest() {
    FibonacciHeap<Integer> fh = new FibonacciHeap();
    List<Integer> list = Arrays.asList(3, 38, 18, 39, 52, 41);
    for (Integer integer : list) {
      fh.insert(integer);
    }
    fh.deleteMin();
    System.out.println("ok");

  }
}
