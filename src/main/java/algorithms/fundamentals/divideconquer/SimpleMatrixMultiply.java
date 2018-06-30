package algorithms.fundamentals.divideconquer;

public class SimpleMatrixMultiply implements MatrixMultiply {

  @Override
  public SquareMatrix multiply(SquareMatrix a, SquareMatrix b) {
    int rows = a.getEffectiveRows();
    SquareMatrix c = new SquareMatrix(rows);
    for (int i = 0; i < rows; i++) {
      for (int i1 = 0; i1 < rows; i1++) {
        int sum = 0;
        for (int j = 0; j < rows; j++) {
          sum += a.get(i, j) * b.get(j, i1);
        }
        c.set(i, i1, sum);
      }
    }
    return c;
  }
}
