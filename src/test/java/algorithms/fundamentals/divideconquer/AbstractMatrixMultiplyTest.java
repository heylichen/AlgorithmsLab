package algorithms.fundamentals.divideconquer;

import org.junit.Test;

public abstract class AbstractMatrixMultiplyTest {

  protected MatrixLoader matrixLoader = new MatrixLoader();

  protected abstract MatrixMultiply getInstance();

  @Test
  public void smallTest() {
    SquareMatrix ma = matrixLoader.load("algorithms/fundamental/divideconquer/smallMatrix-a.text");
    SquareMatrix mb = matrixLoader.load("algorithms/fundamental/divideconquer/smallMatrix-b.text");
    MatrixMultiply matrixMultiply = getInstance();
    SquareMatrix result = matrixMultiply.multiply(ma, mb);
    System.out.println(result);
  }
}
