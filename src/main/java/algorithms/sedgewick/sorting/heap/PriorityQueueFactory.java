package algorithms.sedgewick.sorting.heap;

import algorithms.sedgewick.sorting.PriorityQueue;

/**
 * Created by Chen Li on 2018/2/10.
 */
public class PriorityQueueFactory {

  public PriorityQueue minPQ(int capacity) {
    DefaultPriorityQueue pq = new DefaultPriorityQueue(capacity);
    HeapOperations operations = new DefaultMinHeapOperations();
    pq.setHeapOperations(operations);
    return pq;
  }

  public PriorityQueue maxPQ(int capacity) {
    DefaultPriorityQueue pq = new DefaultPriorityQueue(capacity);
    HeapOperations operations = new DefaultMaxHeapOperations();
    pq.setHeapOperations(operations);
    return pq;
  }

  public PriorityQueue lessExchMaxPQ(int capacity) {
    DefaultPriorityQueue pq = new DefaultPriorityQueue(capacity);
    HeapOperations operations = new LessExchHeapOperations();
    pq.setHeapOperations(operations);
    return pq;
  }
}
