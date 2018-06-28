package algorithms.sorting.quick.partition;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/1.
 */
public interface Partitioner<T> {

  /**
   * given an array, start index low, end index high( end index include),
   * partition the array into two or three parts,
   * return left middle index and right middle index
   * if right middle index = left middle +1, then partition the array into 2 parts,
   * if right middle index > left middle +1, then partition the array into 3 parts
   */
  Pair<Integer, Integer> partition(T[] arr, int low, int high);
}
