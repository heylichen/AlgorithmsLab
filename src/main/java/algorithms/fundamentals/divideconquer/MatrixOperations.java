package algorithms.fundamentals.divideconquer;

import java.util.function.BiFunction;

public class MatrixOperations {

  public static Matrix add(Matrix a, Matrix b) {
    Matrix c = new Matrix(a.getEffectiveRows(),a.getEffectiveColumns());
    MatrixOperations.add(a, b, c);
    return c;
  }

  public static Matrix subtract(Matrix a, Matrix b) {
    Matrix c = new Matrix(a.getEffectiveRows(),a.getEffectiveColumns());
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
    int sum = 0;
    for (int i = 0; i < rows; i++) {
      for (int i1 = 0; i1 < columns; i1++) {
        sum = operator.apply(a.get(i, i1), b.get(i, i1));
        try {
          c.set(i, i1, sum);
        } catch (ArrayIndexOutOfBoundsException e) {
          e.printStackTrace();
        }

      }
    }
  }
}
