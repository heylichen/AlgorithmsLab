package algorithms.fundamentals.divideconquer;

import java.util.Arrays;

import algorithms.sorting.quick.partition.HalfPartitioner;
import algorithms.sorting.quick.partition.Partitioner;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

/**
 * Created by Chen Li on 2018/6/28.
 */
public class RecurrenceMaxSubarray implements MaxSubarray {

  private Partitioner halfPartitioner = new HalfPartitioner();

  @Override
  public Triple<Integer, Integer, Integer> find(Integer[] array) {
    if (array == null || array.length == 0) {
      return null;
    }
    return find(array, 0, array.length - 1);
  }

  private Triple<Integer, Integer, Integer> find(Integer[] array, int low, int high) {
    if (low == high) {
      return Triple.of(low, low, array[low]);
    }
    Pair<Integer, Integer> partitionIndices = halfPartitioner.partition(array, low, high);
    int leftMiddle = partitionIndices.getLeft();
    int rightMiddle = partitionIndices.getRight();

    Triple<Integer, Integer, Integer> totalLeft = find(array, low, leftMiddle);
    Triple<Integer, Integer, Integer> totalRight = find(array, rightMiddle, high);
    //find max crossing sub array
    Triple<Integer, Integer, Integer> crossingSubArray = findMaxCrossingSubarray(array, low, high, partitionIndices);
    //return the max of the three
    return maxOf(totalLeft, totalRight, crossingSubArray);
  }

  private Triple<Integer, Integer, Integer> maxOf(Triple<Integer, Integer, Integer>... triples) {
    return Arrays.stream(triples).reduce((a, b) -> a.getRight().compareTo(b.getRight()) > 0 ? a : b).get();
  }

  private Triple<Integer, Integer, Integer> findMaxCrossingSubarray(Integer[] array,
                                                                    int low, int high,
                                                                    Pair<Integer, Integer> partitionIndices) {
    int maxLeftSum = Integer.MIN_VALUE;
    int leftSum = 0;
    int i = partitionIndices.getLeft();
    int maxLeftIndex = i;
    while (i >= low) {
      leftSum += array[i];
      if (leftSum > maxLeftSum) {
        maxLeftSum = leftSum;
        maxLeftIndex = i;
      }
      i--;
    }

    int maxRightSum = Integer.MIN_VALUE;
    int rightSum = 0;
    int j = partitionIndices.getRight();
    int maxRightIndex = j;
    while (j <= high) {
      rightSum += array[j];
      if (rightSum > maxRightSum) {
        maxRightSum = rightSum;
        maxRightIndex = j;
      }
      j++;
    }

    return Triple.of(maxLeftIndex, maxRightIndex, maxLeftSum + maxRightSum);
  }
}
