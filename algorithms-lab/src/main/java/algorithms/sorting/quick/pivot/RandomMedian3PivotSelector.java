package algorithms.sorting.quick.pivot;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Chen Li on 2018/2/2.
 */
public class RandomMedian3PivotSelector<T extends Comparable<T>> extends Median3PivotSelector<T>
    implements PivotSelector<T> {

  private Random random = ThreadLocalRandom.current();

  @Override
  public int pivot(T[] arr, int low, int high) {

    //median of three rearrange, so that arr[low] is middle value, arr[high] is max value
    int middle = low + +1 + random.nextInt(high - low - 1);
    return median3(arr, low, middle, high);
  }
}
