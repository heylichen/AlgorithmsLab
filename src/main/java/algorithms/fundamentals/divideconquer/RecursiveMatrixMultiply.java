package algorithms.fundamentals.divideconquer;

/**
 * matrix multiplication of arbitrary size
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
    int blockRowsOfA = rowsOfA / 2;
    int blockColumnsOfA = a.getEffectiveColumns() / 2;
    MatrixBlocks blocksOfA = a.split(blockRowsOfA, blockColumnsOfA);
    Matrix a11 = blocksOfA.getAt11();
    Matrix a12 = blocksOfA.getAt12();
    Matrix a21 = blocksOfA.getAt21();
    Matrix a22 = blocksOfA.getAt22();

    int blockRowsOfB = b.getEffectiveRows() / 2;
    int blockColumnsOfB = b.getEffectiveColumns() / 2;
    MatrixBlocks blocksOfB = b.split(blockRowsOfB, blockColumnsOfB);
    Matrix b11 = blocksOfB.getAt11();
    Matrix b12 = blocksOfB.getAt12();
    Matrix b21 = blocksOfB.getAt21();
    Matrix b22 = blocksOfB.getAt22();

    Matrix c = new Matrix(rowsOfA, b.getEffectiveColumns());
    MatrixBlocks blocksOfC = c.split(blockRowsOfA,blockColumnsOfB);
    Matrix c11 = blocksOfC.getAt11();
    Matrix c12 = blocksOfC.getAt12();
    Matrix c21 = blocksOfC.getAt21();
    Matrix c22 = blocksOfC.getAt22();

    MatrixOperations.add(multiply(a11, b11), multiply(a12, b21), c11);
    MatrixOperations.add(multiply(a11, b12), multiply(a12, b22), c12);
    MatrixOperations.add(multiply(a21, b11), multiply(a22, b21), c21);
    MatrixOperations.add(multiply(a21, b12), multiply(a22, b22), c22);
    return c;
  }

}
