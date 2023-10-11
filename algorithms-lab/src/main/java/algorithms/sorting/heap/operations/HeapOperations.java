package algorithms.sorting.heap.operations;

/**
 * Created by Chen Li on 2018/2/10.
 */
public interface HeapOperations<K extends Comparable<K>> {

  void swim(K[] keys, int from, int to);

  void sink(K[] keys, int from, int to);

  int getParent(int child);

  int getMaxPriorityChildOf(K[] keys, int index, int rightBound);

  int getCompares();
}
