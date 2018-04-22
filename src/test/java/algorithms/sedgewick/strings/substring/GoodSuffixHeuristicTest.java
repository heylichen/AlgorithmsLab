package algorithms.sedgewick.strings.substring;

import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/22.
 */
public class GoodSuffixHeuristicTest {

  @Test
  public void compileTest() {
    GoodSuffixHeuristic goodSuffixHeuristic = new GoodSuffixHeuristic();
    goodSuffixHeuristic.compile("ABBABAB");
    printGoodSufixHeuristic(goodSuffixHeuristic);


    goodSuffixHeuristic.compile("cabab");
    printGoodSufixHeuristic(goodSuffixHeuristic);
  }

  private void printGoodSufixHeuristic(GoodSuffixHeuristic goodSuffixHeuristic) {
    print("borderPosition ", goodSuffixHeuristic.getBorderPosition());
    print("shift ", goodSuffixHeuristic.getShift());
  }

  private void print(String arrayName, int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.println(arrayName + "[" + i + "]=" + array[i]);
    }
  }
}