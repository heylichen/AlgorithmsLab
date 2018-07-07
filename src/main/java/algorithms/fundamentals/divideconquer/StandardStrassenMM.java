package algorithms.fundamentals.divideconquer;

import lombok.Getter;
import lombok.Setter;

/**
 * only for matrices with dimensions of powers of 2
 */
@Getter
@Setter
public class StandardStrassenMM extends CutoffMatrixMultiply {

  private StandardMatrixMultiply multiply = new StandardMatrixMultiply();
  private MatrixMultiplyDivideConquer matrixMultiplyDivideConquer;


  @Override
  public Matrix multiply(Matrix a, Matrix b) {
    //cut off logic
    int rowsOfA = a.getEffectiveRows();
    if (rowsOfA <= getCutoffSize()) {
      return multiply.multiply(a, b);
    }
    return matrixMultiplyDivideConquer.divideAndRecursiveCall(a, b, this);
  }
}