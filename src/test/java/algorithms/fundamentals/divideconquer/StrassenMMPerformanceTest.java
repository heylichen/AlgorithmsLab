package algorithms.fundamentals.divideconquer;

import org.junit.Test;

/**
 * Created by Chen Li on 2018/7/6.
 */
public class StrassenMMPerformanceTest extends AbstractMatrixMultiplyTest {

  @Override
  protected MatrixMultiply getInstance() {
    return new StandardMatrixMultiply();
  }

  @Test
  public void performanceTest() {
    DynamicPeelingStrassenMM dynamicPeeling = new DynamicPeelingStrassenMM();
    dynamicPeeling.setCutoffSize(64);
    DynamicPaddingStrassenMM dynamicPadding = new DynamicPaddingStrassenMM();
    dynamicPadding.setCutoffSize(64);
    int size = 1023;
    performanceCompare(dynamicPadding, dynamicPeeling, size, size);
    performanceCompare(dynamicPeeling, dynamicPadding, size, size);

//    size = 1024;
//    performanceCompare(dynamicPadding, dynamicPeeling, size, size);
//    performanceCompare(dynamicPeeling, dynamicPadding, size, size);
//
//    size = 1025;
//    performanceCompare(dynamicPadding, dynamicPeeling, size, size);
//    performanceCompare(dynamicPeeling, dynamicPadding, size, size);
  }

}
