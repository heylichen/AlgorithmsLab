package algorithms.fundamentals.divideconquer;

public class RecursiveMatrixMultiplyTest extends AbstractMatrixMultiplyTest {

  @Override
  protected MatrixMultiply getInstance() {
    return new RecursiveMatrixMultiply();
  }

//  @Test
//  public void name2() {
//    SquareMatrix ma = matrixLoader.load("algorithms/fundamental/divideconquer/smallMatrix-a.text");
//    RecursiveMatrixMultiply matrixMultiply = new RecursiveMatrixMultiply();
//    SquareMatrix a11 = matrixMultiply.submatrix(ma, 2, 0, 0);
//    SquareMatrix a12 = matrixMultiply.submatrix(ma, 2, 0, 1);
//    SquareMatrix b11 = matrixMultiply.submatrix(ma, 2, 1, 0);
//    SquareMatrix b12 = matrixMultiply.submatrix(ma, 2, 1, 1);
//
//    SquareMatrix c12 = matrixMultiply.submatrix(b12, 1, 1, 0);
//    System.out.println("ok");
//
//  }
}