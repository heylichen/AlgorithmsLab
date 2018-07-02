package algorithms.fundamentals.divideconquer;

public class SimpleMatrixMultiply implements MatrixMultiply {

  @Override
  public Matrix multiply(Matrix a, Matrix b) {
    int rows = a.getEffectiveRows();
    int columns = a.getEffectiveColumns();
    int bRows = b.getEffectiveRows();
    int bColumns = b.getEffectiveColumns();
    if (columns != bRows) {
      throw new IllegalArgumentException("matrix not support multiply");
    }

    Matrix c = new Matrix(rows);
    for (int i = 0; i < rows; i++) {
      for (int i1 = 0; i1 < bColumns; i1++) {
        int sum = 0;
        for (int j = 0; j < columns; j++) {
          sum += a.get(i, j) * b.get(j, i1);
        }
        c.set(i, i1, sum);
      }
    }
    return c;
  }
}
