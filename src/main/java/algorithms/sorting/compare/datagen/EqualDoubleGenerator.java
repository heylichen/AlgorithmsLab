package algorithms.sorting.compare.datagen;

/**
 * Created by Chen Li on 2018/3/30.
 */
public class EqualDoubleGenerator implements DataGenerator<Double> {

  public static final Double VALUE = 1.0d;

  @Override
  public Double[] generate(int size) {
    Double[] data = new Double[size];
    for (int i = 0; i < size; i++) {
      data[i] = VALUE;
    }
    return data;
  }
}
