package algorithms.fundamentals.divideconquer;

import algorithms.utils.MatrixGenerator;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/7/4.
 */
public class MatrixOperationsTest {

  private MatrixGenerator matrixGenerator = MatrixGenerator.instance();

  @Test
  public void copyTest() {
    Matrix a = matrixGenerator.generate(4, 4);
    Matrix b = a.submatrix(2, 3, 2, 3);
    Matrix c = new Matrix(2, 2);
    MatrixOperations.copy(b, c);
    System.out.println(b);
    System.out.println(c);
    System.out.println("ok");
  }
}