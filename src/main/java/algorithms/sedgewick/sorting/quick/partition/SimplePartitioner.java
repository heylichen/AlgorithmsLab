package algorithms.sedgewick.sorting.quick.partition;

import algorithms.sedgewick.sorting.ComparableOperations;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/1.
 */
public class SimplePartitioner<T extends Comparable<T>> implements Partitioner<T> {

  private ComparableOperations comparableOperations = new ComparableOperations();

  @Override
  public Pair<Integer, Integer> partition(T[] arr, int low, int high) {
    Comparable v = arr[low];
    int i = low;
    int j = high + 1;
    while (true) {
      while (comparableOperations.less(arr[++i], v)) {
        if (i == high) {
          break;
        }
      }
      while (comparableOperations.less(v, arr[--j])) {
        ;
      }
      if (i < j) {
        comparableOperations.exchange(arr, i, j);
      } else {
        break;
      }
    }
    comparableOperations.exchange(arr, low, j);
    return Pair.of(j - 1, j + 1);
  }
}
