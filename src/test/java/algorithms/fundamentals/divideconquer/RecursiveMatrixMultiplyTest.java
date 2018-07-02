package algorithms.fundamentals.divideconquer;

public class RecursiveMatrixMultiplyTest extends AbstractMatrixMultiplyTest {

  @Override
  protected MatrixMultiply getInstance() {
    return new RecursiveMatrixMultiply();
  }
}