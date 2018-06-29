package algorithms.fundamentals.divideconquer;

/**
 * Created by Chen Li on 2018/6/28.
 */
public class RecurrenceMaxSubarrayTest extends AbstractMaxSubarrayTest {

  @Override
  protected MaxSubarray getMaxSubarray() {
    return new RecurrenceMaxSubarray();
  }
}