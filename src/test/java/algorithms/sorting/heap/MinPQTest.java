package algorithms.sorting.heap;

import algorithms.sedgewick.sorting.PriorityQueue;

/**
 * Created by Chen Li on 2018/2/5.
 */
public class MinPQTest extends AbstractPriorityTest {

  @Override
  protected PriorityQueue<Double> newInstance(int capacity) {
    PriorityQueueFactory factory = new PriorityQueueFactory();
    PriorityQueue<Double> pq = factory.minPQ(capacity);
    return pq;
  }

  @Override
  protected boolean rightOrder(Comparable k1, Comparable k2) {
    return k1.compareTo(k2) <= 0;
  }
}