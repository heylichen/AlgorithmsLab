package algorithms.sorting.quick.partition;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/6/28.
 */
public class HalfPartitioner<T> implements Partitioner<T> {

  @Override
  public Pair<Integer, Integer> partition(T[] arr, int low, int high) {
    if (low >= high) {
      throw new IllegalArgumentException("array too small to partition");
    }
    int middle = (low + high) / 2;
    return Pair.of(middle, middle + 1);
  }
}
