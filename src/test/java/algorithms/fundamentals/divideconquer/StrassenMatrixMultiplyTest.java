package algorithms.fundamentals.divideconquer;

import org.junit.Test;

public class StrassenMatrixMultiplyTest extends AbstractMatrixMultiplyTest {

  @Override
  protected MatrixMultiply getInstance() {
    StrassenMatrixMultiply instance = new StrassenMatrixMultiply();
    return instance;
  }

  @Test
  public void performanceTest() {
    int size = 256;
    StrassenMatrixMultiply instance = new StrassenMatrixMultiply();
    instance.setCutoffSize(64);
    performanceCompare(instance, new StandardMatrixMultiply(), size, size);
  }
}