package algorithms.sedgewick.sorting.compare;

import java.util.Arrays;
import java.util.List;

import algorithms.sedgewick.sorting.Sort;
import algorithms.sedgewick.sorting.compare.RandomArraySortCompare;
import algorithms.sedgewick.sorting.compare.SortCompare;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/3.
 */
@Slf4j
public abstract class AbstractSortCompareTest {

  private SortCompare sortCompare = new RandomArraySortCompare();
  private static final String LOG_HEAD_FORMAT = "%30s %30s %7s %7s %5s";
  private static final String LOG_FORMAT = "%30d %30d %7.3f %7d %5d";

  protected List<Pair<Integer, Integer>> fastSizeAndTimesList = Arrays.asList(
      Pair.of(100000, 20),
      Pair.of(200000, 10),
      Pair.of(400000, 10),
      Pair.of(800000, 5)
  );

  protected List<Pair<Integer, Integer>> middleSizeAndTimesList = Arrays.asList(
      Pair.of(100000, 20),
      Pair.of(200000, 10),
      Pair.of(400000, 10)
  );

  protected List<Pair<Integer, Integer>> slowSizeAndTimesList = Arrays.asList(
      Pair.of(100000, 20),
      Pair.of(200000, 10)
  );

  protected void sortCompare(Sort sort1, Sort sort2, List<Pair<Integer, Integer>> sizeAndTimesList) throws Exception {
    //warming up
    Pair<Integer, Integer> startPair = sizeAndTimesList.get(0);
    twoRoundSortCompare(sort1, sort2, startPair.getLeft(), startPair.getRight(), sortCompare, false);
    sortCompare(sort1, sort2, sizeAndTimesList, sortCompare);
  }

  protected void sortCompare(Sort sort1, Sort sort2, List<Pair<Integer, Integer>> sizeAndTimesList,
                             SortCompare sortCompare) throws Exception {
    String logHead = String.format(LOG_HEAD_FORMAT, sort1.toString() + " ms",
                                   sort2.toString() + " ms", "faster", "size", "times");
    log.info(logHead);
    for (Pair<Integer, Integer> pair : sizeAndTimesList) {
      int size = pair.getLeft();
      int times = pair.getRight();
      twoRoundSortCompare(sort1, sort2, size, times, sortCompare, true);
    }
  }

  protected void twoRoundSortCompare(Sort sort1, Sort sort2, int size, int times, SortCompare sortCompare,
                                     boolean log) {
    Pair<Long, Long> pair1 = sortCompare.compare(sort1, sort2, size, times);
    Pair<Long, Long> pair2 = sortCompare.compare(sort2, sort1, size, times);
    if (log) {
      doLog(pair1.getLeft(), pair1.getRight(), size, times);
      doLog(pair2.getRight(), pair2.getLeft(), size, times);
    }
  }

  protected void doLog(Long elapsed1, Long elapsed2, int size, int times) {
    log.info(String.format(LOG_FORMAT, elapsed1, elapsed2, (double) elapsed2 / (double) elapsed1, size, times));
  }
}
