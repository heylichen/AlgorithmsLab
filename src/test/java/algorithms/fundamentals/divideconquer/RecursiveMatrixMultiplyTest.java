package algorithms.fundamentals.divideconquer;

import algorithms.utils.MatrixGenerator;
import org.junit.Test;

public class RecursiveMatrixMultiplyTest extends AbstractMatrixMultiplyTest {

  private MatrixGenerator matrixGenerator = MatrixGenerator.instance();

  @Override
  protected MatrixMultiply getInstance() {
    return new RecursiveMatrixMultiply();
  }

  @Test
  public void oddMatrixTest() {
    Matrix a = matrixGenerator.generate(3, 5);
    Matrix b = matrixGenerator.generate(5, 7);
    runAndVerify(getInstance(), a, b);

    a = matrixGenerator.generate(31, 53);
    b = matrixGenerator.generate(53, 73);
    runAndVerify(getInstance(), a, b);
  }

}