package algorithms.sedgewick.sorting.compare;

import algorithms.sedgewick.sorting.Sort;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by Chen Li on 2018/2/3.
 */
@Slf4j
public abstract class AbstractSortCompare<T extends Comparable<T>> implements SortCompare {

  @Override
  public void scaleSortPerformance(Sort sort, int startSize, int maxSize, int times) {
    int size = startSize;
    while (size < maxSize) {
      long elapsedMs = timeOf(sort, size, times);
      log.info("{} size {} , sort {} times, use {} ms", sort.toString(), size, times, elapsedMs);
      size = size * 2;
    }
  }

  @Override
  public Pair<Long, Long> compare(Sort sortA, Sort sortB, int size, int times) {
    long t1 = 0; // total for alg1
    long t2 = 0;
    try {
      t1 = timeOf(sortA, size, times);
      t2 = timeOf(sortB, size, times); // total for alg2
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Pair.of(t1, t2);
  }

  @Override
  public long timeOf(Sort sort, int size, int times) {
    long totalMs = 0;
    for (int i = 0; i < times; i++) {
      T[] randomArray = createArray(size);
      totalMs += time(sort, randomArray);
    }
    return totalMs;
  }

  protected long time(Sort sort, T[] dataArr) {
    long start = System.currentTimeMillis();
    sort.sort(dataArr);
    return System.currentTimeMillis() - start;
  }


  protected abstract T[] createArray(int size);
}
