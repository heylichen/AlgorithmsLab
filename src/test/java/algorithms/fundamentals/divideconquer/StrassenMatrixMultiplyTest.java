package algorithms.fundamentals.divideconquer;

public class StrassenMatrixMultiplyTest extends AbstractMatrixMultiplyTest {

  @Override
  protected MatrixMultiply getInstance() {
    return getBean("standardStrassenMM");
  }

}