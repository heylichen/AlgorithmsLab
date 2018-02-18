package algorithms.sedgewick.sorting.heap;

/**
 * Created by Chen Li on 2018/2/6.
 * Sink to the bottom, then swim
 * Most items reinserted into the heap during sortdown
 * go all the way to the bottom. Floyd observed in 1964 that we can thus save time by
 * avoiding the check for whether the item has reached its position, simply promoting
 * the larger of the two children until the bottom is reached, then moving back up the
 * heap to the proper position. This idea cuts the number of compares by a factor of 2
 * asymptotically—close to the number used by mergesort (for a randomly-ordered array)
 */
public class LessCompareHeapSort<K extends Comparable<K>> extends AbstractHeapSort<K> {

  public LessCompareHeapSort(String name) {
    super(name);
  }

  @Override
  protected void sortDown(K[] arr) {
    int k = arr.length - 1;
    while (k > 1) {
      comparableOperations.exchange(arr, 1, k--);
      sinkToBottomThenSwim(arr, 1, k);
    }
  }

  private void sinkToBottomThenSwim(K[] keys, int from, int to) {
    int index = from;
    int childIndex = heapOperations.getMaxPriorityChildOf(keys, index, to);
    K currentV = keys[from];
    while (childIndex != -1) {
      keys[index] = keys[childIndex];
      index = childIndex;
      childIndex = heapOperations.getMaxPriorityChildOf(keys, index, to);
    }
    if (index != from) {
      keys[index] = currentV;
    }
    //swim
    heapOperations.swim(keys, index, 1);
  }
}
