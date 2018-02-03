package algorithms.sedgewick.sorting.compare;

import algorithms.sedgewick.sorting.Sort;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/3.
 */
public interface SortCompare {

  Pair<Long, Long> compare(Sort sortA, Sort sortB, int size, int times);

  void scaleSortPerformance(Sort sort, int startSize, int maxSize, int times);

  long timeOf(Sort sort, int size, int times);
}
