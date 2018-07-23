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
    Table<Integer, Integer, String> lcsTable = HashBasedTable.create();
    for (int i = 0; i < m; i++) {
      lcsTable.put(i, -1, "");
    }
    for (int j = 0; j < m; j++) {
      lcsTable.put(-1, j, "");
    }
    lcsTable.put(-1, -1, "");

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (x.charAt(i) == y.charAt(j)) {
          String lcs = lcsTable.get(i - 1, j - 1) + x.charAt(i);
          lcsTable.put(i, j, lcs);
        } else {
          String left = lcsTable.get(i - 1, j);
          String right = lcsTable.get(i, j - 1);
          if (left.length() > right.length()) {
            lcsTable.put(i, j, left);
          } else {
            lcsTable.put(i, j, right);
          }
        }
      }
    }
    return lcsTable.get(m - 1, n - 1);
  }
}
