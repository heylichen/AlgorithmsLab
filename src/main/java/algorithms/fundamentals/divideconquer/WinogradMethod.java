package algorithms.fundamentals.divideconquer;

public class WinogradMethod implements MatrixMultiplyDivideConquer {

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
    int rowsOfA = a.getEffectiveRows();
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

    //stage1
    Matrix s1 = MatrixOperations.add(a21, a22);
    Matrix s2 = MatrixOperations.subtract(s1, a11);
    Matrix s3 = MatrixOperations.subtract(a11, a21);
    Matrix s4 = MatrixOperations.subtract(a12, s2);
    //stage2
    Matrix t1 = MatrixOperations.subtract(b12, b11);
    Matrix t2 = MatrixOperations.subtract(b22, t1);
    Matrix t3 = MatrixOperations.subtract(b22, b12);
    Matrix t4 = MatrixOperations.subtract(b21, t2);
    //stage3
    Matrix p1 = matrixMultiply.multiply(a11, b11);
    Matrix p2 = matrixMultiply.multiply(a12, b21);
    Matrix p3 = matrixMultiply.multiply(s1, t1);
    Matrix p4 = matrixMultiply.multiply(s2, t2);
    Matrix p5 = matrixMultiply.multiply(s3, t3);
    Matrix p6 = matrixMultiply.multiply(s4, b22);
    Matrix p7 = matrixMultiply.multiply(a22, t4);
    //stage4
    /*
     U1 = P1 + P2;
     U2 = P1 + P4;
     U3 = U2 + P5;
     U4 = U3 + P7;
     U5 = U3 + P3;
     U6 = U2 + P3;
     U7 = U6 + P6:
     c11 = u1, c12 = u7
     c21 = u4, c22 = u5
     */
    Matrix u1 = c11;
    MatrixOperations.add(p1, p2, u1);
    Matrix u2 = MatrixOperations.add(p1, p4);
    Matrix u3 = MatrixOperations.add(u2, p5);
    Matrix u4 = c21;
    MatrixOperations.add(u3, p7, u4);
    Matrix u5 = c22;
    MatrixOperations.add(u3, p3, u5);
    Matrix u7 = c12;
    MatrixOperations.add(u2, p3, u7);
    MatrixOperations.add(u7, p6, u7);
    return c;
  }
}