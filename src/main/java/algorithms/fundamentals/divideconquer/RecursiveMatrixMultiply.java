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
    int blockRowsOfA = rowsOfA / 2;
    int blockColumnsOfA = columnsOfA / 2;
    Matrix a11 = a.blockSubmatrix(blockRowsOfA, blockColumnsOfA, 0, 0);
    Matrix a12 = a.blockSubmatrix(blockRowsOfA, blockColumnsOfA, 0, 1);
    Matrix a21 = a.blockSubmatrix(blockRowsOfA, blockColumnsOfA, 1, 0);
    Matrix a22 = a.blockSubmatrix(blockRowsOfA, blockColumnsOfA, 1, 1);
    int rowsOfB = b.getEffectiveRows();
    int columnsOfB = b.getEffectiveColumns();
    int blockRowsOfB = rowsOfB / 2;
    int blockColumnsOfB = columnsOfB / 2;
    Matrix b11 = b.blockSubmatrix(blockRowsOfB, blockColumnsOfB, 0, 0);
    Matrix b12 = b.blockSubmatrix(blockRowsOfB, blockColumnsOfB, 0, 1);
    Matrix b21 = b.blockSubmatrix(blockRowsOfB, blockColumnsOfB, 1, 0);
    Matrix b22 = b.blockSubmatrix(blockRowsOfB, blockColumnsOfB, 1, 1);

    Matrix result = new Matrix(rowsOfA, columnsOfB);
    Matrix c11 = result.blockSubmatrix(blockRowsOfA, blockColumnsOfB, 0, 0);
    Matrix c12 = result.blockSubmatrix(blockRowsOfA, blockColumnsOfB, 0, 1);
    Matrix c21 = result.blockSubmatrix(blockRowsOfA, blockColumnsOfB, 1, 0);
    Matrix c22 = result.blockSubmatrix(blockRowsOfA, blockColumnsOfB, 1, 1);

    MatrixOperations.add(multiply(a11, b11), multiply(a12, b21), c11);
    MatrixOperations.add(multiply(a11, b12), multiply(a12, b22), c12);
    MatrixOperations.add(multiply(a21, b11), multiply(a22, b21), c21);
    MatrixOperations.add(multiply(a21, b12), multiply(a22, b22), c22);
    return result;
  }

}
