package algorithms.fundamentals.divideconquer;

import org.junit.Test;

public class RecursiveMatrixMultiplyTest extends AbstractMatrixMultiplyTest {

  @Override
  protected MatrixMultiply getInstance() {
    return new RecursiveMatrixMultiply();
  }

  @Test
  public void oddMatrixTest() {
    Matrix a = generate(3, 5);
    Matrix b = generate(5, 7);
    runAndVerify(getInstance(), a, b);

    a = generate(31, 53);
    b = generate(53, 73);
    runAndVerify(getInstance(), a, b);
  }

}