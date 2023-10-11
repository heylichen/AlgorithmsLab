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
    this(new Integer[rows][columns], 0, rows - 1, 0, columns - 1);
  }

  public Matrix(int rows) {
    this(new Integer[rows][rows], 0, rows - 1, 0, rows - 1);
  }

  public Matrix(Integer[][] data, int rows, int columns) {
    this(data, 0, rows - 1, 0, columns - 1);
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
  /**
   * split the original matrix into blockRowSize * blockColumnSize blocks of sub matrices,
   * return the block at index [i][j], index based on 0
   */
  public MatrixBlocks split(int blockRows, int blockColumns) {
    int blockRowsIndex = getRowsBegin() + blockRows;
    int blockColumnsIndex = getColumnsBegin() + blockColumns;
    Matrix a11 = submatrix(getRowsBegin(), blockRowsIndex - 1,
                           getColumnsBegin(), blockColumnsIndex - 1);
    Matrix a12 = submatrix(getRowsBegin(), blockRowsIndex - 1, blockColumnsIndex, getColumnsEnd());

    Matrix a21 = submatrix(blockRowsIndex, getRowsEnd(), getColumnsBegin(), blockColumnsIndex - 1);
    Matrix a22 = submatrix(blockRowsIndex, getRowsEnd(), blockColumnsIndex, getColumnsEnd());

    return new MatrixBlocks(a11, a12, a21, a22);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    int rows = getEffectiveRows();
    int columns = getEffectiveColumns();
    for (int i = 0; i < rows; i++) {
      for (int i1 = 0; i1 < columns; i1++) {
        sb.append(get(i, i1));
        if (i1 < columns - 1) {
          sb.append(",");
        }
      }
      sb.append("\n");
    }
    return sb.toString();
  }


}
