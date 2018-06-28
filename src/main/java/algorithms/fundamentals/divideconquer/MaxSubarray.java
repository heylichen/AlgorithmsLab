package algorithms.fundamentals.divideconquer;

import org.apache.commons.lang3.tuple.Triple;

/**
 * Created by Chen Li on 2018/6/28.
 * given an int  array, find the subarray that
 * the sum of the subarray is the maximum of all
 */
public interface MaxSubarray {

  /**
   * @return a triple
   * left: the left index of the subarray
   * middle: the right index of the subarray
   * right: the sum of the subarray
   */
  Triple<Integer, Integer, Integer> find(Integer[] array);
}
