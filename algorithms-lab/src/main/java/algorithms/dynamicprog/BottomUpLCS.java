package algorithms.dynamicprog;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * Created by Chen Li on 2018/7/23.
 */
public class BottomUpLCS implements LongestCommonSubSequence {

  @Override
  public String find(String x, String y) {
    if (x == null || y == null) {
      return null;
    }
    int m = x.length();
    int n = y.length();
    Table<Integer, Integer, Integer> lcsTable = HashBasedTable.create();
    for (int i = 0; i < m; i++) {
      lcsTable.put(i, -1, 0);
    }
    for (int j = 0; j < m; j++) {
      lcsTable.put(-1, j, 0);
    }
    lcsTable.put(-1, -1, 0);

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (x.charAt(i) == y.charAt(j)) {
          Integer lcs = lcsTable.get(i - 1, j - 1) + 1;
          lcsTable.put(i, j, lcs);
        } else {
          Integer left = lcsTable.get(i - 1, j);
          Integer right = lcsTable.get(i, j - 1);
          if (left > right) {
            lcsTable.put(i, j, left);
          } else {
            lcsTable.put(i, j, right);
          }
        }
      }
    }
    return viewLcs(lcsTable, x, y, m - 1, n - 1);
  }

  private String viewLcs(Table<Integer, Integer, Integer> lcsTable, String x, String y, int i, int j) {
    if (i == -1 || j == -1) {
      return "";
    }
    Integer maxLen = lcsTable.get(i, j);
    Integer two = lcsTable.get(i - 1, j);
    if (x.charAt(i) == y.charAt(j)) {
      return viewLcs(lcsTable, x, y, i - 1, j - 1) + x.charAt(i);
    } else if (maxLen.equals(two)) {
      return viewLcs(lcsTable, x, y, i - 1, j);
    } else {
      return viewLcs(lcsTable, x, y, i, j - 1);
    }
  }
}
