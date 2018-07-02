package algorithms.fundamentals.divideconquer;

import lombok.Getter;
import lombok.Setter;

/**
 * standard strassen matrix multiply
 * for square matrices, a * b, size is M*M, where M is power of 2
 * use 7 instead of 8 multiply in each recursive call
 */
public class StrassenMatrixMultiply implements MatrixMultiply {

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
    Matrix p1 = multiply(a11, s1);
    Matrix p2 = multiply(s2, b22);
    Matrix p3 = multiply(s3, b11);
    Matrix p4 = multiply(a22, s4);
    Matrix p5 = multiply(s5, s6);
    Matrix p6 = multiply(s7, s8);
    Matrix p7 = multiply(s9, s10);

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

    return result;
  }
}
