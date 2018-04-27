package algorithms.sorting;

/**
 * Created by Chen Li on 2018/2/5.
 */
public interface PriorityQueue<K extends Comparable<K>> {

  void insert(K key);

  K peek();

  K pop();

  boolean isEmpty();

  int size();

}
