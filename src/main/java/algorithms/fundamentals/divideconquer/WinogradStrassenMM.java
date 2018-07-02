package algorithms.fundamentals.divideconquer;

import lombok.Getter;
import lombok.Setter;

/**
 * standard strassen matrix multiply
 * for square matrices, a * b, size is M*M, where M is power of 2
 * use 7 instead of 8 multiply in each recursive call
 */
public class WinogradStrassenMM implements MatrixMultiply {

  @Getter
  @Setter
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
    Matrix p1 = multiply(a11, b11);
    Matrix p2 = multiply(a12, b21);
    Matrix p3 = multiply(s1, t1);
    Matrix p4 = multiply(s2, t2);
    Matrix p5 = multiply(s3, t3);
    Matrix p6 = multiply(s4, b22);
    Matrix p7 = multiply(a22, t4);
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
    return result;
  }
}
