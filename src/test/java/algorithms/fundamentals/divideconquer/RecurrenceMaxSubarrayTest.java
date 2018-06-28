package algorithms.fundamentals.divideconquer;

import org.junit.Test;

/**
 * Created by Chen Li on 2018/6/28.
 */
public class RecurrenceMaxSubarrayTest {

  @Test
  public void name() {
    RecurrenceMaxSubarray maxSubarray = new RecurrenceMaxSubarray();
    Integer[] array =new Integer[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
    System.out.println(maxSubarray.find(array));
  }
}