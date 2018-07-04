package algorithms.fundamentals.divideconquer;

public class StandardMatrixMultiplyTest extends AbstractMatrixMultiplyTest {

  @Override
  protected MatrixMultiply getInstance() {
    return new StandardMatrixMultiply();
  }
}