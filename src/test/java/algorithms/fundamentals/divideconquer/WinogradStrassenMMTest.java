package algorithms.fundamentals.divideconquer;

import javax.annotation.Resource;

public class WinogradStrassenMMTest extends AbstractMatrixMultiplyTest {

  @Resource(name = "standardWinogradMM")
  private MatrixMultiply matrixMultiply;
  @Override
  protected MatrixMultiply getInstance() {
    return matrixMultiply;
  }

}