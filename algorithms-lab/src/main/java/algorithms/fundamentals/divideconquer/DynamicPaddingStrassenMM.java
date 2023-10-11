package algorithms.fundamentals.divideconquer;

import lombok.Getter;
import lombok.Setter;

/**
 * strassen matrix multiply for arbitrary sized matrices, using dynamic padding
 */
@Getter
@Setter
public class DynamicPaddingStrassenMM extends CutoffMatrixMultiply {

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
    //do padding and fixing

    boolean rowsAOdd = rowsA % 2 == 1;
    boolean columnsAOdd = columnsA % 2 == 1;
    boolean columnsBOdd = columnsB % 2 == 1;
    //all dimensions even, no padding needed
    if (!rowsAOdd && !columnsAOdd && !columnsBOdd) {
      return matrixMultiplyDivideConquer.divideAndRecursiveCall(a, b, this);
    }
    //do padding
    a = padding(a, rowsAOdd, columnsAOdd);
    b = padding(b, columnsAOdd, columnsBOdd);
    Matrix expandedC = matrixMultiplyDivideConquer.divideAndRecursiveCall(a, b, this);
    //extract by expected size whatever padding method used
    return expandedC.submatrix(expandedC.getRowsBegin(), expandedC.getRowsBegin() + rowsA - 1,
                               expandedC.getColumnsBegin(), expandedC.getColumnsBegin() + columnsB - 1
    );
  }

  private Matrix padding(Matrix matrix, boolean rowsOdd, boolean columnsOdd) {
    if (rowsOdd) {
      matrix = columnsOdd ? MatrixOperations.padBottomAndRight(matrix) : MatrixOperations.padBottom(matrix);
    } else if (columnsOdd) {
      matrix = MatrixOperations.padRight(matrix);
    }
    return matrix;
  }

}
