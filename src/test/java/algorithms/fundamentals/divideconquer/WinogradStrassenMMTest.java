package algorithms.fundamentals.divideconquer;

import org.junit.Test;

public class WinogradStrassenMMTest extends AbstractMatrixMultiplyTest {

  @Override
  protected MatrixMultiply getInstance() {
    WinogradStrassenMM instance = new WinogradStrassenMM();
    return instance;
  }

  @Test
  public void performanceTest() {
//    int size = 2048;
//    StrassenMatrixMultiply strassenMM = new StrassenMatrixMultiply();
//    strassenMM.setCutoffSize(64);
//
//    WinogradStrassenMM winogradStrassenMM = new WinogradStrassenMM();
//    winogradStrassenMM.setCutoffSize(64);
//
//    performanceCompare(winogradStrassenMM, strassenMM, size, size);
  }
}