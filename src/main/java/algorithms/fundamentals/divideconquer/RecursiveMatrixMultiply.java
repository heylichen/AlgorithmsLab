package algorithms.fundamentals.divideconquer;

/**
 * for square matrix
 * rows is 2^n
 */
public class RecursiveMatrixMultiply implements MatrixMultiply {

  private int cutoffSize = 1;
  private SimpleMatrixMultiply multiply = new SimpleMatrixMultiply();

  @Override
  public SquareMatrix multiply(SquareMatrix a, SquareMatrix b) {
    int rows = a.getEffectiveRows();
    if (rows <= cutoffSize) {
      return multiply.multiply(a, b);
    }
    int middle = rows / 2;
    SquareMatrix a11 = submatrix(a, middle, 0, 0);
    SquareMatrix a12 = submatrix(a, middle, 0, 1);
    SquareMatrix a21 = submatrix(a, middle, 1, 0);
    SquareMatrix a22 = submatrix(a, middle, 1, 1);

    SquareMatrix b11 = submatrix(b, middle, 0, 0);
    SquareMatrix b12 = submatrix(b, middle, 0, 1);
    SquareMatrix b21 = submatrix(b, middle, 1, 0);
    SquareMatrix b22 = submatrix(b, middle, 1, 1);

    SquareMatrix result = new SquareMatrix(rows);
    SquareMatrix c11 = submatrix(result, middle, 0, 0);
    SquareMatrix c12 = submatrix(result, middle, 0, 1);
    SquareMatrix c21 = submatrix(result, middle, 1, 0);
    SquareMatrix c22 = submatrix(result, middle, 1, 1);

    SquareMatrix.add(multiply(a11, b11), multiply(a12, b21), c11);
    SquareMatrix.add(multiply(a11, b12), multiply(a12, b22), c12);
    SquareMatrix.add(multiply(a21, b11), multiply(a22, b21), c21);
    SquareMatrix.add(multiply(a21, b12), multiply(a22, b22), c22);
    return result;
  }

  public SquareMatrix submatrix(SquareMatrix matrix, int subLength, int i, int j) {
    return matrix.submatrix(matrix.getRowsBegin() + subLength * i,
                            matrix.getRowsBegin() + subLength * (i + 1) - 1,
                            matrix.getColumnsBegin() + +subLength * j,
                            matrix.getColumnsBegin() + subLength * (j + 1) - 1
    );
  }
}
