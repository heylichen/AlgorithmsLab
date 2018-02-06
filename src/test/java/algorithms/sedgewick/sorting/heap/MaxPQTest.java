package algorithms.sedgewick.sorting.heap;

import org.junit.Test;

/**
 * Created by Chen Li on 2018/2/5.
 */
public class MaxPQTest {

  @Test
  public void insertTest() {
    MaxPQ pq = new MaxPQ(100);
    int size = 100;
    for (int i = 0; i < size; i++) {
      pq.insert(size - i);
    }

    while(!pq.isEmpty()){
      System.out.print(pq.pop()+"\t");
    }

  }
}