package algorithms.dynamicprog;

import java.util.ArrayList;
import java.util.List;

import algorithms.fundamentals.divideconquer.Matrix;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/7/22.
 */
public class BottomUpMatrixChainOrderTest {

  @Test
  public void name() {
    List<Matrix> matrixList = new ArrayList<>();
    matrixList.add(new Matrix(30, 35));
    matrixList.add(new Matrix(35, 15));
    matrixList.add(new Matrix(15, 5));
    matrixList.add(new Matrix(5, 10));
    matrixList.add(new Matrix(10, 20));
    matrixList.add(new Matrix(20, 25));

    BottomUpMatrixChainOrder order = new BottomUpMatrixChainOrder();
    MatrixChainSolution solution = order.findMinCost(matrixList);
    Assert.assertEquals("cost",15125, solution.getCost().intValue());
    System.out.println(solution.descBestMultiplyChain(0, 5));
  }

}