package algorithms.sorting.heap;

import algorithms.sedgewick.sorting.PriorityQueue;
import algorithms.sedgewick.sorting.heap.operations.HeapOperations;
import algorithms.sorting.heap.operations.HeapOperations;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/2/5.
 */
public class DefaultPriorityQueue<K extends Comparable<K>> implements PriorityQueue<K> {

  private K[] keys;
  private int size = 0;
  @Setter
  private HeapOperations<K> heapOperations;

  public DefaultPriorityQueue(K[] keys) {
    this.keys = (K[]) new Object[keys.length + 1];
    System.arraycopy(keys, 0, this.keys, 1, keys.length);
  }

  public DefaultPriorityQueue(int capacity) {
    this.keys = (K[]) new Comparable[capacity + 1];
  }

  @Override
  public void insert(K key) {
    int index = size + 1;
    keys[index] = key;
    //swim
    heapOperations.swim(keys, index, 1);
    size++;
  }

  @Override
  public K peek() {
    return keys[1];
  }

  @Override
  public K pop() {
    K v = keys[1];
    keys[1] = keys[size];
    keys[size] = null;
    size--;
    //sink
    heapOperations.sink(keys, 1, size);
    return v;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }
}
