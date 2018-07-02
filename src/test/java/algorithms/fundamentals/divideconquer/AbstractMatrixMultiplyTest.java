package algorithms.fundamentals.divideconquer;

import java.util.Objects;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public abstract class AbstractMatrixMultiplyTest {

  protected MatrixLoader matrixLoader = new MatrixLoader();
  private SimpleMatrixMultiply simpleMatrixMultiply = new SimpleMatrixMultiply();
  private Random random = new Random();

  protected abstract MatrixMultiply getInstance();

  @Test
  public void smallSquareMatrixTest() {
    smallMatrixTest("algorithms/fundamental/divideconquer/smallMatrix-a.text",
                    "algorithms/fundamental/divideconquer/smallMatrix-b.text");
    smallMatrixTest("algorithms/fundamental/divideconquer/tinyMatrix-a.text",
                    "algorithms/fundamental/divideconquer/tinyMatrix-b.text");
  }

  @Test
  public void rectangularMatrixTest() throws Exception {
    smallMatrixTest("algorithms/fundamental/divideconquer/tinyRectangularMatrix-a.text",
                    "algorithms/fundamental/divideconquer/tinyRectangularMatrix-b.text");
  }

  protected void smallMatrixTest(String pathA, String pathB) {
    Matrix ma = matrixLoader.load(pathA);
    Matrix mb = matrixLoader.load(pathB);
    MatrixMultiply matrixMultiply = getInstance();
    Matrix result = matrixMultiply.multiply(ma, mb);
    Matrix expected = simpleMatrixMultiply.multiply(ma, mb);
    Assert.assertTrue("", matrixEquals(expected, result));
    System.out.println(result);
  }

  @Test
  public void squareMatricesFunctionTest() {
    MatrixMultiply matrixMultiply = getInstance();
    for (int i = 1; i <= 64; i = i * 2) {
      log.info("{} squareMatricesFunctionTest, matrix size {} * {}", getClass().getSimpleName(), i, i);
      Matrix a = generate(i, i);
      Matrix b = generate(i, i);
      runAndVerify(matrixMultiply, a, b);
    }
  }

  protected void performanceCompare(MatrixMultiply a, MatrixMultiply b, int rows, int columns) {
    Matrix ma = generate(rows, columns);

    long aElapsed = runElapsedMs(a, ma, ma);
    long bElapsed = runElapsedMs(b, ma, ma);
    log.info("performanceCompare, matrix size {} * {}, {} : {} , {} : {}",
             rows, columns, a.getClass().getSimpleName(),
             aElapsed, b.getClass().getSimpleName(), bElapsed);
  }


  protected long runElapsedMs(MatrixMultiply matrixMultiply, Matrix a, Matrix b) {
    long start = System.currentTimeMillis();
    matrixMultiply.multiply(a, b);
    return System.currentTimeMillis() - start;
  }


  private void runAndVerify(MatrixMultiply matrixMultiply, Matrix a, Matrix b) {
    Matrix result = matrixMultiply.multiply(a, b);
    Matrix expected = simpleMatrixMultiply.multiply(a, b);
    Assert.assertTrue("", matrixEquals(expected, result));
  }

  private Matrix generate(int rows, int columns) {
    Integer[][] data = new Integer[rows][columns];
    for (int i = 0; i < rows; i++) {
      for (int i1 = 0; i1 < columns; i1++) {
        data[i][i1] = random.nextInt(100) + 1;
      }
    }
    return new Matrix(data, rows, columns);
  }

  private boolean matrixEquals(Matrix a, Matrix b) {
    int rows = a.getEffectiveRows();
    int columns = a.getEffectiveColumns();
    if (rows != b.getEffectiveRows() || columns != b.getEffectiveColumns()) {
      return false;
    }
    for (int i = 0; i < rows; i++) {
      for (int i1 = 0; i1 < columns; i1++) {
        if (!Objects.equals(a.get(i, i1), (b.get(i, i1)))) {
          return false;
        }
      }
    }
    return true;
  }
}
