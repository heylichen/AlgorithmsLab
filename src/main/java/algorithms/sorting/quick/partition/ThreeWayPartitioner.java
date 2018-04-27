package algorithms.sorting.quick.partition;

import algorithms.sedgewick.sorting.ComparableOperations;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/1.
 */
public class ThreeWayPartitioner<T extends Comparable<T>> implements Partitioner<T> {

  private ComparableOperations comparableOperations = new ComparableOperations();

  @Override
  public Pair<Integer, Integer> partition(T[] arr, int low, int high) {
    T v = arr[low];
    int i = low + 1;
    int j = i;
    int n = high;
    while (j <= n) {
      int compared = arr[j].compareTo(v);
      if (compared == 0) {
        j++;
      } else if (compared < 0) {
        comparableOperations.exchange(arr, i++, j++);
      } else {
        comparableOperations.exchange(arr, j, n--);
      }
    }
    comparableOperations.exchange(arr, low, i - 1);
    return Pair.of(i - 2, j);
  }
}
