package algorithms.sedgewick.sorting.quick.pivot;

import java.util.Random;

/**
 * Created by Chen Li on 2018/1/31.
 */
public class RandomPivotSelector<T extends Comparable<T>> implements PivotSelector<T> {

  private Random random = new Random();

  @Override
  public int pivot(T[] arr, int low, int high) {
    return low + random.nextInt(high - low + 1);
  }
}
