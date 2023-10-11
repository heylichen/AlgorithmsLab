package algorithms.dynamicprog;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/7/24.
 */
public class BestBinarySearchTree {


  public Solution find(List<Double> pList, List<Double> qList) {
    List<Double> newPList = new ArrayList<>(qList.size());
    newPList.add(0d);
    for (Double aDouble : pList) {
      newPList.add(aDouble);
    }
    pList = newPList;
    Table<Integer, Integer, Double> expectedCostTable = HashBasedTable.create();
    Table<Integer, Integer, Integer> rootTable = HashBasedTable.create();
    Table<Integer, Integer, Double> wTable = HashBasedTable.create();

    int begin = 1;
    int end = qList.size() - 1;
    for (int i = begin; i <= end + 1; i++) {
      expectedCostTable.put(i, i - 1, qList.get(i - 1));
      wTable.put(i, i - 1, qList.get(i - 1));
    }

    int size = qList.size() - 1;
    for (int len = 1; len <= size; len++) {
      for (int i = 1; i <= size - len + 1; i++) {
        int j = i + len - 1;
        double minExpectedCost = Double.MAX_VALUE;
        double left;
        double right;
        double w;
        int rootIndex = 0;
        for (int k = i; k <= j; k++) {
          left = expectedCostTable.get(i, k - 1);
          right = expectedCostTable.get(k + 1, j);

          w = wTable.get(i, j - 1) + pList.get(j) + qList.get(j);
          wTable.put(i, j, w);
          double totalCost = left + right + w;
          if (totalCost < minExpectedCost) {
            minExpectedCost = totalCost;
            rootIndex = k;
          }
        }

        expectedCostTable.put(i, j, minExpectedCost);
        rootTable.put(i, j, rootIndex);
      }
    }

    Solution solution = new Solution(expectedCostTable.get(1, size), rootTable);
    return solution;
  }

  @Getter
  @Setter
  public static class Solution {

    private Double cost;
    private Table<Integer, Integer, Integer> splitIndexTable;

    public Solution() {
    }

    public Solution(Double cost,
                    Table<Integer, Integer, Integer> splitIndexTable) {
      this.cost = cost;
      this.splitIndexTable = splitIndexTable;
    }
  }
}
