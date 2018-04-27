package algorithms.sorting.quick.partition;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/1.
 */
public interface Partitioner<T extends Comparable<T>> {

  Pair<Integer, Integer> partition(T[] arr, int low, int high);
}
