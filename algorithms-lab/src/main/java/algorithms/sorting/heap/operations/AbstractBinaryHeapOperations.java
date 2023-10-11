package algorithms.sorting.heap.operations;

/**
 * Created by Chen Li on 2018/2/18.
 */
public abstract class AbstractBinaryHeapOperations<K extends Comparable<K>> extends AbstractHeapOperations<K> {

  public int getParent(int child) {
    return child / 2;
  }

  public int getMaxPriorityChildOf(K[] keys, int index, int rightBound) {
    int from = index * 2;
    if (from > rightBound) {
      return -1;
    }
    int to = from + 1;
    if (to > rightBound) {
      to = rightBound;
    }
    int maxPriorityChild = from;
    K maxPriorityChildKey = keys[from];
    for (int i = from + 1; i <= to; i++) {
      K currentKey = keys[i];
      if (isHigherPriority(currentKey, maxPriorityChildKey)) {
        maxPriorityChild = i;
        maxPriorityChildKey = currentKey;
      }
    }

    return maxPriorityChild;
  }
}
