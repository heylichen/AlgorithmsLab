package algorithms.dynamicprog;

import java.util.List;

import algorithms.fundamentals.divideconquer.Matrix;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Created by Chen Li on 2018/7/22.
 */
public class BottomUpMatrixChainOrder implements MatrixChainOrder {

  @Override
  public MatrixChainSolution findMinCost(List<Matrix> matrixList) {
    int matrixCount = matrixList.size();
    Table<Integer, Integer, Integer> costTable = HashBasedTable.create();
    Table<Integer, Integer, Integer> splitTable = HashBasedTable.create();
    //init
    for (int i = 0; i < matrixCount; i++) {
      costTable.put(i, i, 0);
    }

    for (int length = 1; length <= matrixCount; length++) {
      for (int i = 0; i <= matrixCount - length; i++) {
        int minCost = Integer.MAX_VALUE;
        int j = i + length - 1;
        int multi = matrixList.get(i).getEffectiveRows() * matrixList.get(j).getEffectiveColumns();
        if (costTable.get(i, j) != null) {
          continue;
        }
        int bestK = -1;
        for (int k = i; k < j; k++) {
          int cost = costTable.get(i, k) + costTable.get(k + 1, j);
          cost += multi * matrixList.get(k).getEffectiveColumns();
          if (cost < minCost) {
            minCost = cost;
            bestK = k;
          }
        }
        costTable.put(i, j, minCost);
        splitTable.put(i, j, bestK);
      }
    }
    MatrixChainSolution solution = new MatrixChainSolution();
    solution.setCost(costTable.get(0, matrixCount - 1));
    solution.setSplitIndexTable(splitTable);
    return solution;
  }
}
