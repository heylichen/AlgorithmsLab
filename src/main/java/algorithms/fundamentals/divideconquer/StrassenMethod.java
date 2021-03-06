package algorithms.fundamentals.divideconquer;

public class StrassenMethod implements MatrixMultiplyDivideConquer {

  /**
   * no cut off logic here
   *
   * @param a              matrix a
   * @param b              matrix b
   * @param matrixMultiply main matrix multiplication entry, should contain cutoff logic
   * @return the multiplication result
   */
  @Override
  public Matrix divideAndRecursiveCall(Matrix a, Matrix b, MatrixMultiply matrixMultiply) {
    //do not check cutoff here, check it before padding
    int rowsOfA = a.getEffectiveRows();
    //almost the same as standard strassen algorithm, except recursively calling to another method
    //instead of itself
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
    MatrixBlocks blocksOfC = c.split(blockRowsOfA, blockColumnsOfB);
    Matrix c11 = blocksOfC.getAt11();
    Matrix c12 = blocksOfC.getAt12();
    Matrix c21 = blocksOfC.getAt21();
    Matrix c22 = blocksOfC.getAt22();

    //10 Ss
    Matrix s1 = MatrixOperations.subtract(b12, b22);
    Matrix s2 = MatrixOperations.add(a11, a12);
    Matrix s3 = MatrixOperations.add(a21, a22);
    Matrix s4 = MatrixOperations.subtract(b21, b11);
    Matrix s5 = MatrixOperations.add(a11, a22);
    Matrix s6 = MatrixOperations.add(b11, b22);
    Matrix s7 = MatrixOperations.subtract(a12, a22);
    Matrix s8 = MatrixOperations.add(b21, b22);
    Matrix s9 = MatrixOperations.subtract(a11, a21);
    Matrix s10 = MatrixOperations.add(b11, b12);

    //7 Ps
    Matrix p1 = matrixMultiply.multiply(a11, s1);
    Matrix p2 = matrixMultiply.multiply(s2, b22);
    Matrix p3 = matrixMultiply.multiply(s3, b11);
    Matrix p4 = matrixMultiply.multiply(a22, s4);
    Matrix p5 = matrixMultiply.multiply(s5, s6);
    Matrix p6 = matrixMultiply.multiply(s7, s8);
    Matrix p7 = matrixMultiply.multiply(s9, s10);

    //c11 = p5 + p4 - p2 + p6
    MatrixOperations.add(p5, p4, c11);
    MatrixOperations.subtract(c11, p2, c11);
    MatrixOperations.add(c11, p6, c11);
    //c12 = p1 + p2
    MatrixOperations.add(p1, p2, c12);
    //c21 = p3 + p4
    MatrixOperations.add(p3, p4, c21);
    //c22 = p5 + p1 - p3 - p7
    MatrixOperations.add(p5, p1, c22);
    MatrixOperations.subtract(c22, p3, c22);
    MatrixOperations.subtract(c22, p7, c22);
    return c;
  }
}
