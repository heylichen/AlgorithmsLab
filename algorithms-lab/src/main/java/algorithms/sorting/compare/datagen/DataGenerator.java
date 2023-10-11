package algorithms.sorting.compare.datagen;

/**
 * Created by Chen Li on 2018/3/18.
 */
public interface DataGenerator<T extends Comparable<T>> {

  T[] generate(int size);
}
