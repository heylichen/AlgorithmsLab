package algorithms.fundamentals.divideconquer;

import algorithms.utils.MatrixGenerator;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/7/5.
 */
public class DynamicPeelingStrassenMMTest  extends AbstractMatrixMultiplyTest {

  private MatrixGenerator matrixGenerator = MatrixGenerator.instance();

  @Override
  protected MatrixMultiply getInstance() {
    return new  DynamicPeelingStrassenMM();
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

  @Test
  public void performanceTest() {
    int size = 128;
    DynamicPeelingStrassenMM instance = new  DynamicPeelingStrassenMM();
    instance.setCutoffSize(64);
    performanceCompare(instance, new StandardMatrixMultiply(), size, size);
  }
}