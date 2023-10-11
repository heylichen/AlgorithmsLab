package algorithms.sorting.heap;

import algorithms.sorting.PriorityQueue;
import algorithms.sorting.heap.operations.HeapOperations;
import algorithms.sorting.heap.operations.HeapOperationsFactory;

/**
 * Created by Chen Li on 2018/2/10.
 */
public class PriorityQueueFactory {

  HeapOperationsFactory heapOperationsFactory = new HeapOperationsFactory();

  public PriorityQueue minPQ(int capacity) {
    DefaultPriorityQueue pq = new DefaultPriorityQueue(capacity);
    HeapOperations operations = heapOperationsFactory.basicMinHeapOperations();
    pq.setHeapOperations(operations);
    return pq;
  }

  public PriorityQueue maxPQ(int capacity) {
    DefaultPriorityQueue pq = new DefaultPriorityQueue(capacity);
    HeapOperations operations = heapOperationsFactory.basicMaxHeapOperations();
    pq.setHeapOperations(operations);
    return pq;
  }

  public PriorityQueue lessExchMaxPQ(int capacity) {
    DefaultPriorityQueue pq = new DefaultPriorityQueue(capacity);
    HeapOperations operations = heapOperationsFactory.noExchangeMaxHeapOperations();
    pq.setHeapOperations(operations);
    return pq;
  }
}
