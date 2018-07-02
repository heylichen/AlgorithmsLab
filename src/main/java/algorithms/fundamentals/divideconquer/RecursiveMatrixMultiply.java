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
    int rowsOfA = a.getEffectiveRows();
    if (rowsOfA <= cutoffSize) {
      return multiply.multiply(a, b);
    }
    int columnsOfA = a.getEffectiveColumns();
    int middleRowsOfA = a.getRowsBegin() + rowsOfA / 2;
    int middleColumnsOfA = a.getColumnsBegin() + columnsOfA / 2;
    Matrix a11 = a.submatrix(a.getRowsBegin(), middleRowsOfA - 1,
                             a.getColumnsBegin(), middleColumnsOfA - 1);
    Matrix a12 = a.submatrix(a.getRowsBegin(), middleRowsOfA - 1, middleColumnsOfA, a.getColumnsEnd());

    Matrix a21 = a.submatrix(middleRowsOfA, a.getRowsEnd(), a.getColumnsBegin(), middleColumnsOfA - 1);
    Matrix a22 = a.submatrix(middleRowsOfA, a.getRowsEnd(), middleColumnsOfA, a.getColumnsEnd());
    int rowsOfB = b.getEffectiveRows();
    int columnsOfB = b.getEffectiveColumns();
    int middleRowsOfB = b.getRowsBegin() + rowsOfB / 2;
    int middleColumnsOfB = b.getColumnsBegin() + columnsOfB / 2;
    Matrix b11 = b.submatrix(b.getRowsBegin(), middleRowsOfB - 1,
                             b.getColumnsBegin(), middleColumnsOfB - 1);
    Matrix b12 = b.submatrix(b.getRowsBegin(), middleRowsOfB - 1, middleColumnsOfB, b.getColumnsEnd());

    Matrix b21 = b.submatrix(middleRowsOfB, b.getRowsEnd(), b.getColumnsBegin(), middleColumnsOfB - 1);
    Matrix b22 = b.submatrix(middleRowsOfB, b.getRowsEnd(), middleColumnsOfB, b.getColumnsEnd());

    Matrix result = new Matrix(rowsOfA, columnsOfB);
    Matrix c11 = result.submatrix(0, middleRowsOfA - 1, 0, middleColumnsOfB - 1);
    Matrix c12 = result.submatrix(0, middleRowsOfA - 1, middleColumnsOfB, result.getColumnsEnd());
    Matrix c21 = result.submatrix(middleRowsOfA, result.getRowsEnd(), 0, middleColumnsOfB - 1);
    Matrix c22 = result.submatrix(middleRowsOfA, result.getRowsEnd(), middleColumnsOfB, result.getColumnsEnd());

    MatrixOperations.add(multiply(a11, b11), multiply(a12, b21), c11);
    MatrixOperations.add(multiply(a11, b12), multiply(a12, b22), c12);
    MatrixOperations.add(multiply(a21, b11), multiply(a22, b21), c21);
    MatrixOperations.add(multiply(a21, b12), multiply(a22, b22), c22);
    return result;
  }

}
