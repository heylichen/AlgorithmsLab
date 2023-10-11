package algorithms.fundamentals.divideconquer;

import javax.annotation.Resource;

import algorithms.utils.MatrixGenerator;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/7/4.
 */
public class DynamicPaddingStrassenMMTest extends AbstractMatrixMultiplyTest {

  @Resource(name = "dynamicPaddingStrassenMM")
  private MatrixMultiply dynamicPaddingStrassenMM;
  private MatrixGenerator matrixGenerator = MatrixGenerator.instance();

  @Override
  protected MatrixMultiply getInstance() {
    return dynamicPaddingStrassenMM;
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