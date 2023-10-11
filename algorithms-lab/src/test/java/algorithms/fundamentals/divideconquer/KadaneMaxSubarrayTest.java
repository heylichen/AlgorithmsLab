package algorithms.fundamentals.divideconquer;

/**
 * Created by Chen Li on 2018/6/29.
 */
public class KadaneMaxSubarrayTest extends AbstractMaxSubarrayTest {

  @Override
  protected MaxSubarray getMaxSubarray() {
    return new KadaneMaxSubarray();
  }
}
