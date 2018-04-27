package algorithms.sorting.compare;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import algorithms.sedgewick.sorting.Sort;
import com.google.common.base.Stopwatch;

/**
 * Created by Chen Li on 2018/3/30.
 */
public class SimpleTimerSortRunner<T extends Comparable<T>> implements TimerSortRunner<T> {

  @Override
  public long timeTest(Sort<T> sort, T[] data, int times) {
    Stopwatch stopwatch = Stopwatch.createUnstarted();
    for (int i = 0; i < times; i++) {
      T dataCopy[] = Arrays.copyOf(data, data.length);
      System.arraycopy(data, 0, dataCopy, 0, data.length);
      stopwatch.start();
      sort.sort(dataCopy);
      stopwatch.stop();
    }

    return stopwatch.elapsed(TimeUnit.MILLISECONDS);
  }

}
