package algorithms.sedgewick.sorting.heap;

/**
 * Created by Chen Li on 2018/2/10.
 */
public abstract class AbstractHeapOperations<K extends Comparable<K>> implements HeapOperations<K> {

  public void swim(K[] keys, int from, int to) {
    int index = from;
    int parentIndex = index / 2;
    while (parentIndex >= to && isHigherPriority(keys[index], keys[parentIndex])) {
      exchange(keys, parentIndex, index);
      index = parentIndex;
      parentIndex = parentIndex / 2;
    }
  }

  public void sink(K[] keys, int from, int to) {
    int index = from;
    int childIndex = index * 2;
    while (childIndex <= to) {
      int largerChildIndex = -1;
      if (childIndex + 1 <= to && isHigherPriority(keys[childIndex + 1], keys[childIndex])) {
        largerChildIndex = childIndex + 1;
      } else {
        largerChildIndex = childIndex;
      }
      if (isHigherPriority(keys[largerChildIndex], keys[index])) {
        exchange(keys, index, largerChildIndex);
        index = largerChildIndex;
        childIndex = index * 2;
      } else {
        break;
      }
    }
  }

  protected void exchange(K[] arr, int i, int j) {
    if (i == j) {
      return;
    }
    K t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }


  protected abstract boolean isHigherPriority(K key1, K key2);
}
