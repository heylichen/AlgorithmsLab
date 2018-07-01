package algorithms.fundamentals.divideconquer;

import org.junit.Test;

public abstract class AbstractMatrixMultiplyTest {

  protected MatrixLoader matrixLoader = new MatrixLoader();

  protected abstract MatrixMultiply getInstance();

  @Test
  public void smallTest() {
    Matrix ma = matrixLoader.load("algorithms/fundamental/divideconquer/smallMatrix-a.text");
    Matrix mb = matrixLoader.load("algorithms/fundamental/divideconquer/smallMatrix-b.text");
    MatrixMultiply matrixMultiply = getInstance();
    Matrix result = matrixMultiply.multiply(ma, mb);
    System.out.println(result);
  }
}
