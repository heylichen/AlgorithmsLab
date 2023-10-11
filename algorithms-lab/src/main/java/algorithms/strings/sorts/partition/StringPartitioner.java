package algorithms.strings.sorts.partition;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/1.
 */
public interface StringPartitioner<T extends Comparable<T>> {

  Pair<Integer, Integer> partition(T[] arr, int low, int high, int index);
}
