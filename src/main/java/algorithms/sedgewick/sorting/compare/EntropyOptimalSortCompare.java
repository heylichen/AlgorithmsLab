package algorithms.sedgewick.sorting.compare;

import java.util.Random;

/**
 * Created by Chen Li on 2018/2/3.
 */
public class EntropyOptimalSortCompare extends AbstractSortCompare<Integer> {

  private Random rand = new Random();

  /**
   * Arrays with large numbers of duplicate keys performance compare
   * @param size
   * @return
   */
  @Override
  protected Integer[] createArray(int size) {
    Integer[] dataArr = new Integer[size * 2];
    int a = rand.nextInt();
    int b = rand.nextInt();
    for (int i = 0; i < size; i++) {
      int j = i * 2;
      dataArr[j] = a;
      dataArr[j + 1] = b;
    }
    return dataArr;
  }
}
