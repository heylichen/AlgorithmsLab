package algorithms.sorting.compare.datagen;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by Chen Li on 2018/3/30.
 */
public class RandomDoubleGenerator implements DataGenerator<Double> {

  @Override
  public Double[] generate(int size) {
    Double[] data = new Double[size];
    for (int i = 0; i < size; i++) {
      data[i] = StdRandom.uniform();
    }
    return data;
  }
}
