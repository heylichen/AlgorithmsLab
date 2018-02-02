package algorithms.sedgewick.sorting.quick.partition;

import algorithms.sedgewick.sorting.quick.AbstractComparableOperator;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/1.
 */
public class SimplePartitioner<T extends Comparable<T>> extends AbstractComparableOperator implements Partitioner<T> {

  @Override
  public Pair<Integer, Integer> partition(T[] arr, int low, int high) {
    Comparable v = arr[low];
    int i = low;
    int j = high + 1;
    while (true) {
      while (less(arr[++i], v)) {
        if (i == high) {
          break;
        }
      }
      while (less(v, arr[--j])) {
        ;
      }
      if (i < j) {
        exchange(arr, i, j);
      } else {
        break;
      }
    }
    exchange(arr, low, j);
    return Pair.of(j - 1, j + 1);
  }
}
