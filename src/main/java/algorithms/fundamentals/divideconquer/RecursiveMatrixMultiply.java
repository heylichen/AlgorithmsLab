package algorithms.fundamentals.divideconquer;

/**
 * for square matrix
 * rows is 2^n
 */
public class RecursiveMatrixMultiply implements MatrixMultiply {

  private int cutoffSize = 1;
  private SimpleMatrixMultiply multiply = new SimpleMatrixMultiply();

  @Override
  public Matrix multiply(Matrix a, Matrix b) {
    int rows = a.getEffectiveRows();
    if (rows <= cutoffSize) {
      return multiply.multiply(a, b);
    }
    int middle = rows / 2;
    Matrix a11 = submatrix(a, middle, 0, 0);
    Matrix a12 = submatrix(a, middle, 0, 1);
    Matrix a21 = submatrix(a, middle, 1, 0);
    Matrix a22 = submatrix(a, middle, 1, 1);

    Matrix b11 = submatrix(b, middle, 0, 0);
    Matrix b12 = submatrix(b, middle, 0, 1);
    Matrix b21 = submatrix(b, middle, 1, 0);
    Matrix b22 = submatrix(b, middle, 1, 1);

    Matrix result = new Matrix(rows);
    Matrix c11 = submatrix(result, middle, 0, 0);
    Matrix c12 = submatrix(result, middle, 0, 1);
    Matrix c21 = submatrix(result, middle, 1, 0);
    Matrix c22 = submatrix(result, middle, 1, 1);

    Matrix.add(multiply(a11, b11), multiply(a12, b21), c11);
    Matrix.add(multiply(a11, b12), multiply(a12, b22), c12);
    Matrix.add(multiply(a21, b11), multiply(a22, b21), c21);
    Matrix.add(multiply(a21, b12), multiply(a22, b22), c22);
    return result;
  }

  public Matrix submatrix(Matrix matrix, int subLength, int i, int j) {
    return matrix.submatrix(matrix.getRowsBegin() + subLength * i,
                            matrix.getRowsBegin() + subLength * (i + 1) - 1,
                            matrix.getColumnsBegin() + +subLength * j,
                            matrix.getColumnsBegin() + subLength * (j + 1) - 1
    );
  }
}
