package algorithms.dynamicprog;

import java.util.List;

import algorithms.fundamentals.divideconquer.Matrix;

/**
 * Created by Chen Li on 2018/7/22.
 * find the min cost for scalar calculation for matrix chain multiplication
 */
public interface MatrixChainOrder {

  MatrixChainSolution findMinCost(List<Matrix> matrixList);
}
