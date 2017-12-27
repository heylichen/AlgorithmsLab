package algorithms.sedgewick.sorting.elementrary;

import edu.princeton.cs.algs4.StdRandom;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SortCompare {

  private long time(Sort sort, Double[] dataArr) {
    long start = System.currentTimeMillis();
    sort.sort(dataArr);
    return System.currentTimeMillis() - start;
  }

  public long timeRandomInput(Sort sort, int size, int times) {
    long totalMs = 0;
    for (int i = 0; i < times; i++) {
      Double[] randomArray = randomArray(size);
      totalMs += time(sort, randomArray);
    }
    return totalMs;
  }

  public void scaleSortPerformance(Class<? extends Sort> sortClass, int startSize, int maxSize, int times)
      throws IllegalAccessException, InstantiationException {
    Sort sortA = sortClass.newInstance();
    int size = startSize;
    while (size < maxSize) {
      long elapsedMs = timeRandomInput(sortA, size, times);
      log.info("{} size {} , sort {} times, use {} ms",sortClass.getSimpleName(), size, times, elapsedMs);
      size = size * 2;
    }
  }

  public void compare(Class<? extends Sort> sortClassA, Class<? extends Sort> sortClassB, int size, int times)
      throws IllegalAccessException, InstantiationException {
    long t1 = 0; // total for alg1
    long t2 = 0;
    Sort sortA = sortClassA.newInstance();
    Sort sortB = sortClassB.newInstance();
    try {
      t1 = timeRandomInput(sortA, size, times);
      t2 = timeRandomInput(sortB, size, times); // total for alg2
    } catch (Exception e) {
      e.printStackTrace();
    }

    log.info("For {} random Doubles\n {} is {} times faster than {}.  {} use {} ms, {} use {}",
             size, sortClassA.getSimpleName(),
             (double) t2 / (double) t1, sortClassB.getSimpleName(),
             sortClassA.getSimpleName(), t1,
             sortClassB.getSimpleName(), t2
    );
  }

  private Double[] randomArray(int size) {
    Double[] arr = new Double[size];
    for (int i = 0; i < size; i++) {
      arr[i] = StdRandom.uniform();
    }
    return arr;
  }
}
