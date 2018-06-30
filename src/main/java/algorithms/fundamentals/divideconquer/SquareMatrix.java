package algorithms.fundamentals.divideconquer;

import lombok.Getter;

/**
 * squares matrix that has equal rows and columns
 * so we can multiply two matrices
 */
@Getter
public class SquareMatrix {

  private final Integer[][] data;
  private final int rowsBegin;
  private final int rowsEnd;
  private final int columnsBegin;
  private final int columnsEnd;

  public SquareMatrix(Integer[][] data, int rowsBegin, int rowsEnd, int columnsBegin, int columnsEnd) {
    this.data = data;
    this.rowsBegin = rowsBegin;
    this.rowsEnd = rowsEnd;
    this.columnsBegin = columnsBegin;
    this.columnsEnd = columnsEnd;
    if (!isSquareMatrix()) {
      throw new IllegalArgumentException("rows and columns not equal, must be a square matrix!");
    }
  }

  public SquareMatrix(int rows) {
    this(new Integer[rows][rows], 0, rows - 1, 0, rows - 1);
  }

  public SquareMatrix(Integer[][] data, int rows) {
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

  private boolean isSquareMatrix() {
    return getEffectiveColumns() == getEffectiveRows();
  }

  public SquareMatrix submatrix(int rowsBegin, int rowsEnd, int columnsBegin, int columnsEnd) {
    return new SquareMatrix(data, rowsBegin, rowsEnd, columnsBegin, columnsEnd);
  }

//  public void multiply(SquareMatrix another, SquareMatrix result) {
//    int rows = getEffectiveRows();
//
//    for (int i = 0; i < rows; i++) {
//      for (int i1 = 0; i1 < rows; i1++) {
//        int sum = 0;
//        for (int j = 0; j < rows; j++) {
//          sum = this.get(i, j) * another.get(j, i1);
//        }
//        result.set(i, i1, sum);
//      }
//    }
//  }

  public static void add(SquareMatrix a, SquareMatrix b, SquareMatrix c) {
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
