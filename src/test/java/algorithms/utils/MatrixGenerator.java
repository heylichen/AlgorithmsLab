package algorithms.utils;

import java.util.Random;

import algorithms.fundamentals.divideconquer.Matrix;

/**
 * Created by Chen Li on 2018/7/4.
 */
public final class MatrixGenerator {

  private Random random = new Random();
  private int bound = 100;

  public static MatrixGenerator instance() {
    return new MatrixGenerator();
  }

  public Matrix generate(int rows, int columns) {
    Integer[][] data = new Integer[rows][columns];
    for (int i = 0; i < rows; i++) {
      for (int i1 = 0; i1 < columns; i1++) {
        data[i][i1] = random.nextInt(bound) + 1;
      }
    }
    return new Matrix(data, rows, columns);
  }
}
