package algorithms.fundamentals.divideconquer;

import java.util.function.BiFunction;

public class MatrixOperations {

  public static Matrix padBottom(Matrix a) {
    Matrix result = new Matrix(a.getEffectiveRows() + 1, a.getEffectiveColumns());
    copy(a, result);
    for (int i = 0; i < result.getEffectiveColumns(); i++) {
      result.set(result.getRowsEnd(), result.getColumnsBegin() + i, 0);
    }
    return result;
  }

  public static Matrix padRight(Matrix a) {
    Matrix result = new Matrix(a.getEffectiveRows(), a.getEffectiveColumns() + 1);
    copy(a, result);
    for (int i = 0; i < result.getEffectiveRows(); i++) {
      result.set(result.getRowsBegin() + i, result.getColumnsEnd(), 0);
    }
    return result;
  }

  public static Matrix padBottomAndRight(Matrix a) {
    Matrix result = new Matrix(a.getEffectiveRows() + 1, a.getEffectiveColumns() + 1);
    copy(a, result);
    for (int i = 0; i < result.getEffectiveColumns(); i++) {
      result.set(result.getRowsEnd(), result.getColumnsBegin() + i, 0);
    }
    for (int i = 0; i < result.getEffectiveRows(); i++) {
      result.set(result.getRowsBegin() + i, result.getColumnsEnd(), 0);
    }
    return result;
  }


  public static void copy(Matrix from, Matrix to) {
    copy(from, to, to.getRowsBegin(), to.getColumnsBegin());
  }

  public static void copy(Matrix from, Matrix to, int rowBegin, int columnBegin) {
    int rows = from.getEffectiveRows();
    int columns = from.getEffectiveColumns();

    for (int i = 0; i < rows; i++) {
      System.arraycopy(from.getData()[from.getRowsBegin() + i],
                       from.getColumnsBegin(),
                       to.getData()[rowBegin + i],
                       columnBegin,
                       columns);
    }
  }

  public static Matrix add(Matrix a, Matrix b) {
    Matrix c = new Matrix(a.getEffectiveRows(), a.getEffectiveColumns());
    MatrixOperations.add(a, b, c);
    return c;
  }

  public static Matrix subtract(Matrix a, Matrix b) {
    Matrix c = new Matrix(a.getEffectiveRows(), a.getEffectiveColumns());
    MatrixOperations.subtract(a, b, c);
    return c;
  }

  public static void add(Matrix a, Matrix b, Matrix c) {
    calculate(a, b, c, (k1, k2) -> k1.intValue() + k2.intValue());
  }

  public static void subtract(Matrix a, Matrix b, Matrix c) {
    calculate(a, b, c, (k1, k2) -> k1.intValue() - k2.intValue());
  }

  private static void calculate(Matrix a, Matrix b, Matrix c, BiFunction<Integer, Integer, Integer> operator) {
    int rows = a.getEffectiveRows();
    int columns = a.getEffectiveColumns();
    if (rows != b.getEffectiveRows() || columns != b.getEffectiveColumns()) {
      throw new IllegalArgumentException("Operation not supported , Matrices with different dimensions!");
    }
    int sum = 0;
    for (int i = 0; i < rows; i++) {
      for (int i1 = 0; i1 < columns; i1++) {
        sum = operator.apply(a.get(i, i1), b.get(i, i1));
        c.set(i, i1, sum);
      }
    }
  }

}
