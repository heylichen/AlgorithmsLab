package algorithms.dynamicprog;

import com.google.common.collect.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/7/22.
 */
@Getter
@Setter
public class MatrixChainSolution {

  private Integer cost;
  private Table<Integer, Integer, Integer> splitIndexTable;

  public String descBestMultiplyChain(int i, int j) {
    if (i > j) {
      throw new IllegalArgumentException(i + ">" + j);
    }
    if (i == j) {
      return "A" + i;
    }
    Integer k = splitIndexTable.get(i, j);
    String left = descBestMultiplyChain(i, k);
    String right = descBestMultiplyChain(k + 1, j);
    return "(" + left + " * " + right + ")";
  }
}
