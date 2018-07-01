package algorithms.fundamentals.divideconquer;

import lombok.Getter;

@Getter
public class Matrix {

  private final Integer[][] data;
  private final int rowsBegin;
  private final int rowsEnd;
  private final int columnsBegin;
  private final int columnsEnd;

  public Matrix(Integer[][] data, int rowsBegin, int rowsEnd, int columnsBegin, int columnsEnd) {
    this.data = data;
    this.rowsBegin = rowsBegin;
    this.rowsEnd = rowsEnd;
    this.columnsBegin = columnsBegin;
    this.columnsEnd = columnsEnd;
  }

  public Matrix(int rows, int columns) {
    this(new Integer[rows][rows], 0, rows - 1, 0, columns - 1);
  }

  public Matrix(int rows) {
    this(new Integer[rows][rows], 0, rows - 1, 0, rows - 1);
  }

  public Matrix(Integer[][] data, int rows) {
    this(data, 0, rows - 1, 0, rows - 1);
  }

  public Integer get(int i, int j) {
    return data[rowsBegin + i][columnsBegin + j];
  }

  public void set(int i, int j, Integer v) {
    data[rowsBegin + i][columnsBegin + j] = v;
  }

  public int getEffectiveRows() {
    return rowsEnd - rowsBegin + 1;
  }

  public int getEffectiveColumns() {
    return columnsEnd - columnsBegin + 1;
  }

  public Matrix submatrix(int rowsBegin, int rowsEnd, int columnsBegin, int columnsEnd) {
    return new Matrix(data, rowsBegin, rowsEnd, columnsBegin, columnsEnd);
  }

  public static void add(Matrix a, Matrix b, Matrix c) {
    int rows = a.getEffectiveRows();
    int sum = 0;
    for (int i = 0; i < rows; i++) {
      for (int i1 = 0; i1 < rows; i1++) {
        sum = a.get(i, i1).intValue() + b.get(i, i1).intValue();
        c.set(i, i1, sum);
      }
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    int rows = getEffectiveRows();
    for (int i = 0; i < rows; i++) {
      for (int i1 = 0; i1 < rows; i1++) {
        sb.append(get(i, i1));
        if (i1 < rows - 1) {
          sb.append(",");
        }
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
