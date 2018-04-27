package algorithms.sorting.compare;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.compare.datagen.DataGenerator;
import algorithms.sorting.compare.datagen.DataGenerator;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/4/6.
 */
@Getter
@Setter
public class SortTestContext<T extends Comparable<T>> {

  private DataGenerator<T> dataGenerator;
  private Sort<T> sort;

}
