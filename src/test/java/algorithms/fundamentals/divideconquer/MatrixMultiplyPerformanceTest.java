package algorithms.fundamentals.divideconquer;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import algorithms.context.AbstractContextTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/7/6.
 */
//run by hand
@Ignore
public class MatrixMultiplyPerformanceTest extends AbstractContextTest {

  @Resource(name = "standardStrassenMM")
  private MatrixMultiply standardStrassenMM;
  @Resource(name = "dynamicPaddingStrassenMM")
  private MatrixMultiply dynamicPaddingStrassenMM;
  @Resource(name = "dynamicPeelingStrassenMM")
  private MatrixMultiply dynamicPeelingStrassenMM;

  @Resource(name = "standardWinogradMM")
  private MatrixMultiply standardWinogradMM;
  @Resource(name = "dynamicPaddingWinogradMM")
  private MatrixMultiply dynamicPaddingWinogradMM;
  @Resource(name = "dynamicPeelingWinogradMM")
  private MatrixMultiply dynamicPeelingWinogradMM;

  @Before
  public void setUp() throws Exception {
    List<MatrixMultiply> mmList = Arrays.asList(standardWinogradMM, standardStrassenMM,
                                                dynamicPeelingStrassenMM, dynamicPaddingWinogradMM,
                                                dynamicPaddingStrassenMM, dynamicPeelingWinogradMM);
    for (MatrixMultiply matrixMultiply : mmList) {
      if (matrixMultiply instanceof CutoffMatrixMultiply) {
        ((CutoffMatrixMultiply) matrixMultiply).setCutoffSize(64);
      }
    }
  }

  @Test
  public void strassenVsWinograd() {
    //in theory, standardWinogradMM is asymptotically faster
    //1024*1024 standardStrassenMM is faster
    //2048*2048 standardWinogradMM is faster
    compareForSquareMatrices(standardStrassenMM, standardWinogradMM, 2048);
  }

  @Test
  public void paddingVsPeeling() {
    //1023, dynamicPaddingStrassenMM faster
    //1025  dynamicPeelingStrassenMM faster
    compareForSquareMatrices(dynamicPaddingStrassenMM, dynamicPeelingStrassenMM, 1025);
  }

  @Test
  public void winogradPaddingVsPeeling() {
    compareForSquareMatrices(dynamicPaddingWinogradMM, dynamicPeelingWinogradMM, 1025);
  }

  private void compareForSquareMatrices(MatrixMultiply m1, MatrixMultiply m2, int size) {
    AbstractMatrixMultiplyTest.performanceCompare(m1, m2, size, size);
    AbstractMatrixMultiplyTest.performanceCompare(m2, m1, size, size);
  }

  private void compare(MatrixMultiply m1, MatrixMultiply m2, int rows, int columns) {
    AbstractMatrixMultiplyTest.performanceCompare(m1, m2, rows, columns);
    AbstractMatrixMultiplyTest.performanceCompare(m2, m1, rows, columns);
  }
}
