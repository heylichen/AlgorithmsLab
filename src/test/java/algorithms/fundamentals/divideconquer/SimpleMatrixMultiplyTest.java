package algorithms.fundamentals.divideconquer;

public class SimpleMatrixMultiplyTest extends AbstractMatrixMultiplyTest {

  @Override
  protected MatrixMultiply getInstance() {
    return new SimpleMatrixMultiply();
  }
}