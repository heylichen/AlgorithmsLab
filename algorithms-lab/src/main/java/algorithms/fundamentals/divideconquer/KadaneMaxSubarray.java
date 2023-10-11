package algorithms.fundamentals.divideconquer;

import org.apache.commons.lang3.tuple.Triple;

/**
 * Created by Chen Li on 2018/6/29.
 */
public class KadaneMaxSubarray implements MaxSubarray {

  @Override
  public Triple<Integer, Integer, Integer> find(Integer[] array) {
    if (array == null || array.length == 0) {
      return null;
    }
    int left = 0;
    int right = 0;
    int maxEndingHereLeft = 0;
    int maxEndingHere = array[0];
    int maxSoFar = maxEndingHere;
    for (int i = 1; i < array.length; i++) {
      int current = array[i];
      if (current > maxEndingHere + current) {
        maxEndingHereLeft = i;
        maxEndingHere = current;
      } else {
        maxEndingHere = maxEndingHere + current;
      }

      if (maxSoFar < maxEndingHere) {
        left = maxEndingHereLeft;
        right = i;
        maxSoFar = maxEndingHere;
      }
    }

    return Triple.of(left, right, maxSoFar);
  }
}
