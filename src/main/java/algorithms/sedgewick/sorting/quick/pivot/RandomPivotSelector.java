package algorithms.sedgewick.sorting.quick.pivot;

import java.util.Random;

/**
 * Created by Chen Li on 2018/1/31.
 */
public class RandomPivotSelector implements PivotSelector {
  private Random random = new Random();

  @Override
  public <T> int pivot(T[] arr, int low, int high) {
    return low + random.nextInt(high - low + 1);
  }
}
