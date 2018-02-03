package algorithms.sedgewick.sorting.quick.partition;

import algorithms.sedgewick.sorting.AbstractComparableOperator;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/1.
 */
public class ThreeWayPartitioner<T extends Comparable<T>> extends AbstractComparableOperator implements Partitioner<T> {

  @Override
  public Pair<Integer, Integer> partition(T[] arr, int low, int high) {
    T v = arr[low];
    int i = low + 1;
    int j = i;
    int n = high;
    while (j <= n) {
      int compared = arr[i].compareTo(v);
      if (compared == 0) {
        j++;
      } else if (compared < 0) {
        exchange(arr, i++, j++);
      } else {
        exchange(arr, j, n--);
      }
    }
    exchange(arr, low, i - 1);
    return Pair.of(i - 2, j);
  }
}
