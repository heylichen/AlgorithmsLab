package algorithms.fundamentals.divideconquer;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

@Getter
@Setter
public class DynamicPeelingStrassenMM extends CutoffMatrixMultiply {

  private MatrixMultiplyDivideConquer matrixMultiplyDivideConquer;
  private StandardMatrixMultiply multiply = new StandardMatrixMultiply();

  @Override
  public Matrix multiply(Matrix a, Matrix b) {
    int rowsA = a.getEffectiveRows();
    int columnsA = a.getEffectiveColumns();
    int columnsB = b.getEffectiveColumns();
    //TODO : simple cutoff logic need fine tuning
    int cutoffSize = getCutoffSize();
    if (rowsA <= cutoffSize || columnsA <= cutoffSize || columnsB <= cutoffSize) {
      return multiply.multiply(a, b);
    }
    //do peeling and fixing
    boolean rowsAOdd = rowsA % 2 == 1;
    boolean columnsAOdd = columnsA % 2 == 1;
    boolean columnsBOdd = columnsB % 2 == 1;
    //all dimensions even, no padding needed
    if (!rowsAOdd && !columnsAOdd && !columnsBOdd) {
      return matrixMultiplyDivideConquer.divideAndRecursiveCall(a, b, this);
    }
    //do peeling, use three bits to represent odd of three dimensions of M,N,P
    //1. 1 0 0
    if (rowsAOdd && !columnsAOdd && !columnsBOdd) {
      Pair<Matrix, Matrix> partsOfA = verticalSplit(a);
      Matrix upperC = matrixMultiplyDivideConquer.divideAndRecursiveCall(partsOfA.getLeft(), b, this);
      Matrix bottomC = multiply.multiply(partsOfA.getRight(), b);
      Matrix c = new Matrix(rowsA, columnsB);
      MatrixOperations.copy(upperC, c, c.getRowsBegin(), c.getColumnsBegin());
      MatrixOperations.copy(bottomC, c, c.getRowsEnd(), c.getColumnsBegin());
      return c;
    }
    //2. 0 1 0
    if (!rowsAOdd && columnsAOdd && !columnsBOdd) {
      Pair<Matrix, Matrix> partsOfA = horizontalSplit(a);
      Pair<Matrix, Matrix> partsOfB = verticalSplit(b);

      Matrix c1 = matrixMultiplyDivideConquer.divideAndRecursiveCall(partsOfA.getLeft(), partsOfB.getLeft(), this);
      Matrix c2 = multiply.multiply(partsOfA.getRight(), partsOfB.getRight());
      MatrixOperations.add(c1, c2, c2);
      return c2;
    }
    //3. 0 0 1
    if (!rowsAOdd && !columnsAOdd && columnsBOdd) {
      Pair<Matrix, Matrix> partsOfB = horizontalSplit(b);
      Matrix c = new Matrix(rowsA, columnsB);
      Matrix leftC = matrixMultiplyDivideConquer.divideAndRecursiveCall(a, partsOfB.getLeft(), this);
      Matrix rightC = multiply.multiply(a, partsOfB.getRight());
      MatrixOperations.copy(leftC, c, c.getRowsBegin(), c.getColumnsBegin());
      MatrixOperations.copy(rightC, c, c.getRowsEnd(), c.getColumnsEnd());
      return c;
    }

    //4. 1 1 0
    if (rowsAOdd && columnsAOdd && !columnsBOdd) {
      MatrixBlocks blocksOfA = split(a);
      Pair<Matrix, Matrix> partsOfB = verticalSplit(b);
      Matrix t1 = matrixMultiplyDivideConquer.divideAndRecursiveCall(blocksOfA.getAt11(), partsOfB.getLeft(), this);
      Matrix t2 = multiply.multiply(blocksOfA.getAt12(), partsOfB.getRight());
      MatrixOperations.add(t1, t2, t2);
      Matrix t3 = multiply.multiply(blocksOfA.getAt21(), partsOfB.getLeft());
      Matrix t4 = multiply.multiply(blocksOfA.getAt22(), partsOfB.getRight());
      MatrixOperations.add(t3, t4, t4);

      Matrix c = new Matrix(rowsA, columnsB);
      MatrixOperations.copy(t2, c, c.getRowsBegin(), c.getColumnsBegin());
      MatrixOperations.copy(t4, c, c.getRowsEnd(), c.getColumnsBegin());
      return c;
    }
    //5. 0 1 1
    if (!rowsAOdd && columnsAOdd && columnsBOdd) {
      Pair<Matrix, Matrix> partsOfA = horizontalSplit(b);
      MatrixBlocks blocksOfB = split(b);
      Matrix t1 = matrixMultiplyDivideConquer.divideAndRecursiveCall(partsOfA.getLeft(), blocksOfB.getAt11(), this);
      Matrix t2 = multiply.multiply(partsOfA.getRight(), blocksOfB.getAt21());
      MatrixOperations.add(t1, t2, t2);
      Matrix t3 = multiply.multiply(partsOfA.getLeft(), blocksOfB.getAt12());
      Matrix t4 = multiply.multiply(partsOfA.getRight(), blocksOfB.getAt22());
      MatrixOperations.add(t3, t4, t4);

      Matrix c = new Matrix(rowsA, columnsB);
      MatrixOperations.copy(t2, c, c.getRowsBegin(), c.getColumnsBegin());
      MatrixOperations.copy(t4, c, c.getRowsBegin(), c.getColumnsEnd());
      return c;
    }

    //6. 1 0 1
    if (rowsAOdd && !columnsAOdd && columnsBOdd) {
      Pair<Matrix, Matrix> partsOfA = verticalSplit(a);
      Pair<Matrix, Matrix> partsOfB = horizontalSplit(b);
      Matrix c11 = matrixMultiplyDivideConquer.divideAndRecursiveCall(partsOfA.getLeft(), partsOfB.getLeft(), this);
      Matrix c12 = multiply.multiply(partsOfA.getLeft(), partsOfB.getRight());
      Matrix c21 = multiply.multiply(partsOfA.getRight(), partsOfB.getLeft());
      Matrix c22 = multiply.multiply(partsOfA.getRight(), partsOfB.getRight());
      Matrix c = new Matrix(rowsA, columnsB);
      MatrixOperations.copy(c11, c, c.getRowsBegin(), c.getColumnsBegin());
      MatrixOperations.copy(c12, c, c.getRowsBegin(), c.getColumnsEnd());
      MatrixOperations.copy(c21, c, c.getRowsEnd(), c.getColumnsBegin());
      MatrixOperations.copy(c22, c, c.getRowsEnd(), c.getColumnsEnd());
      return c;
    }
    //7. 1 1 1
    if (rowsAOdd && columnsAOdd && columnsBOdd) {
      Matrix c = new Matrix(rowsA, columnsB);
      Matrix c11 = c.submatrix(c.getRowsBegin(), c.getRowsEnd() - 1,
                               c.getColumnsBegin(), c.getColumnsEnd() - 1);
      Matrix c12 = c.submatrix(c.getRowsBegin(), c.getRowsEnd() - 1,
                               c.getColumnsEnd(), c.getColumnsEnd());
      Matrix c21 = c.submatrix(c.getRowsEnd(), c.getRowsEnd(),
                               c.getColumnsBegin(), c.getColumnsEnd() - 1);
      Matrix c22 = c.submatrix(c.getRowsEnd(), c.getRowsEnd(),
                               c.getColumnsEnd(), c.getColumnsEnd());

      MatrixBlocks blocksOfA = split(a);
      MatrixBlocks blocksOfB = split(b);
      Matrix t1 = matrixMultiplyDivideConquer.divideAndRecursiveCall(blocksOfA.getAt11(), blocksOfB.getAt11(), this);
      Matrix t2 = multiply.multiply(blocksOfA.getAt12(), blocksOfB.getAt21());
      MatrixOperations.add(t1, t2, c11);

      Matrix t3 = multiply.multiply(blocksOfA.getAt11(), blocksOfB.getAt12());
      Matrix t4 = multiply.multiply(blocksOfA.getAt12(), blocksOfB.getAt22());
      MatrixOperations.add(t3, t4, c12);

      Matrix t5 = multiply.multiply(blocksOfA.getAt21(), blocksOfB.getAt11());
      Matrix t6 = multiply.multiply(blocksOfA.getAt22(), blocksOfB.getAt21());
      MatrixOperations.add(t5, t6, c21);

      Matrix t7 = multiply.multiply(blocksOfA.getAt21(), blocksOfB.getAt12());
      Matrix t8 = multiply.multiply(blocksOfA.getAt22(), blocksOfB.getAt22());
      MatrixOperations.add(t7, t8, c22);

      return c;
    }
    throw new IllegalStateException("should not occured!");
  }

  private Pair<Matrix, Matrix> horizontalSplit(Matrix matrix) {
    Matrix leftA =
        matrix.submatrix(matrix.getRowsBegin(), matrix.getRowsEnd(), matrix.getColumnsBegin(),
                         matrix.getColumnsEnd() - 1);
    Matrix rightLineA =
        matrix.submatrix(matrix.getRowsBegin(), matrix.getRowsEnd(), matrix.getColumnsEnd(), matrix.getColumnsEnd());
    return Pair.of(leftA, rightLineA);
  }

  private Pair<Matrix, Matrix> verticalSplit(Matrix matrix) {
    Matrix
        topA =
        matrix.submatrix(matrix.getRowsBegin(), matrix.getRowsEnd() - 1, matrix.getColumnsBegin(),
                         matrix.getColumnsEnd());
    Matrix
        bottomLineA =
        matrix.submatrix(matrix.getRowsEnd(), matrix.getRowsEnd(), matrix.getColumnsBegin(), matrix.getColumnsEnd());
    return Pair.of(topA, bottomLineA);
  }

  private MatrixBlocks split(Matrix matrix) {
    Matrix a11 = matrix.submatrix(matrix.getRowsBegin(), matrix.getRowsEnd() - 1,
                                  matrix.getColumnsBegin(), matrix.getColumnsEnd() - 1);
    Matrix a12 = matrix.submatrix(matrix.getRowsBegin(), matrix.getRowsEnd() - 1,
                                  matrix.getColumnsEnd(), matrix.getColumnsEnd());
    Matrix a21 = matrix.submatrix(matrix.getRowsEnd(), matrix.getRowsEnd(),
                                  matrix.getColumnsBegin(), matrix.getColumnsEnd() - 1);
    Matrix a22 = matrix.submatrix(matrix.getRowsEnd(), matrix.getRowsEnd(),
                                  matrix.getColumnsEnd(), matrix.getColumnsEnd());
    return new MatrixBlocks(a11, a12, a21, a22);
  }
}