package algorithms.sedgewick.sorting.compare.datagen;

import java.util.Random;

/**
 * Created by Chen Li on 2018/4/1.
 */
public class EntropyOptimalDoubleGenetator implements DataGenerator<Integer> {

  private Random rand = new Random();

  @Override
  public Integer[] generate(int size) {
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
