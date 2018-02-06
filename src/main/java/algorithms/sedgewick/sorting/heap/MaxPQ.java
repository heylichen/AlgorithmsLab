package algorithms.sedgewick.sorting.heap;

import algorithms.sedgewick.sorting.PriorityQueue;

/**
 * Created by Chen Li on 2018/2/5.
 */
public class MaxPQ<K extends Comparable<K>> implements PriorityQueue<K> {

  private K[] keys;
  private int size = 0;
  private BinaryHeapOperations operation = new BinaryHeapOperations();

  public MaxPQ(K[] keys) {
    this.keys = (K[]) new Object[keys.length + 1];
    System.arraycopy(keys, 0, this.keys, 1, keys.length);
  }

  public MaxPQ(int capacity) {
    this.keys = (K[]) new Comparable[capacity + 1];
  }

  @Override
  public void insert(K key) {
    int index = size + 1;
    keys[index] = key;
    //swim
    operation.swim(keys,index,1);
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
    size--;
    //sink
    operation.sink(keys,1,size);
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
