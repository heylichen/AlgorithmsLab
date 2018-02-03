package algorithms.sedgewick.sorting.compare;

import edu.princeton.cs.algs4.StdRandom;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RandomArraySortCompare extends AbstractSortCompare<Double> {
  
  @Override
  protected Double[] createArray(int size) {
    Double[] arr = new Double[size];
    for (int i = 0; i < size; i++) {
      arr[i] = StdRandom.uniform();
    }
    return arr;
  }
}
