package algorithms.fundamentals.divideconquer;

import javax.annotation.Resource;

import algorithms.utils.MatrixGenerator;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/7/5.
 */
public class DynamicPeelingStrassenMMTest  extends AbstractMatrixMultiplyTest {
  @Resource(name = "dynamicPeelingStrassenMM")
  private MatrixMultiply dynamicPeelingStrassenMM;
  private MatrixGenerator matrixGenerator = MatrixGenerator.instance();

  @Override
  protected MatrixMultiply getInstance() {
    return dynamicPeelingStrassenMM;
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