package algorithms.dynamicprog;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Table;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/7/24.
 */
public class BestBinarySearchTreeTest {

  @Test
  public void findSmall() {
    List<Double> pList = Arrays.asList(0.15d, 0.10d, 0.05d, 0.10d, 0.20d);
    List<Double> qList = Arrays.asList(0.05d, 0.10d, 0.05d, 0.05d, 0.05d, 0.10d);
    BestBinarySearchTree bestBinarySearchTree = new BestBinarySearchTree();
    BestBinarySearchTree.Solution solution = bestBinarySearchTree.find(pList, qList);
    Table<Integer, Integer, Integer> table = solution.getSplitIndexTable();

    Assert.assertEquals(2, table.get(1, 5).intValue());
    Assert.assertEquals(5, table.get(3, 5).intValue());
    Assert.assertEquals(4, table.get(3, 4).intValue());
    Assert.assertEquals(2.75, solution.getCost().doubleValue(), 0.00001);
    System.out.println(solution.getCost());
  }
}